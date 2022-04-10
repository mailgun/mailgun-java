package com.mailgun.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum Duration {

     HOUR("h"),
     DAY("d"),
     MONTH("m");

    private final String value;

    Duration(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
