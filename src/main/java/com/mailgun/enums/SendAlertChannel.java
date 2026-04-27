package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Notification channels for send alerts.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/post-v1-thresholds-alerts-send.md">Create a send alert</a>
 */
public enum SendAlertChannel implements EnumWithValue {

    EMAIL("email"),
    SLACK("slack"),
    WEBHOOK("webhook");

    private final String value;

    SendAlertChannel(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String getValue() {
        return value;
    }

}
