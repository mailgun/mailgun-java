package com.mailgun.model.webhooks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Webhook details result.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-webhooks.html#webhooks">Webhooks</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebhookDetailsResult {

    /**
     * <p>
     * {@link Webhook}
     * </p>
     */
    Webhook webhook;

}
