package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResolutionPeriod {

    HOUR("hour"),
    DAY("day"),
    MONTH("month");

    private final String value;

    ResolutionPeriod(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }


}
