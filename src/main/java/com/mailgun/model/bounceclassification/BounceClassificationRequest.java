package com.mailgun.model.bounceclassification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mailgun.model.Filter;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Request body for querying bounce classification metrics (POST /v2/bounce-classification/metrics).
 * Items with no bounces and no delays ({@code classified_failures_count == 0}) are not returned.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/bounce-classification/post-v2-bounce-classification-metrics.md">List statistic v2</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class BounceClassificationRequest {

    /**
     * A start timestamp (default: 7 days before current time). Must be in RFC 2822 format.
     */
    String start;

    /**
     * An end timestamp (default: current time). Must be in RFC 2822 format.
     */
    String end;

    /**
     * Resolution for the time series (e.g. "day", "hour", "month").
     */
    String resolution;

    /**
     * A duration in the format of '48h', '60m', '30s'. If provided, calculated from end date,
     * overwriting start.
     */
    String duration;

    /**
     * Dimensions to group by (e.g. "entity-name", "domain.name", "group-id", "severity", "category").
     */
    List<String> dimensions;

    /**
     * Metric names to return (e.g. "classified_failures_count").
     */
    List<String> metrics;

    /**
     * Filters to apply to the query.
     */
    Filter filter;

    /**
     * Include stats from all subaccounts.
     */
    @JsonProperty("include_subaccounts")
    Boolean includeSubaccounts;

    /**
     * Pagination and sorting options.
     */
    BounceClassificationPagination pagination;

}
