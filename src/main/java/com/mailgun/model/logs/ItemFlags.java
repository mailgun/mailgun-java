package com.mailgun.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Flags information in logs response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/logs/post-v1-analytics-logs#logs/post-v1-analytics-logs/t=response&c=200&path=items/flags">Logs flags</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemFlags {

    @JsonProperty("is-authenticated")
    Boolean isAuthenticated;

    @JsonProperty("is-system-test")
    Boolean isSystemTest;

    @JsonProperty("is-routed")
    Boolean isRouted;

    @JsonProperty("is-amp")
    Boolean isAmp;

    @JsonProperty("is-test-mode")
    Boolean isTestMode;

    @JsonProperty("is-delayed-bounce")
    Boolean isDelayedBounce;

    @JsonProperty("is-callback")
    Boolean isCallback;

    @JsonProperty("is-encrypted")
    Boolean isEncrypted;
}
