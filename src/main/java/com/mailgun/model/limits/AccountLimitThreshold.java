package com.mailgun.model.limits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mailgun.model.sendalerts.SendAlertFilter;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Limit threshold returned by {@code GET/POST /v1/thresholds/limits} and {@code GET /v1/thresholds/limits/{name}}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/limits/get-v1-thresholds-limits.md">List limit thresholds</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountLimitThreshold {

    String id;

    @JsonProperty("parent_account_id")
    String parentAccountId;

    @JsonProperty("subaccount_id")
    String subaccountId;

    @JsonProperty("account_group")
    String accountGroup;

    String name;

    @JsonProperty("created_at")
    String createdAt;

    @JsonProperty("updated_at")
    String updatedAt;

    @JsonProperty("last_checked")
    String lastChecked;

    String description;

    List<SendAlertFilter> filters;

    /**
     * Metric (API string; see {@link com.mailgun.enums.LimitMetric}).
     */
    String metric;

    /**
     * Threshold comparison (API string; see {@link com.mailgun.enums.SendAlertThresholdComparator}).
     */
    String comparator;

    String limit;

    /**
     * Dimension (API string; see {@link com.mailgun.enums.SendAlertDimension}).
     */
    String dimension;

    String period;

}
