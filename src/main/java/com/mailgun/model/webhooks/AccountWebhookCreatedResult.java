package com.mailgun.model.webhooks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response returned when an account-level webhook is created or updated.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/account-webhooks/post-v1-webhooks.md">Create an account-level webhook</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/account-webhooks/put-v1-webhooks--webhook-id-.md">Update an account-level webhook</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountWebhookCreatedResult {

    /**
     * Unique identifier for the created or updated webhook.
     */
    @JsonProperty("webhook_id")
    String webhookId;

}
