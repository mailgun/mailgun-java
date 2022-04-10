package com.mailgun.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum YesNoHtml implements EnumWithValue {

    YES("yes"),
    NO("no"),
    HTML_ONLY("htmlonly");

    private final String value;

    YesNoHtml(String value) {
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
