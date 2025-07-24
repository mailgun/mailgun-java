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
 * Logs response for Mailgun Logs API.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/openapi-final/logs/post-v1-analytics-logs">Mailgun Logs API</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogsResponse {
    /**
     * The start date for the log search (RFC 2822 format, required).
     * Example: "Mon, 08 Jul 2024 00:00:00 -0000"
     */
    String start;

    /**
     * The end date for the log search (RFC 2822 format, required).
     * Example: "Fri, 12 Jul 2024 00:00:00 -0000"
     */
    String end;

    /**
     * List of log event items returned by the query. Each item represents a log event. Required.
     */
    @JsonProperty("items")
    List<LogItem> items;

    /**
     * Pagination information for the log results.
     * Contains tokens for previous, next, first, last pages, and total count.
     */
    Pagination pagination;

    /**
     * Aggregated statistics for the log results, if requested.
     * Structure depends on the aggregation requested in the query.
     */
    Map<String, Object> aggregates;

    /**
     * <p>
     * Pagination object for Mailgun Logs API response.
     * </p>
     * <ul>
     *   <li>previous: Token to previous page.</li>
     *   <li>next: Token to next page.</li>
     *   <li>first: Token to first page.</li>
     *   <li>last: Token to last page.</li>
     *   <li>total: Total number of items.</li>
     * </ul>
     */
    @Value
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pagination {
        /**
         * Token to previous page.
         */
        String previous;
        /**
         * Token to next page.
         */
        String next;
        /**
         * Token to first page.
         */
        String first;
        /**
         * Token to last page.
         */
        String last;
        /**
         * Total number of items.
         */
        Integer total;
    }
} 