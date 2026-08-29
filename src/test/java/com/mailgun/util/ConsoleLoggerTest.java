package com.mailgun.util;

import feign.Logger;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleLoggerTest {

    private static final String CONFIG_KEY = "MailgunApi#operation()";

    @Test
    void fullLoggingRedactsRequestHeadersAndBodyTest() {
        RecordingConsoleLogger logger = new RecordingConsoleLogger();
        Map<String, Collection<String>> headers = new LinkedHashMap<>();
        headers.put("Authorization", Arrays.asList("Basic test-api-credential"));
        headers.put("Cookie", Arrays.asList("session=test-session-token"));
        headers.put("Content-Type", Arrays.asList("multipart/form-data"));
        Request request = Request.create(Request.HttpMethod.POST, "https://api.example.test/v1/keys",
                headers, "password=test-smtp-password".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

        logger.recordRequest(Logger.Level.FULL, request);

        String output = logger.output();
        String normalizedOutput = output.toLowerCase();
        assertTrue(output.contains("POST https://api.example.test/v1/keys"));
        assertTrue(normalizedOutput.contains("content-type: multipart/form-data"));
        assertFalse(normalizedOutput.contains("authorization"));
        assertFalse(output.contains("test-api-credential"));
        assertFalse(normalizedOutput.contains("cookie"));
        assertFalse(output.contains("test-session-token"));
        assertFalse(output.contains("test-smtp-password"));
    }

    @Test
    void fullLoggingRedactsResponseHeadersAndBodyWithoutConsumingItTest() throws IOException {
        RecordingConsoleLogger logger = new RecordingConsoleLogger();
        Request request = Request.create(Request.HttpMethod.POST, "https://api.example.test/v1/keys",
                new LinkedHashMap<>(), null, StandardCharsets.UTF_8);
        Map<String, Collection<String>> headers = new LinkedHashMap<>();
        headers.put("Content-Type", Arrays.asList("application/json"));
        headers.put("Set-Cookie", Arrays.asList("session=test-response-token"));
        Response response = Response.builder()
                .status(200)
                .reason("OK")
                .headers(headers)
                .body("{\"secret\":\"test-generated-key\"}", StandardCharsets.UTF_8)
                .request(request)
                .build();

        Response loggedResponse = logger.recordResponse(Logger.Level.FULL, response);

        String output = logger.output();
        String normalizedOutput = output.toLowerCase();
        assertEquals("{\"secret\":\"test-generated-key\"}",
                new String(loggedResponse.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8));
        assertTrue(normalizedOutput.contains("content-type: application/json"));
        assertFalse(normalizedOutput.contains("set-cookie"));
        assertFalse(output.contains("test-response-token"));
        assertFalse(output.contains("test-generated-key"));
    }

    private static class RecordingConsoleLogger extends ConsoleLogger {

        private final StringBuilder messages = new StringBuilder();

        @Override
        protected void log(String configKey, String format, Object... args) {
            messages.append(String.format(format, args)).append('\n');
        }

        void recordRequest(Level level, Request request) {
            logRequest(CONFIG_KEY, level, request);
        }

        Response recordResponse(Level level, Response response) throws IOException {
            return logAndRebufferResponse(CONFIG_KEY, level, response, 1L);
        }

        String output() {
            return messages.toString();
        }
    }
}
