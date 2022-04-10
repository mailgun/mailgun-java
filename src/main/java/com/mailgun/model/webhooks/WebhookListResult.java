package com.mailgun.model.webhooks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Webhook list result.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-webhooks.html#webhooks">Webhooks</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebhookListResult {

    /**
     * <p>
     * {@link Webhooks}.
     * </p>
     */
    Webhooks webhooks;

}
