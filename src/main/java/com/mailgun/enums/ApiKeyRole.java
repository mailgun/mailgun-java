package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Mailgun API key role. */
@Getter
@RequiredArgsConstructor
public enum ApiKeyRole implements EnumWithValue {

    ADMIN("admin"),
    BASIC("basic"),
    SENDING("sending"),
    DEVELOPER("developer");

    @JsonValue
    private final String value;
}
