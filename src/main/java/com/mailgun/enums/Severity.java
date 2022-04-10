package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Severity {

    TEMPORARY("temporary"),
    PERMANENT("permanent");

    private final String value;

    Severity(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
