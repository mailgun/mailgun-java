package com.mailgun.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum ReplyPreference {

    LIST("list"),
    SENDER("sender");

    private final String value;

    ReplyPreference(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
