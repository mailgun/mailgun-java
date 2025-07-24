package com.mailgun.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Logs request for Mailgun Logs API.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/openapi-final/logs/post-v1-analytics-logs">Mailgun Logs API</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogsRequest {
    /**
     * The start date (default: 1 day before current time). Must be in RFC 2822 format: https://datatracker.ietf.org/doc/html/rfc2822.html#page-14
     * Example: "Mon, 08 Jul 2024 00:00:00 -0000"
     */
    String start;

    /**
     * The end date (default: current time). Must be in RFC 2822 format: https://datatracker.ietf.org/doc/html/rfc2822.html#page-14
     * Example: "Fri, 12 Jul 2024 00:00:00 -0000"
     */
    String end;

    /**
     * The set of events to include. Array of event type strings.
     * Example: ["delivered", "failed"]
     */
    List<String> events;

    /**
     * Optional set of analytics metric events. Will be converted into corresponding events.
     * Example: ["accepted", "opened"]
     */
    @JsonProperty("metric_events")
    List<String> metricEvents;

    /**
     * Filters to apply to the query. See documentation for filter object structure.
     * Example: { "AND": [ { "attribute": "domain", "comparator": "=", "values": [{ "label": "example.com", "value": "example.com" }] } ] }
     */
    Map<String, Object> filter;

    /**
     * Include logs from all subaccounts. Default: false.
     */
    @JsonProperty("include_subaccounts")
    Boolean includeSubaccounts;

    /**
     * Include total number of log entries. Default: false.
     */
    @JsonProperty("include_totals")
    Boolean includeTotals;

    /**
     * Pagination object. Controls sorting, page token, and limit.
     */
    Pagination pagination;

    /**
     * <p>
     * Pagination object for Mailgun Logs API.
     * </p>
     * <ul>
     *   <li>sort: Colon-separated value indicating column name and sort direction, e.g. 'timestamp:asc'.</li>
     *   <li>token: Opaque string for pagination, returned by previous response.</li>
     *   <li>limit: Maximum number of items to return.</li>
     * </ul>
     */
    @Value
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pagination {
        /**
         * Colon-separated value indicating column name and sort direction, e.g. 'timestamp:asc'.
         */
        String sort;
        /**
         * Opaque string for pagination, returned by previous response.
         */
        String token;
        /**
         * Maximum number of items to return.
         */
        Integer limit;
    }
} 