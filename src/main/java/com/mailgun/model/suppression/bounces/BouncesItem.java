package com.mailgun.model.suppression.bounces;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME;

/**
 * <p>
 * Bounces item.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#bounces">Suppressions/Bounces</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BouncesItem {

    /**
     * <p>
     * An email address.
     * </p>
     */
    String address;

    /**
     * <p>
     * Error code.
     * </p>
     */
    String code;

    /**
     * <p>
     * Error description.
     * </p>
     */
    String error;

    /**
     * <p>
     * Timestamp of a bounce event.
     * </p>
     */
    @JsonFormat(pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME, locale = "en")
    @JsonProperty("created_at")
    ZonedDateTime createdAt;

    /**
     * <p>
     * Message hash.
     * </p>
     */
    @JsonProperty("MessageHash")
    String messageHash;

}
