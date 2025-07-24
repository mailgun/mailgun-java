package com.mailgun.model.metrics;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mailgun.enums.Resolution;
import com.mailgun.model.Pagination;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static com.mailgun.util.Constants.ENGLISH;
import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NUMERIC;

/**
 * <p>
 * Metrics response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#metrics">Metrics</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetricsResponse<T> {

    /**
     * <p>
     * Metric items.
     * </p>
     * {@link MetricsItem}
     */
    List<MetricsItem<T>> items;

    /**
     * <p>
     * A start date.
     * </p>
     * {@link ZonedDateTime}
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NUMERIC, locale = ENGLISH)
    ZonedDateTime start;

    /**
     * <p>
     * A end date.
     * </p>
     * {@link ZonedDateTime}
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NUMERIC, locale = ENGLISH)
    ZonedDateTime end;

    /**
     * <p>
     * Metrics resolution.
     * </p>
     * {@link Resolution}
     */
    Resolution resolution;

    /**
     * <p>
     * Metrics dimensions.
     * </p>
     */
    List<String> dimensions;

    /**
     * <p>
     * Metrics duration in the format of '1d' '2h' '2m'.
     * </p>
     */
    String duration;

    /**
     * <p>
     * Metric aggregates.
     * </p>
     * {@link MetricAggregates}
     */
    MetricAggregates<T> aggregates;

    /**
     * <p>
     * Pagination information.
     * </p>
     * {@link Pagination}
     */
    Pagination pagination;
}
