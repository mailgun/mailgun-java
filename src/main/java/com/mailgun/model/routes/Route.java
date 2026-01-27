package com.mailgun.model.routes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;
import java.util.List;

import static com.mailgun.util.Constants.ENGLISH;
import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME;

/**
 * <p>
 * Route.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/routes">Routes</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {

    /**
     * <p>
     * Route id.
     * </p>
     */
    String id;

    /**
     * <p>
     * Integer: smaller number indicates higher priority.
     * Higher priority routes are handled first.
     * </p>
     */
    Integer priority;

    /**
     * <p>
     * Route description.
     * </p>
     */
    String description;

    /**
     * <p>
     * A filter expression.
     * </p>
     */
    String expression;

    /**
     * <p>
     * Route action.
     * </p>
     */
    List<String> actions;

    /**
     * <p>
     * Route creation time.
     * </p>
     * {@link ZonedDateTime}
     */
    @JsonFormat(pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME, locale = ENGLISH)
    @JsonProperty("created_at")
    ZonedDateTime createdAt;

}
