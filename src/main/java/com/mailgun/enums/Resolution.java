package com.mailgun.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Resolution {
    @JsonProperty("day")
    DAY,
    @JsonProperty("hour")
    HOUR,
    @JsonProperty("month")
    MONTH
}
