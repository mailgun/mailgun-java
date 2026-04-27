package com.mailgun.model.alerts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Request body for {@code POST /v1/alerts/settings/events} and {@code PUT /v1/alerts/settings/events/{id}}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/post-v1-alerts-settings-events.md">Add Alert</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/put-v1-alerts-settings-events--id-.md">Update Alert</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlertEventSettingUpsertRequest {

    @JsonProperty("event_type")
    String eventType;

    /**
     * Delivery channel (API string; see {@link com.mailgun.enums.AlertsChannel}).
     */
    String channel;

    AlertChannelSettings settings;

}
