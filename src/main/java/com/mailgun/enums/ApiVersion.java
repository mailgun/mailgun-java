package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApiVersion {

    V_1("v1"),
    V_2("v2"),
    V_3("v3"),
    V_4("v4"),
    V_5("v5");

    private final String value;

    ApiVersion(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
