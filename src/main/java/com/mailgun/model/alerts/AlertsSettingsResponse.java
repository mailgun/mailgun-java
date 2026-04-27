package com.mailgun.model.alerts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Response for {@code GET /v1/alerts/settings}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/get-v1-alerts-settings.md">List Alerts</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlertsSettingsResponse {

    List<AlertEventSetting> events;

    AlertsWebhooksSettings webhooks;

    AlertsSlackWorkspace slack;

}
