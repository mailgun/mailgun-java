package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mailgun.enums.ResolutionPeriod;
import com.mailgun.model.stats.Stats;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;
import java.util.List;

import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME;

/**
 * <p>
 * Statistics for a tag.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-tags.html#tags">Tags</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagStatsResult {

    /**
     * <p>
     * Name of the tag.
     * </p>
     */
    String tag;

    /**
     * <p>
     * Optional description of a tag.
     * </p>
     */
    String description;

    /**
     * <p>
     * The starting time.
     * </p>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME)
    ZonedDateTime start;

    /**
     * <p>
     * The ending date.
     * </p>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME)
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
