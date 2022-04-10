package com.mailgun.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Event client info.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html">Events API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventClientInfo {

    @JsonProperty("client-type")
    String clientType;

    @JsonProperty("client-os")
    String clientOs;

    @JsonProperty("device-type")
    String deviceType;

    @JsonProperty("client-name")
    String clientName;

    @JsonProperty("user-agent")
    String userAgent;

}
