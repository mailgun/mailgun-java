package com.mailgun.model.domainkeys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Domain key item in list responses (v1 and v4).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/get-v1-dkim-keys">List keys for all domains</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/get-v4-domains--authority-name--keys">List domain keys</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DkimKeyItem {

    @JsonProperty("signing_domain")
    String signingDomain;

    String selector;

    /**
     * Whether the key is active for signing.
     */
    Boolean active;

    /**
     * Whether the key is valid (e.g. DNS records correct).
     */
    Boolean valid;

    /**
     * DNS record name or value (structure may vary by API version).
     */
    @JsonProperty("dns_record")
    Object dnsRecord;
}
