package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SpamAction {

    DISABLED("disabled"),
    BLOCK("block"),
    TAG("tag");

    private final String value;

    SpamAction(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }


}
