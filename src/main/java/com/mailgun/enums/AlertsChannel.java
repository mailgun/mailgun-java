package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Delivery channel for Mailgun Alerts (Optimize and Send Alert notifications).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/post-v1-alerts-settings-events.md">Add Alert</a>
 */
public enum AlertsChannel implements EnumWithValue {

    EMAIL("email"),
    WEBHOOK("webhook"),
    SLACK("slack");

    private final String value;

    AlertsChannel(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String getValue() {
        return value;
    }

}
