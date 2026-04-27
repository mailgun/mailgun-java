package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Comparison operators for send alert filters.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/post-v1-thresholds-alerts-send.md">Create a send alert</a>
 */
public enum SendAlertFilterComparator implements EnumWithValue {

    EQ("="),
    NE("!="),
    CONTAINS("contains"),
    NOT_CONTAINS("not contains"),
    LT("<"),
    LE("<="),
    GT(">"),
    GE(">=");

    private final String value;

    SendAlertFilterComparator(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String getValue() {
        return value;
    }

}
