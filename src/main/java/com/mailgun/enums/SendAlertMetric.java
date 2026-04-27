package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Metrics monitored by send alerts.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/post-v1-thresholds-alerts-send.md">Create a send alert</a>
 */
public enum SendAlertMetric implements EnumWithValue {

    HARD_BOUNCE_RATE("hard_bounce_rate"),
    TEMPORARY_FAIL_RATE("temporary_fail_rate"),
    DELIVERED_RATE("delivered_rate"),
    COMPLAINED_RATE("complained_rate");

    private final String value;

    SendAlertMetric(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String getValue() {
        return value;
    }

}
