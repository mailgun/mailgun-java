package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Comparison operators for the alert threshold (metric vs limit).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/post-v1-thresholds-alerts-send.md">Create a send alert</a>
 */
public enum SendAlertThresholdComparator implements EnumWithValue {

    EQ("="),
    NE("!="),
    LT("<"),
    LE("<="),
    GT(">"),
    GE(">=");

    private final String value;

    SendAlertThresholdComparator(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String getValue() {
        return value;
    }

}
