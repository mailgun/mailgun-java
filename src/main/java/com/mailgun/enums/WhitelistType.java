package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WhitelistType {

    ADDRESS("address"),
    DOMAIN("domain");

    private final String value;

    WhitelistType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
