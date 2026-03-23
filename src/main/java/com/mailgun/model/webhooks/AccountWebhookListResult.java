package com.mailgun.model.webhooks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Response wrapper for the list of account-level webhooks.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/account-webhooks/get-v1-webhooks.md">List account-level webhooks</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountWebhookListResult {

    /**
     * List of account-level webhooks.
     */
    List<AccountWebhook> webhooks;

}
