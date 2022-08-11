package com.mailgun.model.stats;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mailgun.enums.ResolutionPeriod;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;
import java.util.List;

import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME;

/**
 * <p>
 * Mailgun tracks all of the events that occur throughout the system.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#stats">Stats</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsResult {

    /**
     * <p>
     * Stats description.
     * </p>
     */
    String description;

    /**
     * <p>
     * The starting time.
     * </p>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME, locale = "en")
    ZonedDateTime start;

    /**
     * <p>
     * The ending date.
     * </p>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME, locale = "en")
    ZonedDateTime end;

    /**
     * <p>
     * Resolution
     * </p>
     * {@link ResolutionPeriod}
     * <p>
     * Can be either <code>HOUR</code>, <code>DAY</code> or <code>MONTH</code>
     * </p>
     */
    ResolutionPeriod resolution;

    /**
     * <p>
     * Statistics.
     * </p>
     * {@link Stats}
     */
    List<Stats> stats;

}
