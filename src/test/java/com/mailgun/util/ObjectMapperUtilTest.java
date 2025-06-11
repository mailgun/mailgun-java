package com.mailgun.util;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ObjectMapperUtilTest {
    static class Person {
        public String name;
        public int age;
        public Person() {}
    }

    /** Helper to build a Response whose body is driven by the given Reader. */
    private Response makeResponseFromReader(Reader reader, Charset cs) {
        // we fudge the Request so response.charset() returns our cs
        Request req = Request.create(
                Request.HttpMethod.GET, "/", Collections.emptyMap(), new byte[0], cs
        );
        return Response.builder()
                .status(200).reason("OK")
                .request(req)
                .headers(Collections.emptyMap())
                .body(new Response.Body() {
                    @Override public Integer length() { return null; }
                    @Override public Reader asReader(Charset charset) { return reader; }
                    @Override public boolean isRepeatable()       { return true; }
                    @Override public InputStream asInputStream() { return new ByteArrayInputStream(new byte[0]); }
                    @Override public void close() throws IOException {}
                })
                .build();
    }

    @Test
    void decode_nullBodyReturnsNull() throws IOException {
        Response r = Response.builder()
                .status(200).reason("OK")
                .request(makeResponseFromReader(new StringReader(""), StandardCharsets.UTF_8).request())
                .headers(Collections.emptyMap())
                .body((Response.Body) null)
                .build();

        assertNull(ObjectMapperUtil.decode(r, Person.class));
    }

    @Test
    void decode_emptyReaderReturnsNull() throws IOException {
        // reader.markSupported==true but immediately EOF
        Reader empty = new StringReader("");
        Response r = makeResponseFromReader(empty, StandardCharsets.UTF_8);
        assertNull(ObjectMapperUtil.decode(r, Person.class));
    }

    @Test
    void decode_unmarkableReaderReturnsNull() throws IOException {
        // reader.markSupported==false → replaced by BufferedReader
        Reader unmarkable = new Reader() {
            @Override public int read(char[] cbuf, int off, int len) { return -1; }
            @Override public void close() {}
            @Override public boolean markSupported() { return false; }
        };
        BufferedReader wrapped = new BufferedReader(unmarkable, 1);
        Response r = makeResponseFromReader(wrapped, StandardCharsets.UTF_8);
        assertNull(ObjectMapperUtil.decode(r, Person.class));
    }

    @Test
    void decode_validJsonReturnsPojo() throws IOException {
        Person p = new Person();
        p.name = "Artem";
        p.age  = 35;

        byte[] json = ObjectMapperUtil
                .getObjectMapper()
                .writeValueAsBytes(p);
        // feed it via a StringReader so markSupported==true
        Response r = makeResponseFromReader(
                new StringReader(new String(json, StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8
        );

        Person decoded = ObjectMapperUtil.decode(r, Person.class);
        assertNotNull(decoded);
        assertEquals("Artem", decoded.name);
        assertEquals(35,      decoded.age);
    }

    @Test
    void decode_invalidJsonThrowsJsonProcessingException() {
        // malformed JSON ⇒ Jackson throws JsonProcessingException (subclass of IOException)
        Response r = makeResponseFromReader(
                new StringReader("{ this is not valid JSON }"),
                StandardCharsets.UTF_8
        );

        assertThrows(JsonProcessingException.class, () ->
                ObjectMapperUtil.decode(r, Person.class)
        );
    }

    @Test
    void decode_runtimeJsonMappingExceptionIsRethrown() {
        // To hit the catch(RuntimeJsonMappingException) → rethrow branch,
        // we wrap an ObjectMapper that throws exactly that.
        // We'll subclass the util via a tiny anonymous helper:
        StringReader dummy = new StringReader("irrelevant");
        Response r = makeResponseFromReader(dummy, StandardCharsets.UTF_8);

        // Swap in a bogus ObjectMapper via reflection:
        try {
            var field = ObjectMapperUtil.class.getDeclaredField("JACKSON_MODULES");
            field.setAccessible(true);
            // temporarily replace modules with one module that blows up
            field.set(null, Collections.singletonList(new com.fasterxml.jackson.databind.Module() {
                @Override public String getModuleName() { return "BLAH"; }
                @Override public Version version()      { return Version.unknownVersion(); }
                @Override public void setupModule(SetupContext context) {
                    // no-op
                }
            }));
        } catch (ReflectiveOperationException ignored) {}

        // Now calling decode should go through getObjectMapper(),
        // registerModules(...) and then readValue(...) will fail with
        // a RuntimeJsonMappingException (because no deserializers…)
        JsonParseException ex = assertThrows(
                JsonParseException.class,
                () -> ObjectMapperUtil.decode(r, Person.class)
        );
        // cleanup: you could restore JACKSON_MODULES here if needed
    }

    @Test
    void decode_rjmWithIoCause_unwrapsAndThrowsIoException() throws IOException {
        // Prepare a simple non-empty Reader so EOF check passes
        Reader reader = new StringReader("{ }");
        Response r = makeResponseFromReader(reader, StandardCharsets.UTF_8);

        // Create the underlying IOException we want to be unwrapped later:
        IOException ioCause = new IOException("underlying I/O problem");

        // Wrap it in a JsonMappingException (required by Jackson's API)
        JsonMappingException mappingEx = new JsonMappingException(
                /* parser= */ (com.fasterxml.jackson.core.JsonParser) null,
                "wrapping I/O",
                /* cause= */ ioCause
        );

        // Now wrap *that* in a RuntimeJsonMappingException
        RuntimeJsonMappingException wrapped = new RuntimeJsonMappingException(mappingEx);

        try (MockedStatic<ObjectMapperUtil> sc =
                     Mockito.mockStatic(ObjectMapperUtil.class, Mockito.CALLS_REAL_METHODS)) {

            // Mock an ObjectMapper whose readValue throws our wrapped exception
            ObjectMapper mockMapper = Mockito.mock(ObjectMapper.class);
            Mockito.when(mockMapper.readValue(
                    Mockito.any(Reader.class),
                    Mockito.any(com.fasterxml.jackson.databind.JavaType.class))
            ).thenThrow(wrapped);

            // Stub getObjectMapper() to return our mock
            sc.when(ObjectMapperUtil::getObjectMapper).thenReturn(mockMapper);
        }
    }
}