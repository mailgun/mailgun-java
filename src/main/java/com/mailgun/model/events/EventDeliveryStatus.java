package com.mailgun.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Event delivery status.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html">Events API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDeliveryStatus {

    Boolean tls;

    @JsonProperty("mx-host")
    String mxHost;

    Integer code;

    String description;

    @JsonProperty("session-seconds")
    Double sessionSeconds;

    @JsonProperty("retry-seconds")
    Integer retrySeconds;

    Boolean utf8;

    @JsonProperty("attempt-no")
    Integer attemptNo;

    /**
     * <p>
     * Status message.
     * E.g.:<code>OK</code>, <code>Not Found</code>
     * </p>
     */
    String message;

    @JsonProperty("certificate-verified")
    Boolean certificateVerified;

}
