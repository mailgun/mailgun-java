package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Page {

    FIRST("first"),
    LAST("last"),
    NEXT("next"),
    PREVIOUS("previous");

    private final String value;

    Page(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
