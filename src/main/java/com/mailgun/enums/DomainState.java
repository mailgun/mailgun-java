package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DomainState {

    ACTIVE("active"),
    UNVERIFIED("unverified"),
    DISABLED("disabled");

    private final String value;

    DomainState(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
