package com.mailgun.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Routes information in logs response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/logs/post-v1-analytics-logs#logs/post-v1-analytics-logs/t=response&c=200&path=items/routes">Logs routes</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemRoutes {

    String actions;
    String description;
    String expression;
    String id;
    Integer priority;
    ItemRoutesMatch match;
}