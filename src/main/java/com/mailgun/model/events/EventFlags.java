package com.mailgun.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Event flags.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html">Events API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventFlags {

    @JsonProperty("is-authenticated")
    Boolean isAuthenticated;

    @JsonProperty("is-delayed-bounce")
    Boolean isDelayedBounce;

    @JsonProperty("is-routed")
    Boolean isRouted;

    @JsonProperty("is-system-test")
    Boolean isSystemTest;

    @JsonProperty("is-test-mode")
    Boolean isTestMode;

}
