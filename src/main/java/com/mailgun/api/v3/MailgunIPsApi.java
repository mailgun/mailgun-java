package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.ips.IPResult;
import com.mailgun.model.ips.IPsResult;
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
 * @see <a href="https://documentation.mailgun.com/en/latest/api-ips.html">IPs</a>
 * @see com.mailgun.api.v3.MailgunIpPoolsApi Dedicated IP pools (DIPPs)
 */
@Headers("Accept: application/json")
public interface MailgunIPsApi extends MailgunApi {

    /**
     * <p>
     * Returns a list of IPs assigned to your account.
     * </p>
     *
     * @return {@link IPsResult}
     */
    @RequestLine("GET /ips")
    IPsResult getAllIPs();

    /**
     * <p>
     * Returns a list of IPs assigned to your account.
     * </p>
     *
     * @return {@link Response}
     */
    @RequestLine("GET /ips")
    Response getAllIPsFeignResponse();

    /**
     * <p>
     * Return only dedicated IPs if <code>param</code> <code>dedicated</code> set to <code>true</code>, otherwise return all IPs.
     * </p>
     *
     * @param dedicated dedicated IPs
     * @return {@link IPsResult}
     */
    @RequestLine("GET /ips?dedicated={dedicated}")
    IPsResult getDedicatedIPs(@Param("dedicated") boolean dedicated);

    /**
     * <p>
     * Return only dedicated IPs if <code>param</code> <code>dedicated</code> set to <code>true</code>, otherwise return all IPs.
     * </p>
     *
     * @param dedicated dedicated IPs
     * @return {@link Response}
     */
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
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/delete-v3-domains--name--ips--ip-">Remove domain pool IP / unlink DIPP</a>
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
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/delete-v3-domains--name--pool--ip-">Remove domain pool IP (pool path)</a>
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

}
