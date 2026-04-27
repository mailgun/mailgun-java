package com.mailgun.model.sendalerts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * A threshold hit for the account.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/get-v1-thresholds-hits.md">List account hits</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThresholdHit {

    String id;

    String name;

    @JsonProperty("created_at")
    String createdAt;

    @JsonProperty("updated_at")
    String updatedAt;

    boolean triggered;

    @JsonProperty("expires_at")
    String expiresAt;

    @JsonProperty("latest_value")
    String latestValue;

    String metric;

    String comparator;

    String limit;

    @JsonProperty("parent_account_id")
    String parentAccountId;

    @JsonProperty("subaccount_id")
    String subaccountId;

    String dimension;

    @JsonProperty("dimension_value")
    String dimensionValue;

}
