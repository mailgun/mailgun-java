package com.mailgun.model.webhooks;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Optional query parameters for listing account-level webhooks (GET /v1/webhooks).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/account-webhooks/get-v1-webhooks.md">List account-level webhooks</a>
 */
@Value
@Jacksonized
@Builder
public class AccountWebhooksListQuery {

    /**
     * Comma-separated list of webhook IDs to filter results.
     * If specified, only webhooks with matching IDs will be returned.
     */
    // field name matches the query param name as required by FieldQueryMapEncoder
    String webhook_ids;

}
