package com.mailgun.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Template information associated with an event item.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/events/get-v3-domain_name-events.md">Events API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventTemplate {

    /**
     * Name of the template the message was rendered from.
     */
    String name;

    /**
     * Version of the template that was rendered.
     */
    String version;

    /**
     * Whether the template is considered plain text (as opposed to HTML).
     */
    @JsonProperty("is-text")
    String isText;

}
