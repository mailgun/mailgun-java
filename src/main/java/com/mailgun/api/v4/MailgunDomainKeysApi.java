package com.mailgun.api.v4;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.domainkeys.DomainKeyActivateResponse;
import com.mailgun.model.domainkeys.DomainKeysListResponseV4;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

/**
 * Domain Keys (DKIM) API (v4): list keys for a domain, activate, and deactivate. DNS records must be valid to activate.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/get-v4-domains--authority-name--keys">List domain keys</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/put-v4-domains--authority-name--keys--selector--activate">Activate a domain key</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/put-v4-domains--authority-name--keys--selector--deactivate">Deactivate a domain key</a>
 */
@Headers("Accept: application/json")
public interface MailgunDomainKeysApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_4;
    }

    /**
     * List all domain keys for the authority (active/inactive, valid/invalid).
     *
     * @param authorityName domain authority name
     * @return {@link DomainKeysListResponseV4}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/get-v4-domains--authority-name--keys">List domain keys</a>
     */
    @RequestLine("GET /domains/{authority_name}/keys")
    DomainKeysListResponseV4 listDomainKeys(@Param("authority_name") String authorityName);

    /**
     * List domain keys (raw response).
     *
     * @param authorityName domain authority name
     * @return {@link Response}
     */
    @RequestLine("GET /domains/{authority_name}/keys")
    Response listDomainKeysFeignResponse(@Param("authority_name") String authorityName);

    /**
     * Activate a key for DKIM signing. DNS records must be valid to activate.
     *
     * @param authorityName domain authority name
     * @param selector      selector (valid dot atom)
     * @return {@link DomainKeyActivateResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/put-v4-domains--authority-name--keys--selector--activate">Activate a domain key</a>
     */
    @RequestLine("PUT /domains/{authority_name}/keys/{selector}/activate")
    DomainKeyActivateResponse activateDomainKey(@Param("authority_name") String authorityName, @Param("selector") String selector);

    /**
     * Activate a domain key (raw response).
     *
     * @param authorityName domain authority name
     * @param selector      selector
     * @return {@link Response}
     */
    @RequestLine("PUT /domains/{authority_name}/keys/{selector}/activate")
    Response activateDomainKeyFeignResponse(@Param("authority_name") String authorityName, @Param("selector") String selector);

    /**
     * Deactivate a key; it will no longer be used for signing even if valid.
     *
     * @param authorityName domain authority name
     * @param selector      selector (valid dot atom)
     * @return {@link DomainKeyActivateResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/put-v4-domains--authority-name--keys--selector--deactivate">Deactivate a domain key</a>
     */
    @RequestLine("PUT /domains/{authority_name}/keys/{selector}/deactivate")
    DomainKeyActivateResponse deactivateDomainKey(@Param("authority_name") String authorityName, @Param("selector") String selector);

    /**
     * Deactivate a domain key (raw response).
     *
     * @param authorityName domain authority name
     * @param selector      selector
     * @return {@link Response}
     */
    @RequestLine("PUT /domains/{authority_name}/keys/{selector}/deactivate")
    Response deactivateDomainKeyFeignResponse(@Param("authority_name") String authorityName, @Param("selector") String selector);
}
