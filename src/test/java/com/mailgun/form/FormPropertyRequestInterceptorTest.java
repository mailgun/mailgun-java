package com.mailgun.form;


import feign.RequestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class FormPropertyRequestInterceptorTest {

    private RequestTemplate template;

    @BeforeEach
    void setUp() {
        template = mock(RequestTemplate.class);
    }

    @Test
    void builder_withOneProperty_buildsInterceptorThatAppliesThatEntry() {
        // given
        var interceptor = FormPropertyRequestInterceptor.builder()
                .addProperty("foo", "bar")
                .build();

        // when
        interceptor.apply(template);

        // then
        // capture the raw Map
        @SuppressWarnings("rawtypes")
        ArgumentCaptor<Map> captor = ArgumentCaptor.forClass(Map.class);

        verify(template).queries(captor.capture());
        Map<String, Collection<String>> passed = captor.getValue();

        assertEquals(1, passed.size());
        assertTrue(passed.containsKey("foo"));
        assertEquals(List.of("bar"), new ArrayList<>(passed.get("foo")));

        verifyNoMoreInteractions(template);
    }

    @Test
    void builder_withMultipleValuesForSameKey_accumulatesAllValues() {
        // given
        var interceptor = FormPropertyRequestInterceptor.builder()
                .addProperty("k", "v1")
                .addProperty("k", "v2")
                .addProperty("k", "v3")
                .build();

        // when
        interceptor.apply(template);

        // then
        @SuppressWarnings("rawtypes")
        ArgumentCaptor<Map> captor = ArgumentCaptor.forClass(Map.class);
        verify(template).queries(captor.capture());

        @SuppressWarnings("unchecked")
        Map<String, Collection<String>> passed = captor.getValue();

        assertEquals(List.of("v1","v2","v3"), new ArrayList<>(passed.get("k")));
        verifyNoMoreInteractions(template);
    }

}