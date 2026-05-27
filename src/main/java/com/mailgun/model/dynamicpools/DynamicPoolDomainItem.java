package com.mailgun.model.dynamicpools;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Domain enrolled in a Dynamic IP Pool.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/get-v1-dynamic-pools-domains">List domains</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamicPoolDomainItem {

    String id;

    @JsonProperty("account_id")
    String accountId;

    @JsonProperty("account_name")
    String accountName;

    String name;

    @JsonProperty("registered_at")
    String registeredAt;

    String pool;

    Boolean override;

    @JsonProperty("bounce_rate")
    Double bounceRate;

    @JsonProperty("complaint_rate")
    Double complaintRate;

    @JsonProperty("processed_count")
    Integer processedCount;

}
