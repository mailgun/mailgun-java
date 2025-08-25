package com.mailgun.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Logs pagination.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/logs/post-v1-analytics-logs#logs/post-v1-analytics-logs/t=request&path=pagination">Logs pagination</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogsPagination {

    /**
     * <p>
     * Colon-separated value indicating column name and sort direction e.g. 'timestamp:desc'.
     * </p>
     */
    String sort;

    /**
     * <p>
     * A token to the requested page.
     * </p>
     */
    String token;

    /**
     * <p>
     * The maximum number of items returned (100 max).
     * </p>
     */
    Integer limit;
}
