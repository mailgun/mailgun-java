package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.YesNo;
import com.mailgun.enums.YesNoHtml;
import com.mailgun.expanders.EnumExpander;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.domains.DomainConnectionRequest;
import com.mailgun.model.domains.DomainConnectionResponse;
import com.mailgun.model.domains.DomainCredentials;
import com.mailgun.model.domains.DomainListResponse;
import com.mailgun.model.domains.DomainRequest;
import com.mailgun.model.domains.DomainResponse;
import com.mailgun.model.domains.DomainTrackingResponse;
import com.mailgun.model.domains.DomainUnsubscribeConnectionSettingsRequest;
import com.mailgun.model.domains.OpenTrackingSettingsRequest;
import com.mailgun.model.domains.DomainsParametersFilter;
import com.mailgun.model.domains.SingleDomainResponse;
import com.mailgun.model.domains.UpdateDomainClickTrackingSettingsResponse;
import com.mailgun.model.domains.UpdateDomainConnectionResponse;
import com.mailgun.model.domains.UpdateDomainOpenTrackingSettingsResponse;
import com.mailgun.model.domains.UpdateDomainUnsubscribeTrackingSettingsResponse;
import com.mailgun.model.domainkeys.DkimAuthorityRequest;
import com.mailgun.model.domainkeys.DkimAuthorityResponse;
import com.mailgun.model.domainkeys.DkimSelectorRequest;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * <p>
 * Domains API (v3): delete domain, credentials, connection, tracking, and DKIM authority/selector.
 * For listing, creating, getting, updating, and verifying domains use the v4 API:
 * {@link com.mailgun.api.v4.MailgunDomainsApi} (GET/POST/PUT /v4/domains). Delete domain remains on v3.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domains/delete-v3-domains--name-">Delete a domain</a>
 * @see <a href="https://documentation.mailgun.com/en/latest/api-domains.html">Domains</a>
 */
@Headers("Accept: application/json")
public interface MailgunDomainsApi extends MailgunApi {

    /**
     * <p>
     * Returns a list of domains under your account. Prefer {@link com.mailgun.api.v4.MailgunDomainsApi#getDomainsList()}
     * for v4 (paginated, max 1000 per page, filter by state/authority, sort, search).
     * </p>
     *
     * @return {@link DomainListResponse}
     */
    @RequestLine("GET /domains")
    DomainListResponse getDomainsList();

    /**
     * <p>
     * Returns a list of domains under your account (with filter). Prefer {@link com.mailgun.api.v4.MailgunDomainsApi#getDomainsList(DomainsParametersFilter)} for v4.
     * </p>
     *
     * @param filter {@link DomainsParametersFilter}
     * @return {@link DomainListResponse}
     */
    @RequestLine("GET /domains")
    DomainListResponse getDomainsList(@QueryMap DomainsParametersFilter filter);

    /**
     * <p>
     * Returns a list of domains (raw response).
     * </p>
     *
     * @return {@link Response}
     */
    @RequestLine("GET /domains")
    Response getDomainsListFeignResponse();

    /**
     * <p>
     * Returns a list of domains with filter (raw response).
     * </p>
     *
     * @param filter {@link DomainsParametersFilter}
     * @return {@link Response}
     */
    @RequestLine("GET /domains")
    Response getDomainsListFeignResponse(@QueryMap DomainsParametersFilter filter);

    /**
     * <p>
     * Returns a single domain, including credentials and DNS records.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link SingleDomainResponse}
     */
    @RequestLine("GET /domains/{domain}")
    SingleDomainResponse getSingleDomain(@Param("domain") String domain);

    /**
     * <p>
     * Returns a single domain, including credentials and DNS records.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link Response}
     */
    @RequestLine("GET /domains/{domain}")
    Response getSingleDomainFeignResponse(@Param("domain") String domain);

    /**
     * <p>
     * Verifies and returns a single domain, including credentials and DNS records.
     * If the domain is successfully verified the domain’s state will be ‘active’.
     * For more information on verifying domains, visit: @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#verifying-your-domain">Verifying Your Domain</a>
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link DomainResponse}
     */
    @RequestLine("PUT /domains/{domain}/verify")
    DomainResponse verifyDomain(@Param("domain") String domain);

    /**
     * <p>
     * Verifies and returns a single domain, including credentials and DNS records.
     * If the domain is successfully verified the domain’s state will be ‘active’.
     * For more information on verifying domains, visit: @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#verifying-your-domain">Verifying Your Domain</a>
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link Response}
     */
    @RequestLine("PUT /domains/{domain}/verify")
    Response verifyDomainFeignResponse(@Param("domain") String domain);

    /**
     * <p>
     * Create a new domain.
     * </p>
     *
     * @param request {@link DomainRequest}
     * @return {@link DomainResponse}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /domains")
    DomainResponse createNewDomain(DomainRequest request);

    /**
     * <p>
     * Create a new domain.
     * </p>
     *
     * @param request {@link DomainRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /domains")
    Response createNewDomainFeignResponse(DomainRequest request);

    /**
     * <p>
     * Delete a domain. Domain must not be disabled or used as authority for another domain. Sandbox domain cannot be deleted. (DELETE /v3/domains/{name})
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domains/delete-v3-domains--name-">Delete a domain</a>
     */
    @RequestLine("DELETE /domains/{domain}")
    ResponseWithMessage deleteDomain(@Param("domain") String domain);

    /**
     * <p>
     * Delete a domain (raw response).
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link Response}
     */
    @RequestLine("DELETE /domains/{domain}")
    Response deleteDomainFeignResponse(@Param("domain") String domain);

    /**
     * <p>
     * Creates a new set of SMTP credentials for the defined domain.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link DomainCredentials}
     * @return {@link ResponseWithMessage}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /domains/{domain}/credentials")
    ResponseWithMessage createNewCredentials(@Param("domain") String domain, DomainCredentials request);

    /**
     * <p>
     * Creates a new set of SMTP credentials for the defined domain.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link DomainCredentials}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /domains/{domain}/credentials")
    Response createNewCredentialsFeignResponse(@Param("domain") String domain, DomainCredentials request);

    /**
     * <p>
     * Updates the specified SMTP credentials.
     * Currently only the password can be changed.
     * </p>
     *
     * @param domain   Name of the domain
     * @param login    Login
     * @param password A password for the SMTP credentials. (Length Min 5, Max 32)
     * @return {@link ResponseWithMessage}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{domain}/credentials/{login}")
    ResponseWithMessage updateCredentials(@Param("domain") String domain, @Param("login") String login, @Param("password") String password);

    /**
     * <p>
     * Updates the specified SMTP credentials.
     * Currently only the password can be changed.
     * </p>
     *
     * @param domain   Name of the domain
     * @param login    Login
     * @param password A password for the SMTP credentials. (Length Min 5, Max 32)
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{domain}/credentials/{login}")
    Response updateCredentialsFeignResponse(@Param("domain") String domain, @Param("login") String login, @Param("password") String password);

    /**
     * <p>
     * Deletes the defined SMTP credentials.
     * </p>
     *
     * @param domain Name of the domain
     * @param login  Login
     * @return {@link ResponseWithMessage}
     */
    @RequestLine("DELETE /domains/{domain}/credentials/{login}")
    ResponseWithMessage deleteCredentials(@Param("domain") String domain, @Param("login") String login);

    /**
     * <p>
     * Deletes the defined SMTP credentials.
     * </p>
     *
     * @param domain Name of the domain
     * @param login  Login
     * @return {@link Response}
     */
    @RequestLine("DELETE /domains/{domain}/credentials/{login}")
    Response deleteCredentialsFeignResponse(@Param("domain") String domain, @Param("login") String login);

    /**
     * <p>
     * Returns delivery connection settings for the defined domain.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link DomainConnectionResponse}
     */
    @RequestLine("GET /domains/{domain}/connection")
    DomainConnectionResponse getDomainConnectionSettings(@Param("domain") String domain);

    /**
     * <p>
     * Returns delivery connection settings for the defined domain.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link Response}
     */
    @RequestLine("GET /domains/{domain}/connection")
    Response getDomainConnectionSettingsFeignResponse(@Param("domain") String domain);

    /**
     * <p>
     * Updates the specified delivery connection settings for the defined domain.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link DomainConnectionRequest}
     * @return {@link UpdateDomainConnectionResponse}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{domain}/connection")
    UpdateDomainConnectionResponse updateDomainConnectionSettings(@Param("domain") String domain, DomainConnectionRequest request);

    /**
     * <p>
     * Updates the specified delivery connection settings for the defined domain.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link DomainConnectionRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{domain}/connection")
    Response updateDomainConnectionSettingsFeignResponse(@Param("domain") String domain, DomainConnectionRequest request);

    /**
     * Get tracking settings (open, click, unsubscribe, web_scheme) for a domain.
     *
     * @param domain Name of the domain
     * @return {@link DomainTrackingResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/get-v3-domains--name--tracking">Get tracking settings</a>
     */
    @RequestLine("GET /domains/{domain}/tracking")
    DomainTrackingResponse getDomainTrackingSettings(@Param("domain") String domain);

    /**
     * Get tracking settings (raw response).
     *
     * @param domain Name of the domain
     * @return {@link Response}
     */
    @RequestLine("GET /domains/{domain}/tracking")
    Response getDomainTrackingSettingsFeignResponse(@Param("domain") String domain);

    /**
     * Update open tracking settings (active and/or place_at_the_top). Omit fields in request to keep current settings.
     *
     * @param domain  Name of the domain
     * @param request {@link OpenTrackingSettingsRequest}
     * @return {@link UpdateDomainOpenTrackingSettingsResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/put-v3-domains--name--tracking-open">Update open tracking settings</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{domain}/tracking/open")
    UpdateDomainOpenTrackingSettingsResponse updateDomainOpenTrackingSettings(@Param("domain") String domain, OpenTrackingSettingsRequest request);

    /**
     * Update open tracking settings (active only). For active + place_at_the_top use {@link #updateDomainOpenTrackingSettings(String, OpenTrackingSettingsRequest)}.
     *
     * @param domain Name of the domain
     * @param active {@link YesNo}
     * @return {@link UpdateDomainOpenTrackingSettingsResponse}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{domain}/tracking/open")
    UpdateDomainOpenTrackingSettingsResponse updateDomainOpenTrackingSettings(@Param("domain") String domain,
                                                                              @Param(value = "active", expander = EnumExpander.class) YesNo active);

    /**
     * Update open tracking settings (raw response).
     *
     * @param domain Name of the domain
     * @param active {@link YesNo}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{domain}/tracking/open")
    Response updateDomainOpenTrackingSettingsFeignResponse(@Param("domain") String domain,
                                                           @Param(value = "active", expander = EnumExpander.class) YesNo active);

    /**
     * Update open tracking settings with request (raw response).
     *
     * @param domain  Name of the domain
     * @param request {@link OpenTrackingSettingsRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{domain}/tracking/open")
    Response updateDomainOpenTrackingSettingsFeignResponse(@Param("domain") String domain, OpenTrackingSettingsRequest request);

    /**
     * Update click tracking at domain level. Active values: yes, no, htmlonly.
     *
     * @param domain Name of the domain
     * @param active {@link YesNoHtml}
     * @return {@link UpdateDomainClickTrackingSettingsResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/put-v3-domains--name--tracking-click">Update click tracking settings</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{domain}/tracking/click")
    UpdateDomainClickTrackingSettingsResponse updateDomainClickTrackingSettings(@Param("domain") String domain,
                                                                                @Param(value = "active", expander = EnumExpander.class) YesNoHtml active);

    /**
     * Update click tracking (raw response).
     *
     * @param domain Name of the domain
     * @param active {@link YesNoHtml}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{domain}/tracking/click")
    Response updateDomainClickTrackingSettingsFeignResponse(@Param("domain") String domain,
                                                            @Param(value = "active", expander = EnumExpander.class) YesNoHtml active);

    /**
     * Update unsubscribe tracking (active, html_footer, text_footer).
     *
     * @param domain  Name of the domain
     * @param request {@link DomainUnsubscribeConnectionSettingsRequest}
     * @return {@link UpdateDomainUnsubscribeTrackingSettingsResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/put-v3-domains--name--tracking-unsubscribe">Update unsubscribe tracking settings</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{domain}/tracking/unsubscribe")
    UpdateDomainUnsubscribeTrackingSettingsResponse updateDomainUnsubscribeConnectionSettings(@Param("domain") String domain, DomainUnsubscribeConnectionSettingsRequest request);

    /**
     * Update unsubscribe tracking (raw response).
     *
     * @param domain  Name of the domain
     * @param request {@link DomainUnsubscribeConnectionSettingsRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{domain}/tracking/unsubscribe")
    Response updateDomainUnsubscribeConnectionSettingsFeignResponse(@Param("domain") String domain, DomainUnsubscribeConnectionSettingsRequest request);

    /**
     * <p>
     * Update DKIM authority: delegate domain authority to another domain or set domain as its own authority (default).
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link DkimAuthorityRequest} (self: true = domain is authority for itself, false = use root domain authority)
     * @return {@link DkimAuthorityResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/put-v3-domains--name--dkim-authority">Update DKIM authority</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{domain}/dkim_authority")
    DkimAuthorityResponse updateDkimAuthority(@Param("domain") String domain, DkimAuthorityRequest request);

    /**
     * <p>
     * Update DKIM authority (raw response).
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link DkimAuthorityRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{domain}/dkim_authority")
    Response updateDkimAuthorityFeignResponse(@Param("domain") String domain, DkimAuthorityRequest request);

    /**
     * <p>
     * Update DKIM selector for the domain. Selector must be unique among keys.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link DkimSelectorRequest} (dkim_selector; omit to leave unchanged)
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/put-v3-domains--name--dkim-selector">Update a DKIM selector</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{domain}/dkim_selector")
    ResponseWithMessage updateDkimSelector(@Param("domain") String domain, DkimSelectorRequest request);

    /**
     * <p>
     * Update DKIM selector (raw response).
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link DkimSelectorRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /domains/{domain}/dkim_selector")
    Response updateDkimSelectorFeignResponse(@Param("domain") String domain, DkimSelectorRequest request);

}
