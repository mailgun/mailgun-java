package com.mailgun.api.v4;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.webhooks.DomainWebhooksV4Request;
import com.mailgun.model.webhooks.WebhookListResult;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

/**
 * Domain Webhooks API (v4): create, update, and delete domain-level webhooks
 * with support for associating one URL with multiple event types in a single operation.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-webhooks/post-v4-domains--domain--webhooks.md">Create domain webhooks (v4)</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-webhooks/put-v4-domains--domain--webhooks.md">Update domain webhooks (v4)</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-webhooks/delete-v4-domains--domain--webhooks.md">Delete domain webhooks (v4)</a>
 */
@Headers("Accept: application/json")
public interface MailgunWebhooksApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_4;
    }

    /**
     * Create webhook URLs for multiple event types in a single operation.
     * Associates one URL with one or more webhook event types.
     *
     * @param domain  domain name
     * @param request {@link DomainWebhooksV4Request} with url and event_types
     * @return {@link WebhookListResult} updated full webhooks map for the domain
     */
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @RequestLine("POST /domains/{domain}/webhooks")
    WebhookListResult createWebhooks(@Param("domain") String domain, DomainWebhooksV4Request request);

    /**
     * Create webhook URLs for multiple event types (raw response).
     *
     * @param domain  domain name
     * @param request {@link DomainWebhooksV4Request} with url and event_types
     * @return {@link Response}
     */
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @RequestLine("POST /domains/{domain}/webhooks")
    Response createWebhooksFeignResponse(@Param("domain") String domain, DomainWebhooksV4Request request);

    /**
     * Update webhook URL to associate it with different event types.
     * Replaces the existing event type associations for the given URL.
     *
     * @param domain  domain name
     * @param request {@link DomainWebhooksV4Request} with url and replacement event_types
     * @return {@link WebhookListResult} updated full webhooks map for the domain
     */
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @RequestLine("PUT /domains/{domain}/webhooks")
    WebhookListResult updateWebhooks(@Param("domain") String domain, DomainWebhooksV4Request request);

    /**
     * Update webhook URL event type associations (raw response).
     *
     * @param domain  domain name
     * @param request {@link DomainWebhooksV4Request} with url and replacement event_types
     * @return {@link Response}
     */
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @RequestLine("PUT /domains/{domain}/webhooks")
    Response updateWebhooksFeignResponse(@Param("domain") String domain, DomainWebhooksV4Request request);

    /**
     * Delete webhook URLs from all event types they are associated with.
     * Supports deleting multiple URLs at once via comma-separated values.
     *
     * @param domain domain name
     * @param urls   comma-separated webhook URL(s) to delete
     * @return {@link WebhookListResult} updated full webhooks map for the domain
     */
    @RequestLine("DELETE /domains/{domain}/webhooks?url={url}")
    WebhookListResult deleteWebhooks(@Param("domain") String domain, @Param("url") String urls);

    /**
     * Delete webhook URLs from all event types (raw response).
     *
     * @param domain domain name
     * @param urls   comma-separated webhook URL(s) to delete
     * @return {@link Response}
     */
    @RequestLine("DELETE /domains/{domain}/webhooks?url={url}")
    Response deleteWebhooksFeignResponse(@Param("domain") String domain, @Param("url") String urls);

}
