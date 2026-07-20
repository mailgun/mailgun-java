package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.ipwarmups.IpWarmupDetailsResponse;
import com.mailgun.model.ipwarmups.IpWarmupsListQuery;
import com.mailgun.model.ipwarmups.IpWarmupsListResponse;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * IP Address Warmup API.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-address-warmup">IP Address Warmup</a>
 */
@Headers("Accept: application/json")
public interface MailgunIpWarmupsApi extends MailgunApi {

    @RequestLine("GET /ip_warmups")
    IpWarmupsListResponse listIpWarmups();

    @RequestLine("GET /ip_warmups")
    Response listIpWarmupsFeignResponse();

    /**
     * Lists in-flight IP warmup statuses for dedicated IP addresses owned by the account.
     *
     * @param query optional {@code page} and {@code limit}
     * @return {@link IpWarmupsListResponse}
     */
    @RequestLine("GET /ip_warmups")
    IpWarmupsListResponse listIpWarmups(@QueryMap IpWarmupsListQuery query);

    @RequestLine("GET /ip_warmups")
    Response listIpWarmupsFeignResponse(@QueryMap IpWarmupsListQuery query);

    /**
     * Returns the status of an in-flight IP warmup.
     *
     * @param address dedicated IP address owned by the account
     * @return {@link IpWarmupDetailsResponse}
     */
    @RequestLine("GET /ip_warmups/{addr}")
    IpWarmupDetailsResponse getIpWarmup(@Param("addr") String address);

    @RequestLine("GET /ip_warmups/{addr}")
    Response getIpWarmupFeignResponse(@Param("addr") String address);

    /**
     * Creates a warmup plan for a dedicated IP address owned by the account.
     *
     * @param address dedicated IP address
     * @return {@link ResponseWithMessage}
     */
    @RequestLine("POST /ip_warmups/{addr}")
    ResponseWithMessage createIpWarmup(@Param("addr") String address);

    @RequestLine("POST /ip_warmups/{addr}")
    Response createIpWarmupFeignResponse(@Param("addr") String address);

    /**
     * Cancels the warmup plan for a dedicated IP address owned by the account.
     *
     * @param address dedicated IP address
     * @return {@link ResponseWithMessage}
     */
    @RequestLine("DELETE /ip_warmups/{addr}")
    ResponseWithMessage cancelIpWarmup(@Param("addr") String address);

    @RequestLine("DELETE /ip_warmups/{addr}")
    Response cancelIpWarmupFeignResponse(@Param("addr") String address);

}
