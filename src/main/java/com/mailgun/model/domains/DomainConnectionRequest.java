package com.mailgun.model.domains;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * Domain connection request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-domains.html#domains">Domains</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class DomainConnectionRequest {

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
     *
     * <p>
     * The default is <code>false</code>.
     * </p>
     */
    @FormProperty("require_tls")
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
     *
     * <p>
     * The default is <code>false</code>.
     * </p>
     */
    @FormProperty("skip_verification")
    Boolean skipVerification;

}
