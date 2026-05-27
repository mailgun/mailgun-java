package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.ippools.AddIpPoolIpsRequest;
import com.mailgun.model.ippools.CreateIpPoolRequest;
import com.mailgun.model.ippools.DeleteIpPoolQuery;
import com.mailgun.model.ippools.IpPoolCreateResponse;
import com.mailgun.model.ippools.IpPoolDelegationRequest;
import com.mailgun.model.ippools.IpPoolDetailsResponse;
import com.mailgun.model.ippools.IpPoolDomainsQuery;
import com.mailgun.model.ippools.IpPoolDomainsResponse;
import com.mailgun.model.ippools.IpPoolsListResponse;
import com.mailgun.model.ippools.UpdateIpPoolRequest;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * Dedicated IP pools (DIPPs) API.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools">IP Pools</a>
 */
@Headers("Accept: application/json")
public interface MailgunIpPoolsApi extends MailgunApi {

    /**
     * Lists all dedicated IP pools of the account.
     *
     * @return {@link IpPoolsListResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/get-v3-ip-pools">List dedicated IP pools</a>
     */
    @RequestLine("GET /ip_pools")
    IpPoolsListResponse listIpPools();

    @RequestLine("GET /ip_pools")
    Response listIpPoolsFeignResponse();

    /**
     * Creates a new dedicated IP pool (DIPP). The account must have the DIPPs feature enabled.
     *
     * @param request name, description, and optional IPs
     * @return {@link IpPoolCreateResponse} includes the new {@code pool_id}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/post-v3-ip-pools">Add a new DIPP</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /ip_pools")
    IpPoolCreateResponse createIpPool(CreateIpPoolRequest request);

    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /ip_pools")
    Response createIpPoolFeignResponse(CreateIpPoolRequest request);

    /**
     * Returns details for a dedicated IP pool, including linked domains when {@code is_linked} is true.
     *
     * @param poolId id of the DIPP
     * @return {@link IpPoolDetailsResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/get-v3-ip-pools--pool-id-">Get DIPP details</a>
     */
    @RequestLine("GET /ip_pools/{pool_id}")
    IpPoolDetailsResponse getIpPool(@Param("pool_id") String poolId);

    @RequestLine("GET /ip_pools/{pool_id}")
    Response getIpPoolFeignResponse(@Param("pool_id") String poolId);

    /**
     * Deletes a dedicated IP pool. Optional replacement DIPP or IP via query parameters.
     *
     * @param poolId id of the DIPP to delete
     * @param query  optional replacement {@code ip} or {@code pool_id}
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/delete-v3-ip-pools--pool-id-">Delete the DIPP</a>
     */
    @RequestLine("DELETE /ip_pools/{pool_id}")
    ResponseWithMessage deleteIpPool(@Param("pool_id") String poolId, @QueryMap DeleteIpPoolQuery query);

    @RequestLine("DELETE /ip_pools/{pool_id}")
    Response deleteIpPoolFeignResponse(@Param("pool_id") String poolId, @QueryMap DeleteIpPoolQuery query);

    /**
     * Deletes a dedicated IP pool without replacement parameters.
     *
     * @param poolId id of the DIPP to delete
     * @return {@link ResponseWithMessage}
     */
    @RequestLine("DELETE /ip_pools/{pool_id}")
    ResponseWithMessage deleteIpPool(@Param("pool_id") String poolId);

    @RequestLine("DELETE /ip_pools/{pool_id}")
    Response deleteIpPoolFeignResponse(@Param("pool_id") String poolId);

    /**
     * Edits a dedicated IP pool (DIPPs feature required; inherited pools cannot be edited).
     *
     * @param poolId  id of the DIPP
     * @param request fields to update
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/patch-v3-ip-pools--pool-id-">Edit DIPP</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PATCH /ip_pools/{pool_id}")
    ResponseWithMessage updateIpPool(@Param("pool_id") String poolId, UpdateIpPoolRequest request);

    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PATCH /ip_pools/{pool_id}")
    Response updateIpPoolFeignResponse(@Param("pool_id") String poolId, UpdateIpPoolRequest request);

    /**
     * Returns a paginated list of domains linked to the specified DIPP.
     *
     * @param poolId id of the DIPP
     * @param query  optional {@code limit} and {@code page}
     * @return {@link IpPoolDomainsResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/get-v3-ip-pools--pool-id--domains">Get domains linked to DIPP</a>
     */
    @RequestLine("GET /ip_pools/{pool_id}/domains")
    IpPoolDomainsResponse getIpPoolDomains(@Param("pool_id") String poolId, @QueryMap IpPoolDomainsQuery query);

    @RequestLine("GET /ip_pools/{pool_id}/domains")
    Response getIpPoolDomainsFeignResponse(@Param("pool_id") String poolId, @QueryMap IpPoolDomainsQuery query);

    /**
     * Adds a dedicated IP to a DIPP.
     *
     * @param poolId id of the DIPP
     * @param ip     dedicated IP to add
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/put-v3-ip-pools--pool-id--ips--ip-">Add an IP to a DIPP</a>
     */
    @RequestLine("PUT /ip_pools/{pool_id}/ips/{ip}")
    ResponseWithMessage addIpToIpPool(@Param("pool_id") String poolId, @Param("ip") String ip);

    @RequestLine("PUT /ip_pools/{pool_id}/ips/{ip}")
    Response addIpToIpPoolFeignResponse(@Param("pool_id") String poolId, @Param("ip") String ip);

    /**
     * Removes a dedicated IP from a DIPP.
     *
     * @param poolId id of the DIPP
     * @param ip     IP to remove
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/delete-v3-ip-pools--pool-id--ips--ip-">Remove an IP from a DIPP</a>
     */
    @RequestLine("DELETE /ip_pools/{pool_id}/ips/{ip}")
    ResponseWithMessage removeIpFromIpPool(@Param("pool_id") String poolId, @Param("ip") String ip);

    @RequestLine("DELETE /ip_pools/{pool_id}/ips/{ip}")
    Response removeIpFromIpPoolFeignResponse(@Param("pool_id") String poolId, @Param("ip") String ip);

    /**
     * Adds multiple dedicated IPs to a DIPP (JSON body).
     *
     * @param poolId  id of the DIPP
     * @param request list of IPs
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/post-v3-ip-pools--pool-id--ips-json">Add multiple IPs to the DIPP</a>
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @RequestLine("POST /ip_pools/{pool_id}/ips.json")
    ResponseWithMessage addIpsToIpPool(@Param("pool_id") String poolId, AddIpPoolIpsRequest request);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @RequestLine("POST /ip_pools/{pool_id}/ips.json")
    Response addIpsToIpPoolFeignResponse(@Param("pool_id") String poolId, AddIpPoolIpsRequest request);

    /**
     * Delegates a DIPP to a subaccount.
     *
     * @param poolId  id of the DIPP
     * @param request subaccount id
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/put-v3-ip-pools--pool-id--delegate">Delegate DIPP to Subaccount</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /ip_pools/{pool_id}/delegate")
    ResponseWithMessage delegateIpPool(@Param("pool_id") String poolId, IpPoolDelegationRequest request);

    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /ip_pools/{pool_id}/delegate")
    Response delegateIpPoolFeignResponse(@Param("pool_id") String poolId, IpPoolDelegationRequest request);

    /**
     * Revokes delegation of a DIPP from a subaccount.
     *
     * @param poolId  id of the DIPP
     * @param request subaccount id
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/delete-v3-ip-pools--pool-id--delegate">Revoke DIPP from Subaccount</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("DELETE /ip_pools/{pool_id}/delegate")
    ResponseWithMessage revokeIpPoolDelegation(@Param("pool_id") String poolId, IpPoolDelegationRequest request);

    @Headers("Content-Type: multipart/form-data")
    @RequestLine("DELETE /ip_pools/{pool_id}/delegate")
    Response revokeIpPoolDelegationFeignResponse(@Param("pool_id") String poolId, IpPoolDelegationRequest request);

}
