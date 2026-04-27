package com.mailgun.api.v1;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.SendAlertChannel;
import com.mailgun.enums.SendAlertDimension;
import com.mailgun.enums.SendAlertFilterComparator;
import com.mailgun.enums.SendAlertMetric;
import com.mailgun.enums.SendAlertThresholdComparator;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.sendalerts.SendAlert;
import com.mailgun.model.sendalerts.SendAlertFilter;
import com.mailgun.model.sendalerts.SendAlertRequest;
import com.mailgun.model.sendalerts.SendAlertsListResult;
import com.mailgun.model.sendalerts.ThresholdHit;
import com.mailgun.model.sendalerts.ThresholdHitsListResult;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MailgunSendAlertsApiTest extends WireMockBaseTest {

    private static final String API_VERSION = MailgunSendAlertsApi.getApiVersion().getValue();
    private static final String SEND_ALERTS_PATH = "/" + API_VERSION + "/thresholds/alerts/send";
    private static final String THRESHOLD_HITS_PATH = "/" + API_VERSION + "/thresholds/hits";

    private MailgunSendAlertsApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunSendAlertsApi.class);
    }

    @Test
    void listSendAlertsSuccessTest() {
        String body = "{\"items\":[{\"id\":\"alert-1\",\"name\":\"bounce-watch\",\"created_at\":\"2024-01-01T00:00:00Z\","
                + "\"metric\":\"hard_bounce_rate\",\"comparator\":\">\",\"limit\":\"0.05\",\"dimension\":\"domain\","
                + "\"alert_channels\":[\"email\"]}],\"total\":1}";
        stubFor(get(urlPathEqualTo(SEND_ALERTS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        SendAlertsListResult result = api.listSendAlerts();

        assertNotNull(result);
        assertEquals(Integer.valueOf(1), result.getTotal());
        assertNotNull(result.getItems());
        assertEquals(1, result.getItems().size());
        SendAlert alert = result.getItems().get(0);
        assertEquals("alert-1", alert.getId());
        assertEquals("bounce-watch", alert.getName());
        assertEquals(SendAlertMetric.HARD_BOUNCE_RATE.getValue(), alert.getMetric());
        assertEquals(SendAlertThresholdComparator.GT.getValue(), alert.getComparator());
        assertEquals("0.05", alert.getLimit());
        assertEquals(SendAlertDimension.DOMAIN.getValue(), alert.getDimension());
        assertEquals(Collections.singletonList(SendAlertChannel.EMAIL.getValue()), alert.getAlertChannels());
    }

    @Test
    void createSendAlertSuccessTest() {
        String responseBody = "{\"id\":\"new-id\",\"name\":\"my-alert\",\"created_at\":\"2024-06-01T12:00:00Z\","
                + "\"metric\":\"delivered_rate\",\"comparator\":\"<\",\"limit\":\"0.9\",\"dimension\":\"ip_pool\","
                + "\"filters\":[{\"dimension\":\"domain\",\"comparator\":\"contains\",\"values\":[\"example.com\"]}]}";
        stubFor(post(urlPathEqualTo(SEND_ALERTS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(equalToJson("{\"name\":\"my-alert\",\"metric\":\"delivered_rate\",\"comparator\":\"<\","
                        + "\"limit\":\"0.9\",\"dimension\":\"ip_pool\",\"alert_channels\":[\"slack\"],"
                        + "\"filters\":[{\"dimension\":\"domain\",\"comparator\":\"contains\",\"values\":[\"example.com\"]}],"
                        + "\"period\":\"1h\",\"description\":\"watch delivery\"}"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)));

        SendAlertRequest request = SendAlertRequest.builder()
                .name("my-alert")
                .metric(SendAlertMetric.DELIVERED_RATE.getValue())
                .comparator(SendAlertThresholdComparator.LT.getValue())
                .limit("0.9")
                .dimension(SendAlertDimension.IP_POOL.getValue())
                .alertChannels(List.of(SendAlertChannel.SLACK.getValue()))
                .filters(List.of(SendAlertFilter.builder()
                        .dimension(SendAlertDimension.DOMAIN.getValue())
                        .comparator(SendAlertFilterComparator.CONTAINS.getValue())
                        .values(List.of("example.com"))
                        .build()))
                .period("1h")
                .description("watch delivery")
                .build();

        SendAlert created = api.createSendAlert(request);

        assertNotNull(created);
        assertEquals("new-id", created.getId());
        assertEquals("my-alert", created.getName());
        assertNotNull(created.getFilters());
        assertEquals(1, created.getFilters().size());
        assertEquals("example.com", created.getFilters().get(0).getValues().get(0));
    }

    @Test
    void getSendAlertSuccessTest() {
        String alertName = "bounce-watch";
        String body = "{\"name\":\"bounce-watch\",\"created_at\":\"2024-01-01T00:00:00Z\","
                + "\"metric\":\"temporary_fail_rate\",\"comparator\":\">=\",\"limit\":\"0.1\",\"dimension\":\"subaccount\"}";
        stubFor(get(urlPathEqualTo(SEND_ALERTS_PATH + "/" + alertName))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        SendAlert alert = api.getSendAlert(alertName);

        assertNotNull(alert);
        assertEquals(alertName, alert.getName());
        assertEquals(SendAlertMetric.TEMPORARY_FAIL_RATE.getValue(), alert.getMetric());
        assertEquals(SendAlertThresholdComparator.GE.getValue(), alert.getComparator());
        assertEquals(SendAlertDimension.SUBACCOUNT.getValue(), alert.getDimension());
    }

    @Test
    void updateSendAlertSuccessTest() {
        String alertName = "bounce-watch";
        stubFor(put(urlPathEqualTo(SEND_ALERTS_PATH + "/" + alertName))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"updated\"}")));

        SendAlertRequest request = SendAlertRequest.builder()
                .name(alertName)
                .metric(SendAlertMetric.COMPLAINED_RATE.getValue())
                .comparator(SendAlertThresholdComparator.GT.getValue())
                .limit("0.01")
                .dimension(SendAlertDimension.RECIPIENT_PROVIDER.getValue())
                .alertChannels(List.of(SendAlertChannel.WEBHOOK.getValue()))
                .build();

        ResponseWithMessage result = api.updateSendAlert(alertName, request);

        assertNotNull(result);
        assertEquals("updated", result.getMessage());
    }

    @Test
    void deleteSendAlertSuccessTest() {
        String alertName = "old-alert";
        stubFor(delete(urlPathEqualTo(SEND_ALERTS_PATH + "/" + alertName))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"deleted\"}")));

        ResponseWithMessage result = api.deleteSendAlert(alertName);

        assertNotNull(result);
        assertEquals("deleted", result.getMessage());
    }

    @Test
    void listThresholdHitsSuccessTest() {
        String body = "{\"items\":[{\"id\":\"hit-1\",\"name\":\"bounce-watch\",\"created_at\":\"2024-01-02T00:00:00Z\","
                + "\"updated_at\":\"2024-01-02T01:00:00Z\",\"triggered\":true,\"expires_at\":\"2024-01-03T00:00:00Z\","
                + "\"latest_value\":\"0.12\",\"metric\":\"hard_bounce_rate\",\"comparator\":\">\",\"limit\":\"0.05\","
                + "\"dimension\":\"domain\",\"dimension_value\":\"mg.example.com\"}],\"total\":1}";
        stubFor(get(urlPathEqualTo(THRESHOLD_HITS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        ThresholdHitsListResult result = api.listThresholdHits();

        assertNotNull(result);
        assertEquals(Integer.valueOf(1), result.getTotal());
        assertNotNull(result.getItems());
        assertEquals(1, result.getItems().size());
        ThresholdHit hit = result.getItems().get(0);
        assertEquals("hit-1", hit.getId());
        assertTrue(hit.isTriggered());
        assertEquals("bounce-watch", hit.getName());
        assertEquals("mg.example.com", hit.getDimensionValue());
    }

}
