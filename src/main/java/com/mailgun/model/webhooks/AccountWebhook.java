package com.mailgun.model.webhooks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Account-level webhook details returned by the Account Webhooks API.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/account-webhooks/get-v1-webhooks--webhook-id-.md">Get account-level webhook by ID</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountWebhook {

    /**
     * Unique identifier for the webhook.
     */
    @JsonProperty("webhook_id")
    String webhookId;

    /**
     * User-provided description of the webhook.
     */
    String description;

    /**
     * The endpoint URL where webhook events are delivered.
     */
    String url;

    /**
     * List of event types that trigger this webhook.
     */
    @JsonProperty("event_types")
    List<String> eventTypes;

    /**
     * Timestamp indicating when the webhook was created in RFC3339 format.
     */
    @JsonProperty("created_at")
    String createdAt;

}
