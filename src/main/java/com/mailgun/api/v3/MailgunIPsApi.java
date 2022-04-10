package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.ips.IPResult;
import com.mailgun.model.ips.IPsResult;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

/**
 * <p>
 * The IP API endpoint allows you to access information regarding the IPs allocated to your Mailgun account
 * that are used for outbound sending.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-ips.html">IPs</a>
 */
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
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
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
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("POST /domains/{domain}/ips")
    Response assignIPToDomainFeignResponse(@Param("domain") String domain, @Param("ip") String ip);

    /**
     * <p>
     * Unassign an IP from the domain specified.
     * </p>
     *
     * @param domain Name of the domain
     * @param ip     IP address that should be unassign.
     * @return {@link ResponseWithMessage}
     */
    @RequestLine("DELETE /domains/{domain}/ips/{ip}")
    ResponseWithMessage unassignIPFromDomain(@Param("domain") String domain, @Param("ip") String ip);

    /**
     * <p>
     * Unassign an IP from the domain specified.
     * </p>
     *
     * @param domain Name of the domain
     * @param ip     IP address that should be unassign.
     * @return {@link Response}
     */
    @RequestLine("DELETE /domains/{domain}/ips/{ip}")
    Response unassignIPFromDomainFeignResponse(@Param("domain") String domain, @Param("ip") String ip);

}
