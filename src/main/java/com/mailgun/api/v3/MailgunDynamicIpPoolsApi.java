package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.dynamicpools.AssignableDomainsQuery;
import com.mailgun.model.dynamicpools.AssignableDomainsResponse;
import com.mailgun.model.dynamicpools.DynamicPoolsListResponse;
import com.mailgun.model.dynamicpools.EnrollAllDomainsInDynamicPoolsQuery;
import com.mailgun.model.dynamicpools.EnrollDomainInDynamicPoolQuery;
import com.mailgun.model.dynamicpools.InitializeDynamicPoolsRequest;
import com.mailgun.model.dynamicpools.RemoveDomainFromDynamicPoolQuery;
import com.mailgun.model.dynamicpools.UpdateDynamicPoolRequest;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * Dynamic IP Pools API (v3).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools">Dynamic IP Pools</a>
 * @see com.mailgun.api.v1.MailgunDynamicIpPoolsApi v1 domain listing, history, and overrides
 */
@Headers("Accept: application/json")
public interface MailgunDynamicIpPoolsApi extends MailgunApi {

    /**
     * Enrolls a domain in Dynamic IP Pools.
     *
     * @param name  domain name
     * @param query optional {@code replacement_ip}
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/post-v3-domains--name--dynamic-pools">Enroll domain</a>
     */
    @RequestLine("POST /domains/{name}/dynamic_pools")
    ResponseWithMessage enrollDomain(@Param("name") String name, @QueryMap EnrollDomainInDynamicPoolQuery query);

    @RequestLine("POST /domains/{name}/dynamic_pools")
    Response enrollDomainFeignResponse(@Param("name") String name, @QueryMap EnrollDomainInDynamicPoolQuery query);

    @RequestLine("POST /domains/{name}/dynamic_pools")
    ResponseWithMessage enrollDomain(@Param("name") String name);

    @RequestLine("POST /domains/{name}/dynamic_pools")
    Response enrollDomainFeignResponse(@Param("name") String name);

    /**
     * Removes a domain from Dynamic IP Pools. Provide replacement via {@code query}.
     *
     * @param name  domain name
     * @param query {@code replacement_ip} or {@code replacement_pool_id}
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/delete-v3-domains--name--dynamic-pools">Remove domain</a>
     */
    @RequestLine("DELETE /domains/{name}/dynamic_pools")
    ResponseWithMessage removeDomain(@Param("name") String name, @QueryMap RemoveDomainFromDynamicPoolQuery query);

    @RequestLine("DELETE /domains/{name}/dynamic_pools")
    Response removeDomainFeignResponse(@Param("name") String name, @QueryMap RemoveDomainFromDynamicPoolQuery query);

    /**
     * Lists domains not enrolled in Dynamic IP Pools.
     *
     * @param query optional filters
     * @return {@link AssignableDomainsResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/get-v3-domains-dynamic-pools-assignable">List assignable domains</a>
     */
    @RequestLine("GET /domains/dynamic_pools/assignable")
    AssignableDomainsResponse listAssignableDomains(@QueryMap AssignableDomainsQuery query);

    @RequestLine("GET /domains/dynamic_pools/assignable")
    Response listAssignableDomainsFeignResponse(@QueryMap AssignableDomainsQuery query);

    /**
     * Starts an async job to enroll all account domains (parent account only).
     *
     * @param query {@code include_subaccounts}
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/post-v3-domains-all-dynamic-pools-enroll">Enroll all domains</a>
     */
    @RequestLine("POST /domains/all/dynamic_pools/enroll")
    ResponseWithMessage enrollAllDomains(@QueryMap EnrollAllDomainsInDynamicPoolsQuery query);

    @RequestLine("POST /domains/all/dynamic_pools/enroll")
    Response enrollAllDomainsFeignResponse(@QueryMap EnrollAllDomainsInDynamicPoolsQuery query);

    /**
     * Returns IPs in each Dynamic IP Pool.
     *
     * @return {@link DynamicPoolsListResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/get-v3-dynamic-pools">List all Dynamic IP pools</a>
     */
    @RequestLine("GET /dynamic_pools")
    DynamicPoolsListResponse listDynamicPools();

    @RequestLine("GET /dynamic_pools")
    Response listDynamicPoolsFeignResponse();

    /**
     * Replaces IPs in all dynamic pools.
     *
     * @param request IPs per pool band
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/post-v3-dynamic-pools-all">Initialize/set IPs for all pools</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /dynamic_pools/all")
    ResponseWithMessage initializeDynamicPools(InitializeDynamicPoolsRequest request);

    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /dynamic_pools/all")
    Response initializeDynamicPoolsFeignResponse(InitializeDynamicPoolsRequest request);

    /**
     * Removes all dynamic IP pools from the account.
     *
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/delete-v3-dynamic-pools-all">Remove all dynamic IP pools</a>
     */
    @RequestLine("DELETE /dynamic_pools/all")
    ResponseWithMessage removeAllDynamicPools();

    @RequestLine("DELETE /dynamic_pools/all")
    Response removeAllDynamicPoolsFeignResponse();

    /**
     * Adds a dedicated IP to a dynamic pool.
     *
     * @param poolName pool name (e.g. {@code dynamic_good})
     * @param ip       dedicated IP
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/post-v3-dynamic-pools--pool-name---ip-">Add IP to Dynamic IP Pool</a>
     */
    @RequestLine("POST /dynamic_pools/{pool_name}/{ip}")
    ResponseWithMessage addIpToDynamicPool(@Param("pool_name") String poolName, @Param("ip") String ip);

    @RequestLine("POST /dynamic_pools/{pool_name}/{ip}")
    Response addIpToDynamicPoolFeignResponse(@Param("pool_name") String poolName, @Param("ip") String ip);

    /**
     * Adds and/or removes IPs in a dynamic pool.
     *
     * @param poolName pool name
     * @param request  IPs to add or remove
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/patch-v3-dynamic-pools--pool-name-">Update pool IPs</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PATCH /dynamic_pools/{pool_name}")
    ResponseWithMessage updateDynamicPool(@Param("pool_name") String poolName, UpdateDynamicPoolRequest request);

    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PATCH /dynamic_pools/{pool_name}")
    Response updateDynamicPoolFeignResponse(@Param("pool_name") String poolName, UpdateDynamicPoolRequest request);

}
