package com.mailgun.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * <p>
 * Event message info.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html">Events API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventMessage {

    /**
     * <p>
     * Event message headers.
     * {@link EventMessageHeaders}
     * </p>
     */
    EventMessageHeaders headers;

    /**
     * <p>
     * Message attachments..
     * </p>
     */
    List<String> attachments;

    /**
     * <p>
     * Message recipients.
     * </p>
     */
    List<String> recipients;

    /**
     * <p>
     * Message size.
     * </p>
     */
    Integer size;

}
