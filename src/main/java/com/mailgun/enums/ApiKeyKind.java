package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Mailgun API key kind. */
@Getter
@RequiredArgsConstructor
public enum ApiKeyKind implements EnumWithValue {

    DOMAIN("domain"),
    USER("user"),
    WEB("web");

    @JsonValue
    private final String value;
}
