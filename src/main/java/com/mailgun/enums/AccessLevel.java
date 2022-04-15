package com.mailgun.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum AccessLevel {

    /**
     * <p>
     * Only authenticated users can post to this list. It is used for mass announcements and newsletters.
     * </p>
     * <p>
     * This is the default access level.
     * </p>
     */
    READ_ONLY("readonly"),

    /**
     * <p>
     * Subscribed members of the list can communicate with each other.
     * </p>
     */
    MEMBERS("members"),

    /**
     * <p>
     * Everyone can post to this list. Recommended turning spam filtering on when using this mode.
     * </p>
     */
    EVERYONE("everyone");

    private final String value;

    AccessLevel(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
