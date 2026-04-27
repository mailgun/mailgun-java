package com.mailgun.api.v1;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.alerts.AlertAvailableEventsResponse;
import com.mailgun.model.alerts.AlertEventSetting;
import com.mailgun.model.alerts.AlertEventSettingUpsertRequest;
import com.mailgun.model.alerts.AlertSlackChannel;
import com.mailgun.model.alerts.AlertSlackChannelsQuery;
import com.mailgun.model.alerts.AlertSlackChannelsResponse;
import com.mailgun.model.alerts.AlertsEmailTestRequest;
import com.mailgun.model.alerts.AlertsSettingsResponse;
import com.mailgun.model.alerts.AlertsSlackTestRequest;
import com.mailgun.model.alerts.AlertsSlackWorkspace;
import com.mailgun.model.alerts.AlertsWebhookSigningKeyResponse;
import com.mailgun.model.alerts.AlertsWebhookTestRequest;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

/**
 * Mailgun Alerts API (v1): event types, per-event delivery settings (email, webhook, Slack),
 * signing key rotation, test notifications, and Slack workspace channels.
 * <p>
 * This is separate from Send Alert <em>thresholds</em> ({@code /v1/thresholds/...}), which are configured via
 * {@link com.mailgun.api.v1.MailgunSendAlertsApi}.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/get-v1-alerts-events.md">List events</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/post-v1-alerts-settings-events.md">Add Alert</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/put-v1-alerts-settings-events--id-.md">Update Alert</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/delete-v1-alerts-settings-events--id-.md">Remove Alert</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/get-v1-alerts-settings.md">List Alerts</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/put-v1-alerts-settings-slack.md">Update Slack settings</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/delete-v1-alerts-settings-slack.md">Delete Slack settings</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/put-v1-alerts-settings-webhooks-signing-key.md">Reset Webhook Signing Key</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/post-v1-alerts-webhooks-test.md">Test webhook</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/post-v1-alerts-email-test.md">Test message (email)</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/post-v1-alerts-slack-test.md">Test message (Slack)</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/delete-v1-alerts-slack-oauth.md">Revoke Slack access token</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/get-v1-alerts-slack-channels--id-.md">Get Slack channel</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/get-v1-alerts-slack-channels.md">List Slack channels</a>
 */
@Headers({"Accept: application/json", "Content-Type: application/json"})
public interface MailgunAlertsApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_1;
    }

    @RequestLine("GET /alerts/events")
    AlertAvailableEventsResponse listAlertEvents();

    @RequestLine("POST /alerts/settings/events")
    AlertEventSetting addAlertEventSetting(AlertEventSettingUpsertRequest request);

    @RequestLine("PUT /alerts/settings/events/{id}")
    ResponseWithMessage updateAlertEventSetting(@Param("id") String id, AlertEventSettingUpsertRequest request);

    @RequestLine("DELETE /alerts/settings/events/{id}")
    ResponseWithMessage removeAlertEventSetting(@Param("id") String id);

    @RequestLine("GET /alerts/settings")
    AlertsSettingsResponse listAlertSettings();

    @RequestLine("PUT /alerts/settings/slack")
    ResponseWithMessage updateSlackSettings(AlertsSlackWorkspace request);

    @RequestLine("DELETE /alerts/settings/slack")
    ResponseWithMessage deleteSlackSettings();

    @RequestLine("PUT /alerts/settings/webhooks/signing_key")
    AlertsWebhookSigningKeyResponse resetWebhookSigningKey();

    @RequestLine("POST /alerts/webhooks/test")
    ResponseWithMessage testWebhook(AlertsWebhookTestRequest request);

    @RequestLine("POST /alerts/email/test")
    ResponseWithMessage testEmail(AlertsEmailTestRequest request);

    @RequestLine("POST /alerts/slack/test")
    ResponseWithMessage testSlack(AlertsSlackTestRequest request);

    @RequestLine("DELETE /alerts/slack/oauth")
    ResponseWithMessage revokeSlackOAuth();

    @RequestLine("GET /alerts/slack/channels/{id}")
    AlertSlackChannel getSlackChannel(@Param("id") String id);

    @RequestLine("GET /alerts/slack/channels")
    AlertSlackChannelsResponse listSlackChannels();

    @RequestLine("GET /alerts/slack/channels")
    AlertSlackChannelsResponse listSlackChannels(@QueryMap AlertSlackChannelsQuery query);

}
