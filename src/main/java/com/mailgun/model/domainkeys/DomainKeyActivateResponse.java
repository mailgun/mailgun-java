package com.mailgun.model.domainkeys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response for activate/deactivate domain key (PUT .../keys/{selector}/activate or .../deactivate).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/put-v4-domains--authority-name--keys--selector--activate">Activate a domain key</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/put-v4-domains--authority-name--keys--selector--deactivate">Deactivate a domain key</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainKeyActivateResponse {

    String message;
    String authority;
    String selector;
    Boolean active;
}
