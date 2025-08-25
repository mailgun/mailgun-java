package com.mailgun.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Delivery status information in logs response.
 * </p>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/logs/post-v1-analytics-logs#logs/post-v1-analytics-logs/t=response&c=200&path=items/delivery-status">Logs item delivery status</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDeliveryStatus {

    String message;

    @JsonProperty("attempt-no")
    Integer attemptNo;

    Integer code;

    @JsonProperty("bounce-type")
    String bounceType;

    String description;

    @JsonProperty("session-seconds")
    Float sessionSeconds;

    @JsonProperty("retry-seconds")
    Integer retrySeconds;

    @JsonProperty("enhanced-code")
    String enhancedCode;

    @JsonProperty("mx-host")
    String mxHost;

    @JsonProperty("certificate-verified")
    Boolean certificateVerified;

    Boolean tls;

    Boolean utf8;

    @JsonProperty("first-delivery-attempt-seconds")
    Integer firstDeliveryAttemptSeconds;

    @JsonProperty("last-code")
    Integer lastCode;

    @JsonProperty("last-message")
    String lastMessage;
}
