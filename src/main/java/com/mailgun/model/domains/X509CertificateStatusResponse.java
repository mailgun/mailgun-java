package com.mailgun.model.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * TLS certificate status for tracking domain (GET /v2/x509/{domain}/status).
 * Status is one of: expired, processing, active, error. Certificate PEM present when status is active, error or expired.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/get-v2-x509--domain--status">Tracking Certificate: Get certificate and status</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class X509CertificateStatusResponse {

    /**
     * Certificate status: expired, processing, active, or error.
     */
    String status;

    /**
     * Error message if status is error; null otherwise.
     */
    String error;

    /**
     * x509 certificate in PEM format. Present when status is active, error, or expired.
     */
    String certificate;
}
