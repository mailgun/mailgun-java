package com.mailgun.api.v1;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.AlertsChannel;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.alerts.AlertAvailableEventsResponse;
import com.mailgun.model.alerts.AlertChannelSettings;
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
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MailgunAlertsApiTest extends WireMockBaseTest {

    private static final String V = MailgunAlertsApi.getApiVersion().getValue();
    private static final String EVENTS = "/" + V + "/alerts/events";
    private static final String SETTINGS = "/" + V + "/alerts/settings";
    private static final String SETTINGS_EVENTS = "/" + V + "/alerts/settings/events";
    private static final String SETTINGS_SLACK = "/" + V + "/alerts/settings/slack";
    private static final String SIGNING_KEY = "/" + V + "/alerts/settings/webhooks/signing_key";
    private static final String WEBHOOKS_TEST = "/" + V + "/alerts/webhooks/test";
    private static final String EMAIL_TEST = "/" + V + "/alerts/email/test";
    private static final String SLACK_TEST = "/" + V + "/alerts/slack/test";
    private static final String SLACK_OAUTH = "/" + V + "/alerts/slack/oauth";
    private static final String SLACK_CHANNELS = "/" + V + "/alerts/slack/channels";

    private MailgunAlertsApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunAlertsApi.class);
    }

    @Test
    void listAlertEventsSuccessTest() {
        stubFor(get(urlPathEqualTo(EVENTS))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"events\":[\"ip_listed\",\"ip_delisted\"]}")));

        AlertAvailableEventsResponse r = api.listAlertEvents();

        assertNotNull(r.getEvents());
        assertEquals(List.of("ip_listed", "ip_delisted"), r.getEvents());
    }

    @Test
    void addAlertEventSettingSuccessTest() {
        stubFor(post(urlPathEqualTo(SETTINGS_EVENTS))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":\"set-1\",\"event_type\":\"ip_listed\",\"channel\":\"webhook\","
                                + "\"settings\":{\"url\":\"https://example.com/hook\"}}")));

        AlertEventSettingUpsertRequest req = AlertEventSettingUpsertRequest.builder()
                .eventType("ip_listed")
                .channel(AlertsChannel.WEBHOOK.getValue())
                .settings(AlertChannelSettings.builder()
                        .url("https://example.com/hook")
                        .build())
                .build();

        AlertEventSetting created = api.addAlertEventSetting(req);

        assertEquals("set-1", created.getId());
        assertEquals("ip_listed", created.getEventType());
        assertEquals(AlertsChannel.WEBHOOK.getValue(), created.getChannel());
        assertEquals("https://example.com/hook", created.getSettings().getUrl());
    }

    @Test
    void updateAndRemoveAlertEventSettingSuccessTest() {
        String id = "set-42";
        stubFor(put(urlPathEqualTo(SETTINGS_EVENTS + "/" + id))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"message\":\"settings updated\"}")));

        AlertEventSettingUpsertRequest req = AlertEventSettingUpsertRequest.builder()
                .eventType("ip_delisted")
                .channel(AlertsChannel.EMAIL.getValue())
                .settings(AlertChannelSettings.builder()
                        .emails(List.of("a@example.com", "b@example.com"))
                        .build())
                .build();

        ResponseWithMessage upd = api.updateAlertEventSetting(id, req);
        assertEquals("settings updated", upd.getMessage());

        stubFor(delete(urlPathEqualTo(SETTINGS_EVENTS + "/" + id))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"message\":\"removed\"}")));

        ResponseWithMessage del = api.removeAlertEventSetting(id);
        assertEquals("removed", del.getMessage());
    }

    @Test
    void listAlertSettingsSuccessTest() {
        String body = "{\"events\":[{\"id\":\"e1\",\"event_type\":\"ip_listed\",\"channel\":\"email\","
                + "\"settings\":{\"emails\":[\"ops@example.com\"]}}],"
                + "\"webhooks\":{\"signing_key\":\"whsec_test\"},"
                + "\"slack\":{\"token\":\"xoxb-1\",\"team_id\":\"T1\",\"team_name\":\"Team\",\"scope\":\"channels:read\"}}";
        stubFor(get(urlPathEqualTo(SETTINGS))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        AlertsSettingsResponse r = api.listAlertSettings();

        assertNotNull(r.getEvents());
        assertEquals(1, r.getEvents().size());
        assertEquals("e1", r.getEvents().get(0).getId());
        assertEquals("whsec_test", r.getWebhooks().getSigningKey());
        assertEquals("T1", r.getSlack().getTeamId());
    }

    @Test
    void slackSettingsAndOAuthSuccessTest() {
        stubFor(put(urlPathEqualTo(SETTINGS_SLACK))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse().withStatus(200).withBody("{\"message\":\"ok\"}")));

        ResponseWithMessage put = api.updateSlackSettings(AlertsSlackWorkspace.builder()
                .token("xoxb-new")
                .teamId("T9")
                .teamName("MyTeam")
                .scope("channels:read")
                .build());
        assertEquals("ok", put.getMessage());

        stubFor(delete(urlPathEqualTo(SETTINGS_SLACK))
                .willReturn(aResponse().withStatus(200).withBody("{\"message\":\"slack cleared\"}")));

        assertEquals("slack cleared", api.deleteSlackSettings().getMessage());

        stubFor(delete(urlPathEqualTo(SLACK_OAUTH))
                .willReturn(aResponse().withStatus(200).withBody("{\"message\":\"revoked\"}")));

        assertEquals("revoked", api.revokeSlackOAuth().getMessage());
    }

    @Test
    void resetWebhookSigningKeySuccessTest() {
        stubFor(put(urlPathEqualTo(SIGNING_KEY))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"signing_key\":\"whsec_rotated\"}")));

        AlertsWebhookSigningKeyResponse r = api.resetWebhookSigningKey();
        assertEquals("whsec_rotated", r.getSigningKey());
    }

    @Test
    void testWebhookEmailSlackSuccessTest() {
        stubFor(post(urlPathEqualTo(WEBHOOKS_TEST))
                .willReturn(aResponse().withStatus(200).withBody("{\"message\":\"webhook queued\"}")));
        assertEquals("webhook queued", api.testWebhook(AlertsWebhookTestRequest.builder()
                .eventType("ip_listed")
                .url("https://example.com/h")
                .build()).getMessage());

        stubFor(post(urlPathEqualTo(EMAIL_TEST))
                .willReturn(aResponse().withStatus(200).withBody("{\"message\":\"email sent\"}")));
        assertEquals("email sent", api.testEmail(AlertsEmailTestRequest.builder()
                .eventType("ip_listed")
                .emails(Collections.singletonList("t@example.com"))
                .build()).getMessage());

        stubFor(post(urlPathEqualTo(SLACK_TEST))
                .willReturn(aResponse().withStatus(200).withBody("{\"message\":\"slack sent\"}")));
        assertEquals("slack sent", api.testSlack(AlertsSlackTestRequest.builder()
                .eventType("ip_listed")
                .channelIds(List.of("C1", "C2"))
                .build()).getMessage());
    }

    @Test
    void listAndGetSlackChannelsSuccessTest() {
        stubFor(get(urlEqualTo(SLACK_CHANNELS + "?limit=10"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"items\":[{\"id\":\"C1\",\"name\":\"alerts\",\"is_archived\":false}],"
                                + "\"paging\":{\"first\":\"f\",\"last\":\"l\",\"next\":\"n\",\"previous\":\"p\"}}")));

        AlertSlackChannelsResponse page = api.listSlackChannels(AlertSlackChannelsQuery.builder()
                .limit(10)
                .build());
        assertEquals(1, page.getItems().size());
        assertEquals("C1", page.getItems().get(0).getId());
        assertFalse(page.getItems().get(0).isArchived());
        assertEquals("n", page.getPaging().getNext());

        stubFor(get(urlPathEqualTo(SLACK_CHANNELS))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"items\":[],\"paging\":{}}")));
        assertTrue(api.listSlackChannels().getItems().isEmpty());

        stubFor(get(urlPathEqualTo(SLACK_CHANNELS + "/C1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"id\":\"C1\",\"name\":\"alerts\",\"is_archived\":true}")));

        AlertSlackChannel ch = api.getSlackChannel("C1");
        assertEquals("alerts", ch.getName());
        assertTrue(ch.isArchived());
    }

    @Test
    void addAlertWithSlackSettingsAndDisabledMapSuccessTest() {
        stubFor(post(urlPathEqualTo(SETTINGS_EVENTS))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"id\":\"s2\",\"event_type\":\"send_alert_threshold_breached\",\"channel\":\"slack\","
                                + "\"settings\":{\"channel_ids\":[\"C1\"],\"disabled_channel_ids\":{\"C2\":true}}}")));

        AlertEventSetting created = api.addAlertEventSetting(AlertEventSettingUpsertRequest.builder()
                .eventType("send_alert_threshold_breached")
                .channel(AlertsChannel.SLACK.getValue())
                .settings(AlertChannelSettings.builder()
                        .channelIds(List.of("C1"))
                        .disabledChannelIds(Map.of("C2", true))
                        .build())
                .build());

        assertEquals("s2", created.getId());
        assertNotNull(created.getSettings().getDisabledChannelIds());
        assertEquals(true, created.getSettings().getDisabledChannelIds().get("C2"));
    }

}
