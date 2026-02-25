package com.mailgun.api.v2;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.domains.X509CertificateOperationResponse;
import com.mailgun.model.domains.X509CertificateStatusResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

/**
 * Tracking Certificate API (v2): TLS certificates for tracking domains (Let's Encrypt, HTTP-01 via tracking CNAME).
 * Domain format: webPrefix.domainName from domain settings. Generate/regenerate return 202; poll {@code location} for status.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/get-v2-x509--domain--status">Get certificate and status</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/post-v2-x509--domain-">Generate certificate</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/put-v2-x509--domain-">Regenerate expired certificate</a>
 */
@Headers("Accept: application/json")
public interface MailgunTrackingCertificateApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_2;
    }

    /**
     * Get x509 TLS certificate and status for the tracking domain (webPrefix.domainName).
     *
     * @param domain tracking domain (e.g. web prefix + domain name from domain settings)
     * @return {@link X509CertificateStatusResponse} (status: expired, processing, active, error)
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/get-v2-x509--domain--status">Tracking Certificate: Get certificate and status</a>
     */
    @RequestLine("GET /x509/{domain}/status")
    X509CertificateStatusResponse getCertificateStatus(@Param("domain") String domain);

    /**
     * Get certificate status (raw response).
     *
     * @param domain tracking domain
     * @return {@link Response}
     */
    @RequestLine("GET /x509/{domain}/status")
    Response getCertificateStatusFeignResponse(@Param("domain") String domain);

    /**
     * Generate a TLS certificate for the tracking domain (background task). Returns 202; poll {@code location} for status.
     *
     * @param domain tracking domain (webPrefix.domainName)
     * @return {@link X509CertificateOperationResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/post-v2-x509--domain-">Tracking Certificate: Generate</a>
     */
    @RequestLine("POST /x509/{domain}")
    X509CertificateOperationResponse generateCertificate(@Param("domain") String domain);

    /**
     * Generate certificate (raw response). Typically 202.
     *
     * @param domain tracking domain
     * @return {@link Response}
     */
    @RequestLine("POST /x509/{domain}")
    Response generateCertificateFeignResponse(@Param("domain") String domain);

    /**
     * Regenerate an expired TLS certificate (background task). Does not regenerate if certificate is still valid. Returns 202; poll {@code location} for status.
     *
     * @param domain tracking domain (webPrefix.domainName)
     * @return {@link X509CertificateOperationResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/put-v2-x509--domain-">Tracking Certificate: Regenerate expired certificate</a>
     */
    @RequestLine("PUT /x509/{domain}")
    X509CertificateOperationResponse regenerateCertificate(@Param("domain") String domain);

    /**
     * Regenerate certificate (raw response). Typically 202.
     *
     * @param domain tracking domain
     * @return {@link Response}
     */
    @RequestLine("PUT /x509/{domain}")
    Response regenerateCertificateFeignResponse(@Param("domain") String domain);
}
