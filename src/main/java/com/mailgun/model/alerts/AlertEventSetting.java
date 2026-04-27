package com.mailgun.model.alerts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * A configured alert setting (event type + channel + delivery details).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/post-v1-alerts-settings-events.md">Add Alert</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/get-v1-alerts-settings.md">List Alerts</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlertEventSetting {

    String id;

    @JsonProperty("event_type")
    String eventType;

    /**
     * Delivery channel (API string; see {@link com.mailgun.enums.AlertsChannel}).
     */
    String channel;

    AlertChannelSettings settings;

    @JsonProperty("disabled_at")
    String disabledAt;

}
