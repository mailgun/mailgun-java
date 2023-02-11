package com.mailgun.model.suppression.complaints;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

import static com.mailgun.util.Constants.ENGLISH;
import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME_DAY_OCTAL_LITERAL;

/**
 * <p>
 * Complaints item.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#complaints">Suppressions/Complaints</a>
 */
@Value
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplaintsItem {

    /**
     * <p>
     * An email address.
     * </p>
     */
    String address;

    /**
     * <p>
     * Timestamp of a complaints event.
     * </p>
     * {@link ZonedDateTime}
     */
    @JsonFormat(pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME_DAY_OCTAL_LITERAL, locale = ENGLISH)
    @JsonProperty("created_at")
    ZonedDateTime createdAt;

}
