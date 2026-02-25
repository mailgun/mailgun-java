package com.mailgun.model.domainkeys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Response for listing domain keys for a domain (GET /v4/domains/{authority_name}/keys).
 * Includes active/inactive and valid/invalid keys.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/get-v4-domains--authority-name--keys">List domain keys</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainKeysListResponseV4 {

    List<DkimKeyItem> items;
}
