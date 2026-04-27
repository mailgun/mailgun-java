package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Dimensions for send alert metrics and filters.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/post-v1-thresholds-alerts-send.md">Create a send alert</a>
 */
public enum SendAlertDimension implements EnumWithValue {

    DOMAIN("domain"),
    IP("ip"),
    IP_POOL("ip_pool"),
    RECIPIENT_PROVIDER("recipient_provider"),
    SUBACCOUNT("subaccount");

    private final String value;

    SendAlertDimension(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String getValue() {
        return value;
    }

}
