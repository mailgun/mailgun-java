package com.mailgun.model.subaccounts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

import static com.mailgun.util.Constants.ENGLISH;
import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME_DAY_OCTAL_LITERAL;

/**
 * Mailgun subaccount entity.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subaccount {

    String id;

    String name;

    @JsonFormat(pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME_DAY_OCTAL_LITERAL, locale = ENGLISH)
    @JsonProperty("created_at")
    ZonedDateTime createdAt;

    @JsonFormat(pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME_DAY_OCTAL_LITERAL, locale = ENGLISH)
    @JsonProperty("updated_at")
    ZonedDateTime updatedAt;

    String status;

    SubaccountFeatures features;

}
