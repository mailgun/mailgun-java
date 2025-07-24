package com.mailgun.form;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.multipart.Output;

import static feign.form.ContentType.MULTIPART;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MultipartFormContentProcessorTest {

    private MultipartFormContentProcessor processor;
    private RequestTemplate template;
    private Charset charset = StandardCharsets.UTF_8;

    @BeforeEach
    void setUp() {
        // delegate is never actually called in our tests, so a no-op is fine
        Encoder noopDelegate = (object, bodyType, reqTemplate) -> { /* no-op */ };
        processor = new MultipartFormContentProcessor(noopDelegate);
        template  = new RequestTemplate();
    }

    @Test
    void getSupportedContentType_returnsMultipart() {
        assertEquals(MULTIPART, processor.getSupportedContentType());
    }

    @Test
    void process_singleStringEntry_setsHeaderAndBody() throws EncodeException {
        Map<String,Object> data = Collections.singletonMap("hello", "world");

        processor.process(template, charset, data);

        // --- header assertions ---
        List<String> ct = (List<String>) template.headers().get("Content-Type");
        assertNotNull(ct, "Content-Type header should be present");
        assertEquals(1, ct.size());
        String contentType = ct.get(0);

        assertTrue(contentType.startsWith("multipart/form-data"),
                "should start with multipart/form-data");
        assertTrue(contentType.contains("charset=" + charset.name()),
                "should contain the charset");
        assertTrue(contentType.matches(".*boundary=[0-9a-fA-F]+"),
                "should contain a hex boundary");

        // --- body assertions ---
        byte[] bodyBytes = template.body();
        assertNotNull(bodyBytes);
        String body = new String(bodyBytes, charset);

        // extract the boundary from the header
        String boundary = contentType.substring(contentType.indexOf("boundary=") + 9);
        assertTrue(body.startsWith("--" + boundary),
                "body should start with the boundary marker");
        assertTrue(body.trim().endsWith("--" + boundary + "--"),
                "body should end with the closing boundary");

        // payload should include our form name/value
        assertTrue(body.contains("name=\"hello\""), "should contain the field name");
        assertTrue(body.contains("world"),           "should contain the field value");
    }

    @Test
    void process_skipsNullValueEntries() throws EncodeException {
        Map<String,Object> data = new LinkedHashMap<>();
        data.put("skipMe", null);
        data.put("keepMe", "yes");

        processor.process(template, charset, data);

        String body = new String(template.body(), charset);

        assertFalse(body.contains("skipMe"), "entries with null values must be skipped");
        assertTrue(body.contains("name=\"keepMe\""), "non-null entries must be written");
        assertTrue(body.contains("yes"),             "non-null entries must be written");
    }

    @Test
    void addWriter_and_findApplicableWriter_favorsNewWriter() throws Exception {
        // define a dummy type + writer
        class Foo { }
        Foo foo = new Foo();
        feign.form.multipart.Writer fooWriter = new feign.form.multipart.Writer() {
            @Override
            public boolean isApplicable(Object v) {
                return v instanceof Foo;
            }
            @Override
            public void write(Output out, String boundary, String key, Object value) {
                out.write(("FOO:" + key).getBytes(charset));
            }
        };

        // insert at front via reflection so it wins before the built-ins
        var writersField = MultipartFormContentProcessor.class
                .getDeclaredField("writers");
        writersField.setAccessible(true);
        @SuppressWarnings("unchecked")
        Deque<feign.form.multipart.Writer> deque =
                (Deque<feign.form.multipart.Writer>) writersField.get(processor);
        deque.addFirst(fooWriter);

        // mix Foo + a plain String
        Map<String,Object> data = new LinkedHashMap<>();
        data.put("myFoo", foo);
        data.put("text",  "abc");

        processor.process(template, charset, data);

        String body = new String(template.body(), charset);
        // sentinel from our custom Foo writer
        assertTrue(body.contains("FOO:myFoo"), "custom writer should handle Foo");
        // and the default path still handles the String
        assertTrue(body.contains("name=\"text\""), "String entries must still be handled");
    }
}