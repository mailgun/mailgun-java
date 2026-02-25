package com.mailgun.model.domainkeys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response after creating a domain key (POST /v1/dkim/keys).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/post-v1-dkim-keys">Create a domain key</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateDomainKeyResponse {

    @JsonProperty("signing_domain")
    String signingDomain;

    String selector;

    /**
     * DNS record to publish (structure may vary).
     */
    @JsonProperty("dns_record")
    Object dnsRecord;
}
