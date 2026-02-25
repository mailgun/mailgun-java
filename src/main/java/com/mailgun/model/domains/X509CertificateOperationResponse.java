package com.mailgun.model.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response for generate/regenerate TLS certificate (POST/PUT /v2/x509/{domain}). Returns 202.
 * Poll the {@code location} URL to check certificate status.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/post-v2-x509--domain-">Tracking Certificate: Generate</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/put-v2-x509--domain-">Tracking Certificate: Regenerate expired certificate</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class X509CertificateOperationResponse {

    /**
     * Status message for the request.
     */
    String message;

    /**
     * URL path to poll for certificate status.
     */
    String location;
}
