package com.mailgun.model.dynamicpools;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Dynamic IP Pool assignment history record.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/get-v1-dynamic-pools-history">List account history</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamicPoolHistoryRecord {

    String id;

    @JsonProperty("owning_account_id")
    String owningAccountId;

    @JsonProperty("account_id")
    String accountId;

    @JsonProperty("account_name")
    String accountName;

    @JsonProperty("domain_id")
    String domainId;

    @JsonProperty("domain_name")
    String domainName;

    @JsonProperty("new_band")
    String newBand;

    @JsonProperty("prev_band")
    String prevBand;

    String reason;

    @JsonProperty("bounce_rate")
    Double bounceRate;

    @JsonProperty("complaint_rate")
    Double complaintRate;

    @JsonProperty("processed_count")
    Integer processedCount;

    @JsonProperty("initiated_by")
    String initiatedBy;

    String timestamp;

}
