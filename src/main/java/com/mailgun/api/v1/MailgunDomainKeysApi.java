package com.mailgun.api.v1;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.domainkeys.CreateDomainKeyRequest;
import com.mailgun.model.domainkeys.CreateDomainKeyResponse;
import com.mailgun.model.domainkeys.DomainKeysListQuery;
import com.mailgun.model.domainkeys.DomainKeysListResponse;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * Domain Keys (DKIM) API (v1): list keys for all domains, create, and delete. Paginated (limit default 10, max 100).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/get-v1-dkim-keys">List keys for all domains</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/post-v1-dkim-keys">Create a domain key</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/delete-v1-dkim-keys">Delete a domain key</a>
 */
@Headers("Accept: application/json")
public interface MailgunDomainKeysApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_1;
    }

    /**
     * List domain keys for all domains. Paginated (default limit 10, max 100).
     *
     * @return {@link DomainKeysListResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/get-v1-dkim-keys">List keys for all domains</a>
     */
    @RequestLine("GET /dkim/keys")
    DomainKeysListResponse listDomainKeys();

    /**
     * List domain keys with optional filter and pagination (page, limit, signing_domain, selector).
     *
     * @param query optional page, limit (default 10, max 100), signing_domain, selector
     * @return {@link DomainKeysListResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/get-v1-dkim-keys">List keys for all domains</a>
     */
    @RequestLine("GET /dkim/keys")
    DomainKeysListResponse listDomainKeys(@QueryMap DomainKeysListQuery query);

    /**
     * List domain keys (raw response).
     *
     * @return {@link Response}
     */
    @RequestLine("GET /dkim/keys")
    Response listDomainKeysFeignResponse();

    /**
     * List domain keys with query (raw response).
     *
     * @param query optional query params
     * @return {@link Response}
     */
    @RequestLine("GET /dkim/keys")
    Response listDomainKeysFeignResponse(@QueryMap DomainKeysListQuery query);

    /**
     * Create a domain key. Private keys are never exported after creation or import.
     *
     * @param request signing_domain, selector, optional bits (1024/2048), optional pem (file or string)
     * @return {@link CreateDomainKeyResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/post-v1-dkim-keys">Create a domain key</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /dkim/keys")
    CreateDomainKeyResponse createDomainKey(CreateDomainKeyRequest request);

    /**
     * Create a domain key (raw response).
     *
     * @param request create request
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /dkim/keys")
    Response createDomainKeyFeignResponse(CreateDomainKeyRequest request);

    /**
     * Delete a domain key. Keys are not recoverable after deletion.
     *
     * @param signingDomain signing domain (query param)
     * @param selector      selector (query param)
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/delete-v1-dkim-keys">Delete a domain key</a>
     */
    @RequestLine("DELETE /dkim/keys?signing_domain={signing_domain}&selector={selector}")
    ResponseWithMessage deleteDomainKey(@Param("signing_domain") String signingDomain, @Param("selector") String selector);

    /**
     * Delete a domain key (raw response).
     *
     * @param signingDomain signing domain
     * @param selector      selector
     * @return {@link Response}
     */
    @RequestLine("DELETE /dkim/keys?signing_domain={signing_domain}&selector={selector}")
    Response deleteDomainKeyFeignResponse(@Param("signing_domain") String signingDomain, @Param("selector") String selector);
}
