package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WebScheme {

    HTTP("http"),
    HTTPS("https");

    private final String value;

    WebScheme(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
