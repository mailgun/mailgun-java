package com.mailgun.model.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Domain connection.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-domains.html#domains">Domains</a>
 */
@Value
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainConnection {

    /**
     * <p>
     * <code>true</code> or <code>false</code>
     * </p>
     *
     * <p>
     * If set to <code>true</code>, this requires the message only be sent over a TLS connection.
     * If a TLS connection can not be established, Mailgun will not deliver the message.
     * </p>
     *
     * <p>
     * If set to <code>false</code>, Mailgun will still try and upgrade the connection,
     * but if Mailgun cannot, the message will be delivered over a plaintext SMTP connection.
     * </p>
     */
    @JsonProperty("require_tls")
    Boolean requireTls;

    /**
     * <p>
     * <code>true</code> or <code>false</code>
     * </p>
     *
     * <p>
     * If set to <code>true</code>, the certificate and hostname will not be verified when trying to establish a TLS connection
     * and Mailgun will accept any certificate during delivery.
     * </p>
     *
     * <p>
     * If set to <code>false</code>, Mailgun will verify the certificate and hostname.
     * If either one can not be verified, a TLS connection will not be established.
     * </p>
     */
    @JsonProperty("skip_verification")
    Boolean skipVerification;

}
