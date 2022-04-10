package com.mailgun.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum YesNo implements EnumWithValue {

    YES("yes"),
    NO("no");

    private final String value;

    YesNo(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String getValue() {
        return value;
    }

    public static String getValue(boolean b) {
        return b ? YES.value : NO.value;
    }

}
