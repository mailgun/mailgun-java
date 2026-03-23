package com.mailgun.model.webhooks;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for bulk-deleting account-level webhooks (DELETE /v1/webhooks).
 * Either {@code webhook_ids} or {@code all} must be provided. If both are set,
 * only the specified {@code webhook_ids} are deleted.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/account-webhooks/delete-v1-webhooks.md">Delete account-level webhooks</a>
 */
@Value
@Jacksonized
@Builder
public class AccountWebhooksDeleteQuery {

    /**
     * Comma-separated list of webhook IDs to delete.
     * If provided, only these specific webhooks will be deleted.
     */
    // field name matches the query param name as required by FieldQueryMapEncoder
    String webhook_ids;

    /**
     * Set to {@code true} to delete all account-level webhooks.
     * Acts as a safety mechanism to prevent accidental deletion.
     */
    Boolean all;

}
