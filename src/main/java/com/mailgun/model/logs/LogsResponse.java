package com.mailgun.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mailgun.model.Paging;

import java.util.List;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Logs response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/logs/post-v1-analytics-logs#logs/post-v1-analytics-logs/response&c=200/body">Logs</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogsResponse {

    /**
     * <p>
     * Start date for the query in ISO 8601 format.
     * </p>
     */
    String start;

    /**
     * <p>
     * End date for the query in ISO 8601 format.
     * </p>
     */
    String end;

    /**
     * <p>
     * List of log items.
     * </p>
     */
    List<LogsResponseItem> items;
    Paging pagination;
    LogResponseAggregate aggregates;
}
