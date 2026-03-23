package com.mailgun.api.v1;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.webhooks.AccountWebhook;
import com.mailgun.model.webhooks.AccountWebhookCreatedResult;
import com.mailgun.model.webhooks.AccountWebhookListResult;
import com.mailgun.model.webhooks.AccountWebhookRequest;
import com.mailgun.model.webhooks.AccountWebhooksDeleteQuery;
import com.mailgun.model.webhooks.AccountWebhooksListQuery;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * Account Webhooks API (v1): create, retrieve, update, and delete account-level webhooks.
 * Account-level webhooks are configured independently for US and EU regions.
 * When triggered, webhook URLs are deduplicated by event type across account and domain levels.
 * Note: Webhook changes can take up to 10 minutes to become effective due to caching.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/account-webhooks/get-v1-webhooks.md">List account-level webhooks</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/account-webhooks/post-v1-webhooks.md">Create an account-level webhook</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/account-webhooks/put-v1-webhooks--webhook-id-.md">Update an account-level webhook</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/account-webhooks/delete-v1-webhooks.md">Delete account-level webhooks</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/account-webhooks/get-v1-webhooks--webhook-id-.md">Get account-level webhook by ID</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/account-webhooks/delete-v1-webhooks--webhook-id-.md">Delete account-level webhook by ID</a>
 */
@Headers("Accept: application/json")
public interface MailgunAccountWebhooksApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_1;
    }

    /**
     * Retrieve all account-level webhooks.
     *
     * @return {@link AccountWebhookListResult}
     */
    @RequestLine("GET /webhooks")
    AccountWebhookListResult getAllWebhooks();

    /**
     * Retrieve all account-level webhooks (raw response).
     *
     * @return {@link Response}
     */
    @RequestLine("GET /webhooks")
    Response getAllWebhooksFeignResponse();

    /**
     * Retrieve account-level webhooks filtered by specific webhook IDs.
     *
     * @param query {@link AccountWebhooksListQuery} optional filter by comma-separated webhook IDs
     * @return {@link AccountWebhookListResult}
     */
    @RequestLine("GET /webhooks")
    AccountWebhookListResult getAllWebhooks(@QueryMap AccountWebhooksListQuery query);

    /**
     * Retrieve account-level webhooks filtered by specific webhook IDs (raw response).
     *
     * @param query {@link AccountWebhooksListQuery} optional filter by comma-separated webhook IDs
     * @return {@link Response}
     */
    @RequestLine("GET /webhooks")
    Response getAllWebhooksFeignResponse(@QueryMap AccountWebhooksListQuery query);

    /**
     * Retrieve a specific account-level webhook by its ID.
     *
     * @param webhookId the webhook ID to retrieve
     * @return {@link AccountWebhook}
     */
    @RequestLine("GET /webhooks/{webhook_id}")
    AccountWebhook getWebhook(@Param("webhook_id") String webhookId);

    /**
     * Retrieve a specific account-level webhook by its ID (raw response).
     *
     * @param webhookId the webhook ID to retrieve
     * @return {@link Response}
     */
    @RequestLine("GET /webhooks/{webhook_id}")
    Response getWebhookFeignResponse(@Param("webhook_id") String webhookId);

    /**
     * Create an account-level webhook.
     *
     * @param request {@link AccountWebhookRequest} with url, event_types, and optional description
     * @return {@link AccountWebhookCreatedResult} containing the new webhook ID
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /webhooks")
    AccountWebhookCreatedResult createWebhook(AccountWebhookRequest request);

    /**
     * Create an account-level webhook (raw response).
     *
     * @param request {@link AccountWebhookRequest} with url, event_types, and optional description
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /webhooks")
    Response createWebhookFeignResponse(AccountWebhookRequest request);

    /**
     * Update an existing account-level webhook by replacing its URL, description, and event types.
     *
     * @param webhookId the webhook ID to update
     * @param request   {@link AccountWebhookRequest} with the replacement configuration
     * @return {@link AccountWebhookCreatedResult} containing the webhook ID
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /webhooks/{webhook_id}")
    AccountWebhookCreatedResult updateWebhook(@Param("webhook_id") String webhookId, AccountWebhookRequest request);

    /**
     * Update an existing account-level webhook (raw response).
     *
     * @param webhookId the webhook ID to update
     * @param request   {@link AccountWebhookRequest} with the replacement configuration
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /webhooks/{webhook_id}")
    Response updateWebhookFeignResponse(@Param("webhook_id") String webhookId, AccountWebhookRequest request);

    /**
     * Delete account-level webhooks. Either set {@code all=true} to remove all webhooks,
     * or provide {@code webhook_ids} to delete specific ones.
     *
     * @param query {@link AccountWebhooksDeleteQuery} specifying which webhooks to delete
     */
    @RequestLine("DELETE /webhooks")
    void deleteWebhooks(@QueryMap AccountWebhooksDeleteQuery query);

    /**
     * Delete account-level webhooks (raw response).
     *
     * @param query {@link AccountWebhooksDeleteQuery} specifying which webhooks to delete
     * @return {@link Response}
     */
    @RequestLine("DELETE /webhooks")
    Response deleteWebhooksFeignResponse(@QueryMap AccountWebhooksDeleteQuery query);

    /**
     * Delete a specific account-level webhook by its ID.
     *
     * @param webhookId the webhook ID to delete
     */
    @RequestLine("DELETE /webhooks/{webhook_id}")
    void deleteWebhook(@Param("webhook_id") String webhookId);

    /**
     * Delete a specific account-level webhook by its ID (raw response).
     *
     * @param webhookId the webhook ID to delete
     * @return {@link Response}
     */
    @RequestLine("DELETE /webhooks/{webhook_id}")
    Response deleteWebhookFeignResponse(@Param("webhook_id") String webhookId);

}
