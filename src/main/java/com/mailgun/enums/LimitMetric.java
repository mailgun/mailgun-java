package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Metrics monitored by account limit thresholds (pre-send features).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/limits/post-v1-thresholds-limits.md">Create a limit threshold</a>
 */
public enum LimitMetric implements EnumWithValue {

    EMAIL_PREVIEW_SUCCESS_COUNT("email_preview_success_count"),
    SEED_TEST_COUNT("seed_test_count");

    private final String value;

    LimitMetric(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String getValue() {
        return value;
    }

}
