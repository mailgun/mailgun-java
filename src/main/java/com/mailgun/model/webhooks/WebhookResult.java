package com.mailgun.model.webhooks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Webhook result.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-webhooks.html#webhooks">Webhooks</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebhookResult {

    /**
     * <p>
     * Result status message.
     * </p>
     */
    String message;

    /**
     * <p>
     * {@link Webhook}.
     * </p>
     */
    Webhook webhook;

}
