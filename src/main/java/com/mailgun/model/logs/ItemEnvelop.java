package com.mailgun.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Envelope information in logs response.
 * </p>
 *
 *  @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/logs/post-v1-analytics-logs#logs/post-v1-analytics-logs/t=response&c=200&path=items/envelope">Logs item envelop</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemEnvelop {
    String sender;
    String transport;

    @JsonProperty("sending-ip")
    String sendingIp;

    String targets;

    @JsonProperty("i-ip-pool-id")
    String iIpPoolId;
}
