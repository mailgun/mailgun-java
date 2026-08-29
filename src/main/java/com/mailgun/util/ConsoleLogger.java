package com.mailgun.util;

import feign.Logger;
import feign.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Default SDK logger. Sensitive authentication headers are omitted, and bodies are never logged because
 * Mailgun requests and responses can contain message content, passwords, private keys, and generated secrets.
 * Consequently, {@link Level#FULL} is intentionally body-safe and logs at header detail.
 */
@Slf4j
public class ConsoleLogger extends Logger {

    private static final Set<String> SENSITIVE_HEADERS = new HashSet<>(Arrays.asList(
            "authorization",
            "cookie",
            "proxy-authorization",
            "set-cookie"
    ));

    @Override
    protected boolean shouldLogRequestHeader(String header) {
        return !isSensitiveHeader(header);
    }

    @Override
    protected boolean shouldLogResponseHeader(String header) {
        return !isSensitiveHeader(header);
    }

    /**
     * Request bodies can contain message contents, API keys, SMTP passwords, and private keys.
     * The default SDK logger therefore caps FULL logging at headers.
     */
    @Override
    protected void logRequest(String configKey, Level logLevel, feign.Request request) {
        super.logRequest(configKey, withoutBody(logLevel), request);
    }

    /** Response bodies can contain generated API keys, SMTP passwords, and signing keys. */
    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response,
                                              long elapsedTime) throws IOException {
        return super.logAndRebufferResponse(configKey, withoutBody(logLevel), response, elapsedTime);
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        log.info(String.format(String.format("%s%s", methodTag(configKey), format), args));
    }

    private static Level withoutBody(Level level) {
        return level == Level.FULL ? Level.HEADERS : level;
    }

    private static boolean isSensitiveHeader(String header) {
        return header != null && SENSITIVE_HEADERS.contains(header.toLowerCase());
    }

}
