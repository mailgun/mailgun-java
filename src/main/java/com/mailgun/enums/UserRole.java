package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * User role on a Mailgun account.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/users">Users API</a>
 */
public enum UserRole implements EnumWithValue {

    /** Basic/Analyst user role. */
    BASIC("basic"),
    /** Billing user role. */
    BILLING("billing"),
    /** Support user role. */
    SUPPORT("support"),
    /** Developer user role. */
    DEVELOPER("developer"),
    /** Admin user role. */
    ADMIN("admin");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String getValue() {
        return value;
    }
}
