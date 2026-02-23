package com.mailgun.api.v4;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.domains.DomainListResponse;
import com.mailgun.model.domains.DomainRequest;
import com.mailgun.model.domains.DomainResponse;
import com.mailgun.model.domains.DomainUpdateRequest;
import com.mailgun.model.domains.DomainsParametersFilter;
import com.mailgun.model.domains.SingleDomainResponse;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * <p>
 * Domains API (v4): list, create, get details, update, and verify domains. List can be filtered by state or authority,
 * optionally sorted, and is paginated (max 1000 items per page).
 * </p>
 * <p>
 * To delete a domain use the v3 API: {@link com.mailgun.api.v3.MailgunDomainsApi#deleteDomain(String)} (DELETE /v3/domains/{name}).
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domains/get-v4-domains">Get domains</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domains/post-v4-domains">Create a domain</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domains/get-v4-domains--name-">Get domain details</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domains/put-v4-domains--name-">Update domain</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domains/put-v4-domains--name--verify">Verify domain</a>
 */
@Headers("Accept: application/json")
public interface MailgunDomainsApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_4;
    }

    /**
     * Get the list of domains. Paginated, max 1000 items per page.
     *
     * @return {@link DomainListResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domains/get-v4-domains">Get domains</a>
     */
    @RequestLine("GET /domains")
    DomainListResponse getDomainsList();

    /**
     * Get the list of domains with optional filter (state, authority, limit, skip, sort, search, include_subaccounts).
     *
     * @param filter optional filter and pagination
     * @return {@link DomainListResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domains/get-v4-domains">Get domains</a>
     */
    @RequestLine("GET /domains")
    DomainListResponse getDomainsList(@QueryMap DomainsParametersFilter filter);

    /**
     * Get the list of domains (raw response).
     *
     * @return {@link Response}
     */
    @RequestLine("GET /domains")
    Response getDomainsListFeignResponse();

    /**
     * Get the list of domains with optional filter (raw response).
     *
     * @param filter optional filter and pagination
     * @return {@link Response}
     */
    @RequestLine("GET /domains")
    Response getDomainsListFeignResponse(@QueryMap DomainsParametersFilter filter);

    /**
     * Create a domain for sending emails.
     *
     * @param request domain creation parameters
     * @return {@link DomainResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domains/post-v4-domains">Create a domain</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /domains")
    DomainResponse createNewDomain(DomainRequest request);

    /**
     * Create a domain for sending emails (raw response).
     *
     * @param request domain creation parameters
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /domains")
    Response createNewDomainFeignResponse(DomainRequest request);

    /**
     * Get domain details (state and settings).
     *
     * @param name domain name
     * @return {@link SingleDomainResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domains/get-v4-domains--name-">Get domain details</a>
     */
    @RequestLine("GET /domains/{name}")
    SingleDomainResponse getSingleDomain(@Param("name") String name);

    /**
     * Get domain details (raw response).
     *
     * @param name domain name
     * @return {@link Response}
     */
    @RequestLine("GET /domains/{name}")
    Response getSingleDomainFeignResponse(@Param("name") String name);

    /**
     * Update domain configuration (SMTP credentials, sender security, spam action, wildcard, tracking web scheme, etc.).
     *
     * @param name    domain name
     * @param request update parameters
     * @return {@link DomainResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domains/put-v4-domains--name-">Update domain</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{name}")
    DomainResponse updateDomain(@Param("name") String name, DomainUpdateRequest request);

    /**
     * Update domain configuration (raw response).
     *
     * @param name    domain name
     * @param request update parameters
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{name}")
    Response updateDomainFeignResponse(@Param("name") String name, DomainUpdateRequest request);

    /**
     * Verify the domain's DNS records (A, CNAME, SPF, DKIM, MX). On success the domain state becomes 'active'.
     *
     * @param name domain name
     * @return {@link DomainResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domains/put-v4-domains--name--verify">Verify domain</a>
     */
    @RequestLine("PUT /domains/{name}/verify")
    DomainResponse verifyDomain(@Param("name") String name);

    /**
     * Verify the domain's DNS records (raw response).
     *
     * @param name domain name
     * @return {@link Response}
     */
    @RequestLine("PUT /domains/{name}/verify")
    Response verifyDomainFeignResponse(@Param("name") String name);
}
