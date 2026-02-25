package com.mailgun.model.domainkeys;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.File;

/**
 * Request to create a domain key (POST /v1/dkim/keys). Private keys are never exported after creation/import.
 * PEM can be sent as a file attachment or as a form-string.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/post-v1-dkim-keys">Create a domain key</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class CreateDomainKeyRequest {

    /**
     * Signing domain for the new key. Required.
     */
    @FormProperty("signing_domain")
    String signingDomain;

    /**
     * Selector for the new key. Required.
     */
    String selector;

    /**
     * Key size in bits: 1024 or 2048.
     */
    Integer bits;

    /**
     * Private key PEM file (RSA, PKCS #1 ASN.1 DER). Optional if generating; use when importing. Mutually exclusive with pemContent.
     */
    File pem;

    /**
     * Private key PEM as form-string. Optional; use when importing instead of file attachment. Mutually exclusive with pem.
     */
    @FormProperty("pem")
    String pemContent;
}
