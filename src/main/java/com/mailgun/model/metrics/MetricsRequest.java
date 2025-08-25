package com.mailgun.model.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mailgun.enums.Resolution;
import com.mailgun.model.Filter;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Metrics request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#metrics">Metrics</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetricsRequest {

    /**
     * <p>
     * A start date.
     * </p>
     * {@link ZonedDateTime}
     */
    ZonedDateTime start;

    /**
     * <p>
     * A end date.
     * </p>
     * {@link ZonedDateTime}
     */
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
     * Metrics duration in the format of '1d' '2h' '2m'.
     * </p>
     */
    String duration;

    /**
     * <p>
     * Metrics dimensions.
     * </p>
     */
    List<String> dimensions;

    /**
     * <p>
     * Metrics options.
     * </p>
     */
    List<String> metrics;

    /**
     * <p>
     * Filters to apply to the query.
     * </p>
     */
    Filter filter;

    /**
     * <p>
     * Include stats from all subaccounts.
     * </p>
     */
    @JsonProperty("include_subaccounts")
    Boolean includeSubAccounts;

    /**
     * <p>
     * Include top-level aggregate metrics.
     * </p>
     */
    @JsonProperty("include_aggregates")
    Boolean includeAggregates;
}
