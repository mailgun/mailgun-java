package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Supported webhooks
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-webhooks.html#webhooks">Webhooks</a>
 */
public enum WebhookName implements EnumWithValue {

    /**
     * <p>
     * Tracking Clicks.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-clicks">Tracking Clicks</a>
     */
    CLICKED("clicked"),

    /**
     * <p>
     * Tracking Spam Complaints.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-spam-complaints">Tracking Spam Complaints</a>
     */
    COMPLAINED("complained"),

    /**
     * <p>
     * Tracking Deliveries.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-deliveries">Tracking Deliveries</a>
     */
    DELIVERED("delivered"),

    /**
     * <p>
     * Tracking Opens.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-opens">Tracking Opens</a>
     */
    OPENED("opened"),

    /**
     * <p>
     * Tracking Permanent Failures.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-failures">Tracking Failures</a>
     */
    PERMANENT_FAIL("permanent_fail"),

    /**
     * <p>
     * Tracking Temporary Failures.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-failures">Tracking Failures</a>
     */
    TEMPORARY_FAIL("temporary_fail"),

    /**
     * <p>
     * Tracking Unsubscribes.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-unsubscribes">Tracking Unsubscribes</a>
     */
    UNSUBSCRIBED("unsubscribed");

    private final String value;

    WebhookName(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
