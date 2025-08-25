package com.mailgun.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mailgun.model.Filter;

import java.util.Set;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Logs request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/logs/post-v1-analytics-logs#logs/post-v1-analytics-logs/request/body">Logs</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogsRequest {

    /**
     * <p>
     * The start date (default: 1 day before current time). Must be in RFC 2822 format: https://datatracker.ietf.org/doc/html/rfc2822.html#page-14
     * </p>
     */
    String start;

    /**
     * <p>
     * The end date (default: current time). Must be in RFC 2822 format: https://datatracker.ietf.org/doc/html/rfc2822.html#page-14
     * </p>
     */
    String end;

    /**
     * <p>
     * The set of events to include.
     * </p>
     */
    Set<String> events;

    /**
     * <p>
     * Optional set of analytics metric events. Will be converted into corresponding.
     * </p>
     */
    @JsonProperty("metric_events")
    Set<String> metricEvents;

    /**
     * <p>
     * Filters to apply to the query.
     * </p>
     * {@link Filter}
     */
    Filter filter;

    /**
     * <p>
     * Include logs from all subaccounts.
     * </p>
     */
    @JsonProperty("include_subaccounts")
    Boolean includeSubaccounts;

    /**
     * <p>
     * Include total number of log entries.
     * </p>
     */
    Boolean includeTotals;

    /**
     * <p>
     * Pagination options.
     * </p>
     * {@link LogsPagination}
     */
    LogsPagination pagination;
}
