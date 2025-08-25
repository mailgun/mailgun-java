package com.mailgun.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Client information in logs response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/logs/post-v1-analytics-logs#logs/post-v1-analytics-logs/t=response&c=200&path=items/client-info">Logs client info</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemClientInfo {

    @JsonProperty("client-name")
    String clientName;

    @JsonProperty("client-os")
    String clientOs;

    @JsonProperty("client-type")
    String clientType;

    @JsonProperty("device-type")
    String deviceType;

    @JsonProperty("user-agent")
    String userAgent;

    String ip;
    String bot;
}