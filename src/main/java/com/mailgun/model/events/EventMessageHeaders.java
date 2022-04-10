package com.mailgun.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Event message headers.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html">Events API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventMessageHeaders {

    /**
     * <p>
     * Message id.
     * </p>
     */
    @JsonProperty("message-id")
    String messageId;

    /**
     * <p>
     * Email address from <code>To</code> header.
     * </p>
     */
    String to;

    /**
     * <p>
     * Email address from <code>From</code> header.
     * </p>
     */
    String from;

    /**
     * <p>
     * Message subject.
     * </p>
     */
    String subject;

}
