package com.mailgun.model.sendalerts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Send alert returned by the Send Alerts API.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/get-v1-thresholds-alerts-send--name-.md">Get a send alert</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendAlert {

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

    @JsonProperty("alert_channels")
    List<String> alertChannels;

    List<SendAlertFilter> filters;

    String metric;

    String comparator;

    String limit;

    String dimension;

    String period;

}
