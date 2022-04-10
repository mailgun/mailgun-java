package com.mailgun.model.webhooks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * <p>
 * Webhook.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-webhooks.html#webhooks">Webhooks</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Webhook {

    /**
     * <p>
     * URLs for the webhook event.
     * Only up to 3 URLs are supported.
     * </p>
     */
    List<String> urls;

}
