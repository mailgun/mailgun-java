package com.mailgun.form;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import feign.RequestTemplate;
import feign.codec.Encoder;
import feign.form.ContentProcessor;
import feign.form.ContentType;

import static com.mailgun.form.FormEncoder.MAP_STRING_WILDCARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class FormEncoderTest {

    @Mock
    Encoder delegate;
    @Mock
    ContentProcessor urlProcessor;
    @Mock
    ContentProcessor multiProcessor;

    @Captor
    ArgumentCaptor<Map<String, Object>> dataCaptor;
    FormEncoder encoder;
    RequestTemplate template;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // pretend we only support URL-encoded and multipart
        when(urlProcessor.getSupportedContentType())
                .thenReturn(ContentType.of("application/x-www-form-urlencoded"));
        when(multiProcessor.getSupportedContentType())
                .thenReturn(feign.form.ContentType.MULTIPART);

        Map<feign.form.ContentType,ContentProcessor> procs = new HashMap<>();
        procs.put(ContentType.of("application/x-www-form-urlencoded"), urlProcessor);
        procs.put(feign.form.ContentType.MULTIPART, multiProcessor);

        encoder = new FormEncoder(delegate, procs);
        template = new RequestTemplate();
    }


    //--------------------------------------------------------------------------
    // getContentTypeValue(...)
    //--------------------------------------------------------------------------
    @Test
    void getContentTypeValue_noHeaderReturnsNull() {
        assertNull(invokeGetContentTypeValue(Collections.emptyMap()));
    }

    @Test
    void getContentTypeValue_headerWithOnlyNullsSkipsAndReturnsNull() {
        // use Arrays.asList, which supports null entries
        Map<String, Collection<String>> hdrs = Map.of(
                "Content-Type",
                Arrays.asList(null, null)
        );
        assertNull(invokeGetContentTypeValue(hdrs));
    }

    @Test
    void getContentTypeValue_headerReturnsFirstNonNull() {
        Map<String, Collection<String>> hdrs = Map.of(
                "content-type",
                Arrays.asList(
                        null,
                        "text/plain; charset=ISO-8859-1",
                        "ignored"
                )
        );
        String found = invokeGetContentTypeValue(hdrs);
        assertEquals("text/plain; charset=ISO-8859-1", found);
    }

    //--------------------------------------------------------------------------
    // getCharset(...)
    //--------------------------------------------------------------------------
    @Test
    void getCharset_explicitCharset() {
        Charset cs = invokeGetCharset("application/json; charset=US-ASCII");
        assertEquals(Charset.forName("US-ASCII"), cs);
    }

    @Test
    void getCharset_missingCharsetDefaultsToUtf8() {
        Charset cs = invokeGetCharset("application/json");
        assertEquals(StandardCharsets.UTF_8, cs);
    }

    //--------------------------------------------------------------------------
    // encode(...) unsupported content‐type
    //--------------------------------------------------------------------------
    @Test
    void encode_unsupportedContentType_delegatesAndReturns() throws Exception {
        // no Content-Type header at all
        encoder.encode("anything", String.class, template);
        verify(delegate).encode("anything", (Type) String.class, template);
        verifyNoMoreInteractions(urlProcessor, multiProcessor);
    }

    //--------------------------------------------------------------------------
    // encode(...) supported but wrong bodyType
    //--------------------------------------------------------------------------
    @Test
    void encode_supportedButNonMapNonPojo_delegates() throws Exception {
        // set Content-Type to application/x-www-form-urlencoded
        template.header("Content-Type", "application/x-www-form-urlencoded");
        // bodyType = Integer.class → not Map and not a user‐POJO
        encoder.encode(42, Integer.class, template);

        verify(delegate).encode(42, (Type) Integer.class, template);
        verifyNoMoreInteractions(urlProcessor);
    }

    //--------------------------------------------------------------------------
    // encode(...) wildcard map
    //--------------------------------------------------------------------------
    @Test
    void encode_supportedMap_writesRawMap() throws Exception {
        template.header("Content-Type", "application/x-www-form-urlencoded");
        Map<String, Object> payload = Map.of("a", 1, "b", "two");

        encoder.encode(payload, MAP_STRING_WILDCARD, template);

        verify(urlProcessor).process(eq(template), any(Charset.class), dataCaptor.capture());
        assertSame(payload, dataCaptor.getValue(), "Should pass the original map");
    }

    //--------------------------------------------------------------------------
    // encode(...) user‐POJO branch
    //--------------------------------------------------------------------------
    static class MyPojo {
        public String foo = "bar";
        public int n = 5;
    }

    @Test
    void encode_supportedPojo_convertsToMapThenWrites() throws Exception {
        template.header("Content-Type", "application/x-www-form-urlencoded");
        MyPojo pojo = new MyPojo();

        encoder.encode(pojo, MyPojo.class, template);

        verify(urlProcessor).process(eq(template), any(Charset.class), dataCaptor.capture());
        Map<String, Object> m = dataCaptor.getValue();
        // should contain both fields
        assertEquals(2, m.size());
        assertEquals("bar", m.get("foo"));
        assertEquals(5, m.get("n"));
    }

    //--------------------------------------------------------------------------
    // helpers to call private methods via reflection
    //--------------------------------------------------------------------------
    private String invokeGetContentTypeValue(Map<String, Collection<String>> hdrs) {
        try {
            var m = FormEncoder.class.getDeclaredMethod("getContentTypeValue", Map.class);
            m.setAccessible(true);
            return (String) m.invoke(encoder, hdrs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Charset invokeGetCharset(String value) {
        try {
            var m = FormEncoder.class.getDeclaredMethod("getCharset", String.class);
            m.setAccessible(true);
            return (Charset) m.invoke(encoder, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}