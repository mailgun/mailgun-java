package com.mailgun.model.credentials;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

import static com.mailgun.util.Constants.ENGLISH;
import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NUMERIC;

/** SMTP credential metadata returned by Mailgun. */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmtpCredential {

    String mailbox;

    String login;

    @JsonFormat(pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NUMERIC, locale = ENGLISH)
    @JsonProperty("created_at")
    ZonedDateTime createdAt;

    @JsonProperty("size_bytes")
    String sizeBytes;
}
