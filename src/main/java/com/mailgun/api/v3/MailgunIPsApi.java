package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.ips.IPResult;
import com.mailgun.model.ips.IpDomainsOperationResponse;
import com.mailgun.model.ips.IpDomainsQuery;
import com.mailgun.model.ips.IpDomainsResult;
import com.mailgun.model.ips.IPsResult;
import com.mailgun.model.ips.IpsDetailsAllQuery;
import com.mailgun.model.ips.IpsDetailsAllResult;
import com.mailgun.model.ips.IpsListQuery;
import com.mailgun.model.ips.PlaceIpInBandRequest;
import com.mailgun.model.ips.RemoveIpFromAllDomainsQuery;
import com.mailgun.model.ips.RequestNewIpAvailabilityResponse;
import com.mailgun.model.ippools.DomainIpRemovalQuery;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * <p>
 * The IP API endpoint allows you to access information regarding the IPs allocated to your Mailgun account
 * that are used for outbound sending.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips">IPs</a>
 * @see com.mailgun.api.v3.MailgunIpPoolsApi Dedicated IP pools (DIPPs)
 */
@Headers("Accept: application/json")
public interface MailgunIPsApi extends MailgunApi {

    /**
     * Lists account IPs with optional filters.
     *
     * @param query optional {@code dedicated} and {@code enabled} filters
     * @return {@link IPsResult} includes {@code assignable_to_pools} when DIPPs are enabled
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/get-v3-ips">List account IPs</a>
     */
    @RequestLine("GET /ips")
    IPsResult getAllIPs(@QueryMap IpsListQuery query);

    @RequestLine("GET /ips")
    Response getAllIPsFeignResponse(@QueryMap IpsListQuery query);

    /**
     * Lists all account IPs.
     *
     * @return {@link IPsResult}
     */
    @RequestLine("GET /ips")
    IPsResult getAllIPs();

    @RequestLine("GET /ips")
    Response getAllIPsFeignResponse();

    /**
     * Return only dedicated IPs if <code>dedicated</code> is <code>true</code>, otherwise return all IPs.
     *
     * @param dedicated dedicated IPs filter
     * @return {@link IPsResult}
     */
    @RequestLine("GET /ips?dedicated={dedicated}")
    IPsResult getDedicatedIPs(@Param("dedicated") boolean dedicated);

    @RequestLine("GET /ips?dedicated={dedicated}")
    Response getDedicatedIPsFeignResponse(@Param("dedicated") boolean dedicated);

    /**
     * <p>
     * Returns information about the specified IP.
     * </p>
     *
     * @param ip specified IP
     * @return {@link IPResult}
     */
    @RequestLine("GET /ips/{ip}")
    IPResult getSpecifiedIP(@Param("ip") String ip);

    /**
     * <p>
     * Returns information about the specified IP.
     * </p>
     *
     * @param ip specified IP
     * @return {@link Response}
     */
    @RequestLine("GET /ips/{ip}")
    Response getSpecifiedIPFeignResponse(@Param("ip") String ip);

    /**
     * <p>
     * Returns a list of IPs currently assigned to the specified domain.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link IPsResult}
     */
    @RequestLine("GET /domains/{domain}/ips")
    IPsResult getDomainIPs(@Param("domain") String domain);

    /**
     * <p>
     * Returns a list of IPs currently assigned to the specified domain.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link Response}
     */
    @RequestLine("GET /domains/{domain}/ips")
    Response getDomainIPsFeignResponse(@Param("domain") String domain);

    /**
     * <p>
     * Assign a dedicated IP to the domain specified.
     * </p>
     * <p>
     * Note: Only dedicated IPs can be assigned to a domain.
     * </p>
     *
     * @param domain Name of the domain
     * @param ip     IP address that should be assigned to the domain pool.
     * @return {@link ResponseWithMessage}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /domains/{domain}/ips")
    ResponseWithMessage assignIPToDomain(@Param("domain") String domain, @Param("ip") String ip);

    /**
     * <p>
     * Assign a dedicated IP to the domain specified.
     * </p>
     * <p>
     * Note: Only dedicated IPs can be assigned to a domain.
     * </p>
     *
     * @param domain Name of the domain
     * @param ip     IP address that should be assigned to the domain pool.
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /domains/{domain}/ips")
    Response assignIPToDomainFeignResponse(@Param("domain") String domain, @Param("ip") String ip);

    /**
     * <p>
     * Remove an IP from the domain pool, unlink a DIPP, or remove the entire domain pool.
     * </p>
     * <p>
     * The path {@code ip} may be a valid IP address, {@link com.mailgun.model.ippools.IpPoolConstants#DOMAIN_POOL_REMOVE_ALL all},
     * or {@link com.mailgun.model.ippools.IpPoolConstants#DOMAIN_POOL_UNLINK_IP_POOL ip_pool} to unlink the linked DIPP.
     * When unlinking, specify replacement via {@code query} ({@code ip} or {@code pool_id}, not both).
     * </p>
     *
     * @param domain Name of the domain
     * @param ip     IP to remove, {@code all}, or {@code ip_pool}
     * @param query  optional replacement when unlinking a DIPP
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/delete-v3-domains--name--ips--ip-">Remove domain pool IP / unlink DIPP</a>
     */
    @RequestLine("DELETE /domains/{domain}/ips/{ip}")
    ResponseWithMessage unassignIPFromDomain(@Param("domain") String domain, @Param("ip") String ip,
                                             @QueryMap DomainIpRemovalQuery query);

    @RequestLine("DELETE /domains/{domain}/ips/{ip}")
    Response unassignIPFromDomainFeignResponse(@Param("domain") String domain, @Param("ip") String ip,
                                               @QueryMap DomainIpRemovalQuery query);

    /**
     * <p>
     * Remove an IP from the domain pool, unlink a DIPP, or remove the entire domain pool.
     * </p>
     *
     * @param domain Name of the domain
     * @param ip     IP to remove, {@code all}, or {@code ip_pool}
     * @return {@link ResponseWithMessage}
     * @see #unassignIPFromDomain(String, String, DomainIpRemovalQuery)
     */
    @RequestLine("DELETE /domains/{domain}/ips/{ip}")
    ResponseWithMessage unassignIPFromDomain(@Param("domain") String domain, @Param("ip") String ip);

    @RequestLine("DELETE /domains/{domain}/ips/{ip}")
    Response unassignIPFromDomainFeignResponse(@Param("domain") String domain, @Param("ip") String ip);

    /**
     * Same as {@link #unassignIPFromDomain(String, String, DomainIpRemovalQuery)} via {@code DELETE /domains/{domain}/pool/{ip}}.
     *
     * @param domain Name of the domain
     * @param ip     IP to remove, {@code all}, or {@code ip_pool}
     * @param query  optional replacement when unlinking a DIPP
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/delete-v3-domains--name--pool--ip-">Remove domain pool IP (pool path)</a>
     */
    @RequestLine("DELETE /domains/{domain}/pool/{ip}")
    ResponseWithMessage unassignIPFromDomainPool(@Param("domain") String domain, @Param("ip") String ip,
                                                 @QueryMap DomainIpRemovalQuery query);

    @RequestLine("DELETE /domains/{domain}/pool/{ip}")
    Response unassignIPFromDomainPoolFeignResponse(@Param("domain") String domain, @Param("ip") String ip,
                                                   @QueryMap DomainIpRemovalQuery query);

    /**
     * @see #unassignIPFromDomainPool(String, String, DomainIpRemovalQuery)
     */
    @RequestLine("DELETE /domains/{domain}/pool/{ip}")
    ResponseWithMessage unassignIPFromDomainPool(@Param("domain") String domain, @Param("ip") String ip);

    @RequestLine("DELETE /domains/{domain}/pool/{ip}")
    Response unassignIPFromDomainPoolFeignResponse(@Param("domain") String domain, @Param("ip") String ip);

    /**
     * Lists domains where the given IP is assigned.
     *
     * @param ip    account IP address
     * @param query pagination and search
     * @return {@link IpDomainsResult}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/get-v3-ips--ip--domains">Get IP domains</a>
     */
    @RequestLine("GET /ips/{ip}/domains")
    IpDomainsResult getIpDomains(@Param("ip") String ip, @QueryMap IpDomainsQuery query);

    @RequestLine("GET /ips/{ip}/domains")
    Response getIpDomainsFeignResponse(@Param("ip") String ip, @QueryMap IpDomainsQuery query);

    /**
     * Assigns an IP to all account domains.
     *
     * @param ip account IP address
     * @return {@link IpDomainsOperationResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/post-v3-ips--ip--domains">Assign IP to all domains</a>
     */
    @RequestLine("POST /ips/{ip}/domains")
    IpDomainsOperationResponse assignIpToAllDomains(@Param("ip") String ip);

    @RequestLine("POST /ips/{ip}/domains")
    Response assignIpToAllDomainsFeignResponse(@Param("ip") String ip);

    /**
     * Removes an IP from all account domains.
     *
     * @param ip    account IP address
     * @param query optional {@code alternative} replacement IP
     * @return {@link IpDomainsOperationResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/delete-v3-ips--ip--domains">Remove IP from all domains</a>
     */
    @RequestLine("DELETE /ips/{ip}/domains")
    IpDomainsOperationResponse removeIpFromAllDomains(@Param("ip") String ip, @QueryMap RemoveIpFromAllDomainsQuery query);

    @RequestLine("DELETE /ips/{ip}/domains")
    Response removeIpFromAllDomainsFeignResponse(@Param("ip") String ip, @QueryMap RemoveIpFromAllDomainsQuery query);

    @RequestLine("DELETE /ips/{ip}/domains")
    IpDomainsOperationResponse removeIpFromAllDomains(@Param("ip") String ip);

    @RequestLine("DELETE /ips/{ip}/domains")
    Response removeIpFromAllDomainsFeignResponse(@Param("ip") String ip);

    /**
     * Places a dedicated IP into a dedicated IP band.
     *
     * @param addr    IP address
     * @param request target band name
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/post-v3-ips--addr--ip-band">Place IP into IP band</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /ips/{addr}/ip_band")
    ResponseWithMessage placeIpInBand(@Param("addr") String addr, PlaceIpInBandRequest request);

    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /ips/{addr}/ip_band")
    Response placeIpInBandFeignResponse(@Param("addr") String addr, PlaceIpInBandRequest request);

    /**
     * Returns how many new dedicated IPs the billing plan allows (deprecated).
     *
     * @return {@link RequestNewIpAvailabilityResponse}
     * @deprecated Use billing/account APIs instead; kept for backwards compatibility.
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/get-v3-ips-request-new">Available IPs per plan</a>
     */
    @Deprecated
    @RequestLine("GET /ips/request/new")
    RequestNewIpAvailabilityResponse getNewIpAvailability();

    @Deprecated
    @RequestLine("GET /ips/request/new")
    Response getNewIpAvailabilityFeignResponse();

    /**
     * Requests a new dedicated IP for the account.
     *
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/post-v3-ips-request-new">Add new dedicated IP</a>
     */
    @RequestLine("POST /ips/request/new")
    ResponseWithMessage requestNewDedicatedIp();

    @RequestLine("POST /ips/request/new")
    Response requestNewDedicatedIpFeignResponse();

    /**
     * Lists IPs for the account and subaccounts (detailed view feature required).
     *
     * @param query filters, pagination, and sort
     * @return {@link IpsDetailsAllResult}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/get-v3-ips-details-all">List IPs detailed view</a>
     */
    @RequestLine("GET /ips/details/all")
    IpsDetailsAllResult getAllIpsDetails(@QueryMap IpsDetailsAllQuery query);

    @RequestLine("GET /ips/details/all")
    Response getAllIpsDetailsFeignResponse(@QueryMap IpsDetailsAllQuery query);

    @RequestLine("GET /ips/details/all")
    IpsDetailsAllResult getAllIpsDetails();

    @RequestLine("GET /ips/details/all")
    Response getAllIpsDetailsFeignResponse();

}
