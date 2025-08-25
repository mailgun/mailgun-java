package com.mailgun.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Logs response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/logs/post-v1-analytics-logs#logs/post-v1-analytics-logs/t=response&c=200&path=items">Logs Response Item</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogsResponseItem {

    String id;
    String event;

    @JsonProperty("@timestamp")
    String timestamp;

    ItemAccount account;

    List<ItemCampaign> campaigns;

    List<String> tags;

    String method;

    @JsonProperty("originating-ip")
    String originatingIp;

    @JsonProperty("api-key-id")
    String apiKeyId;

    @JsonProperty("delivery-status")
    ItemDeliveryStatus deliveryStatus;

    @JsonProperty("i-delivery-optimizer")
    String iDeliveryOptimizer;

    ItemDomain domain;

    String recipient;

    @JsonProperty("recipient-domain")
    String recipientDomain;

    @JsonProperty("recipient-provider")
    String recipientProvider;

    ItemEnvelop envelope;

    ItemStorage storage;

    ItemTemplate template;

    @JsonProperty("log-level")
    String logLevel;

    @JsonProperty("user-variables")
    String userVariables;

    ItemMessage message;
    ItemFlags flags;

    @JsonProperty("primary-dkim")
    String primaryDkim;

    String ip;
    ItemGeolocation geolocation;

    @JsonProperty("client-info")
    ItemClientInfo clientInfo;

    String severity;
    String reason;
    ItemRoutes routes;

    @JsonProperty("mailing-list")
    ItemMailingList mailingList;

    String url;
}
