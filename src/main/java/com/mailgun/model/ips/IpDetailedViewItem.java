package com.mailgun.model.ips;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Detailed IP record from {@code GET /v3/ips/details/all}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/get-v3-ips-details-all">List account IPs (detailed view)</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpDetailedViewItem {

    String address;

    @JsonProperty("parent_account_id")
    String parentAccountId;

    @JsonProperty("account_id")
    String accountId;

    @JsonProperty("pool_ids")
    List<String> poolIds;

    Boolean dedicated;

    @JsonProperty("created_at")
    String createdAt;

    @JsonProperty("pool_last_modified_at")
    String poolLastModifiedAt;

    @JsonProperty("domains_last_modified_at")
    String domainsLastModifiedAt;

}
