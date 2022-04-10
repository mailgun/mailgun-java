package com.mailgun.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum EventType {

    ACCEPTED("accepted"),
    REJECTED("rejected"),
    DELIVERED("delivered"),
    FAILED("failed"),
    OPENED("opened"),
    CLICKED("clicked"),
    UNSUBSCRIBED("unsubscribed"),
    COMPLAINED("complained"),
    STORED("stored");

    private final String value;

    EventType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
