package com.mailgun.interceptors;

import feign.RequestTemplate;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MailgunRequestInterceptorTest {

    @Test
    void builder_withNoProperties_onlySetsHeaders() {
        // 1) Build an interceptor with two headers
        MailgunRequestInterceptor interceptor =
                MailgunRequestInterceptor.builder()
                        .addHeader("X-Test-1", "value1")
                        .addHeader("X-Test-1", "value2")
                        .addHeader("X-Test-2", "other")
                        .build();

        RequestTemplate template = new RequestTemplate();
        interceptor.apply(template);

        // 2) After apply, headers() should contain exactly what we added
        Map<String, Collection<String>> hdrs = template.headers();
        assertEquals(2, hdrs.size());
        assertTrue(hdrs.containsKey("X-Test-1"));
        assertTrue(hdrs.containsKey("X-Test-2"));

        // The first header must have both values, in order of insertion
        Collection<String> test1Values = hdrs.get("X-Test-1");
        assertArrayEquals(new String[]{"value1","value2"}, test1Values.toArray());

        // And the second header only the single value
        assertArrayEquals(new String[]{"other"}, hdrs.get("X-Test-2").toArray());

        // 3) No query parameters should have been added
        assertTrue(template.queries().isEmpty());
    }

    @Test
    void builder_withProperties_alsoSetsQueries() {
        // 1) Build with both headers and properties
        MailgunRequestInterceptor interceptor =
                MailgunRequestInterceptor.builder()
                        .addHeader("H", "hval")
                        .addProperty("p1", "v1")
                        .addProperty("p1", "v2")
                        .addProperty("p2", "z")
                        .build();

        RequestTemplate template = new RequestTemplate();
        interceptor.apply(template);

        // 2) Headers as before
        Map<String, Collection<String>> hdrs = template.headers();
        assertEquals(1, hdrs.size());
        assertArrayEquals(new String[]{"hval"}, hdrs.get("H").toArray());

        // 3) Queries now present
        Map<String, Collection<String>> queries = template.queries();
        // two distinct property names
        assertEquals(2, queries.size());
        assertTrue(queries.containsKey("p1"));
        assertTrue(queries.containsKey("p2"));

        // p1 must yield both values
        assertArrayEquals(new String[]{"v1","v2"}, queries.get("p1").toArray());
        // p2 only one
        assertArrayEquals(new String[]{"z"}, queries.get("p2").toArray());
    }

    @Test
    void apply_withEmptyBuilder_doesNothing() {
        // If you never add header or property, nothing happens
        MailgunRequestInterceptor interceptor = MailgunRequestInterceptor.builder().build();
        RequestTemplate template = new RequestTemplate();
        interceptor.apply(template);

        assertTrue(template.headers().isEmpty(), "no headers");
        assertTrue(template.queries().isEmpty(), "no queries");
    }
}