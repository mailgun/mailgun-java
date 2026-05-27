package com.mailgun.api.v1;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.dynamicpools.DynamicPoolDomainPreviewResponse;
import com.mailgun.model.dynamicpools.DynamicPoolDomainsQuery;
import com.mailgun.model.dynamicpools.DynamicPoolDomainsResponse;
import com.mailgun.model.dynamicpools.DynamicPoolHistoryListResponse;
import com.mailgun.model.dynamicpools.DynamicPoolHistoryQuery;
import com.mailgun.model.dynamicpools.DynamicPoolHistoryRecord;
import com.mailgun.model.dynamicpools.DynamicPoolOverrideRequest;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

import java.util.List;

/**
 * Dynamic IP Pools management API (v1): domains, preview, history, and overrides.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools">Dynamic IP Pools</a>
 * @see com.mailgun.api.v3.MailgunDynamicIpPoolsApi v3 pool and domain enrollment endpoints
 */
@Headers("Accept: application/json")
public interface MailgunDynamicIpPoolsApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_1;
    }

    /**
     * Lists domains enrolled in Dynamic IP Pools across parent and subaccounts.
     *
     * @param query optional filters and pagination
     * @return {@link DynamicPoolDomainsResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/get-v1-dynamic-pools-domains">List domains</a>
     */
    @RequestLine("GET /dynamic_pools/domains")
    DynamicPoolDomainsResponse listDomains(@QueryMap DynamicPoolDomainsQuery query);

    @RequestLine("GET /dynamic_pools/domains")
    Response listDomainsFeignResponse(@QueryMap DynamicPoolDomainsQuery query);

    /**
     * Previews which dynamic pool a domain would be assigned to (does not enroll).
     *
     * @param name domain name
     * @return {@link DynamicPoolDomainPreviewResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/get-v1-dynamic-pools-domains--name--preview">Preview domain assignment</a>
     */
    @RequestLine("GET /dynamic_pools/domains/{name}/preview")
    DynamicPoolDomainPreviewResponse previewDomain(@Param("name") String name);

    @RequestLine("GET /dynamic_pools/domains/{name}/preview")
    Response previewDomainFeignResponse(@Param("name") String name);

    /**
     * Returns history records for a domain.
     *
     * @param name domain name
     * @return history records
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/get-v1-dynamic-pools-domains--name--history">List domain history</a>
     */
    @RequestLine("GET /dynamic_pools/domains/{name}/history")
    List<DynamicPoolHistoryRecord> getDomainHistory(@Param("name") String name);

    @RequestLine("GET /dynamic_pools/domains/{name}/history")
    Response getDomainHistoryFeignResponse(@Param("name") String name);

    /**
     * Overrides a domain's dynamic pool assignment.
     *
     * @param name    domain name
     * @param request target pool name
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/put-v1-dynamic-pools-domains--name--override">Override domain assignment</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /dynamic_pools/domains/{name}/override")
    ResponseWithMessage overrideDomainPool(@Param("name") String name, DynamicPoolOverrideRequest request);

    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /dynamic_pools/domains/{name}/override")
    Response overrideDomainPoolFeignResponse(@Param("name") String name, DynamicPoolOverrideRequest request);

    /**
     * Removes a dynamic pool override so health checks manage assignment again.
     *
     * @param name domain name
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/delete-v1-dynamic-pools-domains--name--override">Remove override</a>
     */
    @RequestLine("DELETE /dynamic_pools/domains/{name}/override")
    ResponseWithMessage removeDomainOverride(@Param("name") String name);

    @RequestLine("DELETE /dynamic_pools/domains/{name}/override")
    Response removeDomainOverrideFeignResponse(@Param("name") String name);

    /**
     * Lists history across all domains on parent and subaccounts.
     *
     * @param query filters and pagination
     * @return {@link DynamicPoolHistoryListResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/get-v1-dynamic-pools-history">List account history</a>
     */
    @RequestLine("GET /dynamic_pools/history")
    DynamicPoolHistoryListResponse listHistory(@QueryMap DynamicPoolHistoryQuery query);

    @RequestLine("GET /dynamic_pools/history")
    Response listHistoryFeignResponse(@QueryMap DynamicPoolHistoryQuery query);

}
