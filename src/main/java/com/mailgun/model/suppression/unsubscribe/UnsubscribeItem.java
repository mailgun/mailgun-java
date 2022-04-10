package com.mailgun.model.suppression.unsubscribe;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;
import java.util.List;

import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME_DAY_OCTAL_LITERAL;

/**
 * <p>
 * Unsubscribe item.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#unsubscribes">Suppressions/Unsubscribe</a>
 */
@Value
@Jacksonized
@ToString
@EqualsAndHashCode
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnsubscribeItem {

    /**
     * <p>
     * An email address.
     * </p>
     */
    String address;

    /**
     * <p>
     * List of tag names.
     * </p>
     */
    List<String> tags;

    /**
     * <p>
     * Timestamp of an unsubscribe event.
     * </p>
     * {@link ZonedDateTime}
     */
    @JsonFormat(pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME_DAY_OCTAL_LITERAL)
    @JsonProperty("created_at")
    ZonedDateTime createdAt;

}
