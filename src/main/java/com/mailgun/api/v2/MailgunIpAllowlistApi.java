package com.mailgun.api.v2;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.ipallowlist.IpAllowlistEntryRequest;
import com.mailgun.model.ipallowlist.IpAllowlistResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

/**
 * Account IP Allowlist API (v2): restrict API key and SMTP credential usage to trusted IP addresses.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-allowlist">IP Allowlist API</a>
 */
@Headers("Accept: application/json")
public interface MailgunIpAllowlistApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_2;
    }

    /** Returns every IP allowlist entry on the account. */
    @RequestLine("GET /ip_whitelist")
    IpAllowlistResponse getIpAllowlist();

    /** Returns every IP allowlist entry on the account as a raw response. */
    @RequestLine("GET /ip_whitelist")
    Response getIpAllowlistFeignResponse();

    /** Updates the description of an existing IP allowlist entry. */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /ip_whitelist")
    IpAllowlistResponse updateIpAllowlistEntry(IpAllowlistEntryRequest request);

    /** Updates an IP allowlist entry as a raw response. */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /ip_whitelist")
    Response updateIpAllowlistEntryFeignResponse(IpAllowlistEntryRequest request);

    /** Adds an IP allowlist entry to the account. */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /ip_whitelist")
    IpAllowlistResponse addIpAllowlistEntry(IpAllowlistEntryRequest request);

    /** Adds an IP allowlist entry as a raw response. */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /ip_whitelist")
    Response addIpAllowlistEntryFeignResponse(IpAllowlistEntryRequest request);

    /** Deletes an IP allowlist entry identified by its address. */
    @RequestLine("DELETE /ip_whitelist?address={address}")
    IpAllowlistResponse deleteIpAllowlistEntry(@Param("address") String address);

    /** Deletes an IP allowlist entry as a raw response. */
    @RequestLine("DELETE /ip_whitelist?address={address}")
    Response deleteIpAllowlistEntryFeignResponse(@Param("address") String address);
}
