package com.mailgun.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Event envelope.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html">Events API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventEnvelope {

    /**
     * <p>
     * Event transport method.
     * </p>
     */
    String transport;

    /**
     * <p>
     * Event sender email.
     * </p>
     */
    String sender;

    /**
     * <p>
     * Event sending IP.
     * </p>
     */
    @JsonProperty("sending-ip")
    String sendingIp;

    /**
     * <p>
     * Event target email.
     * </p>
     */
    String targets;

}
