package com.mailgun.model.alerts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response for {@code PUT /v1/alerts/settings/webhooks/signing_key}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/put-v1-alerts-settings-webhooks-signing-key.md">Reset Webhook Signing Key</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlertsWebhookSigningKeyResponse {

    @JsonProperty("signing_key")
    String signingKey;

}
