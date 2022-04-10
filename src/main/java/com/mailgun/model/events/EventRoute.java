package com.mailgun.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Event route.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html">Events API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventRoute {


    /**
     * <p>
     * Event route id.
     * </p>
     */
    String id;

    /**
     * <p>
     * Expression.
     * </p>
     */
    String expression;

    /**
     * <p>
     * {@link EventRouteMatch}.
     * </p>
     */
    EventRouteMatch match;

}
