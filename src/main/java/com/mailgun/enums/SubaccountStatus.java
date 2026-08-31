package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Mailgun subaccount status. */
@Getter
@RequiredArgsConstructor
public enum SubaccountStatus implements EnumWithValue {

    DISABLED("disabled"),
    OPEN("open"),
    CLOSED("closed");

    @JsonValue
    private final String value;
}
