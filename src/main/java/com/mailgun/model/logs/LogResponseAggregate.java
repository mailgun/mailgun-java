package com.mailgun.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Aggregates information in logs response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/logs/post-v1-analytics-logs#logs/post-v1-analytics-logs/t=response&c=200&path=aggregates">Logs aggregates</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogResponseAggregate {

    Long all;
    Map<String, Long> metrics;
}