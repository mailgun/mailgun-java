package com.mailgun.api.v1;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.WebhookName;
import com.mailgun.model.webhooks.AccountWebhook;
import com.mailgun.model.webhooks.AccountWebhookCreatedResult;
import com.mailgun.model.webhooks.AccountWebhookListResult;
import com.mailgun.model.webhooks.AccountWebhookRequest;
import com.mailgun.model.webhooks.AccountWebhooksDeleteQuery;
import com.mailgun.model.webhooks.AccountWebhooksListQuery;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunAccountWebhooksApiTest extends WireMockBaseTest {

    private static final String API_VERSION = MailgunAccountWebhooksApi.getApiVersion().getValue();
    private static final String WEBHOOKS_PATH = "/" + API_VERSION + "/webhooks";

    private MailgunAccountWebhooksApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunAccountWebhooksApi.class);
    }

    @Test
    void getAllWebhooksSuccessTest() {
        String body = "{\"webhooks\":[{\"webhook_id\":\"wh-1\",\"description\":\"test\",\"url\":\"https://example.com/hook\",\"event_types\":[\"clicked\"],\"created_at\":\"2024-01-01T00:00:00Z\"}]}";
        stubFor(get(urlPathEqualTo(WEBHOOKS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        AccountWebhookListResult result = api.getAllWebhooks();

        assertNotNull(result);
        assertNotNull(result.getWebhooks());
        assertEquals(1, result.getWebhooks().size());
        AccountWebhook webhook = result.getWebhooks().get(0);
        assertEquals("wh-1", webhook.getWebhookId());
        assertEquals("test", webhook.getDescription());
        assertEquals("https://example.com/hook", webhook.getUrl());
        assertNotNull(webhook.getEventTypes());
        assertEquals(1, webhook.getEventTypes().size());
        assertEquals("clicked", webhook.getEventTypes().get(0));
        assertEquals("2024-01-01T00:00:00Z", webhook.getCreatedAt());
    }

    @Test
    void getAllWebhooksWithQuerySuccessTest() {
        String body = "{\"webhooks\":[{\"webhook_id\":\"wh-1\",\"description\":\"desc\",\"url\":\"https://example.com/hook\",\"event_types\":[\"delivered\"],\"created_at\":\"2024-01-01T00:00:00Z\"}]}";
        stubFor(get(urlEqualTo(WEBHOOKS_PATH + "?webhook_ids=wh-1"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        AccountWebhooksListQuery query = AccountWebhooksListQuery.builder()
                .webhook_ids("wh-1")
                .build();
        AccountWebhookListResult result = api.getAllWebhooks(query);

        assertNotNull(result);
        assertNotNull(result.getWebhooks());
        assertEquals(1, result.getWebhooks().size());
        assertEquals("wh-1", result.getWebhooks().get(0).getWebhookId());
    }

    @Test
    void getWebhookSuccessTest() {
        String webhookId = "wh-42";
        String body = "{\"webhook_id\":\"wh-42\",\"description\":\"my webhook\",\"url\":\"https://example.com/wh\",\"event_types\":[\"permanent_fail\",\"temporary_fail\"],\"created_at\":\"2024-03-01T12:00:00Z\"}";
        stubFor(get(urlPathEqualTo(WEBHOOKS_PATH + "/" + webhookId))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        AccountWebhook result = api.getWebhook(webhookId);

        assertNotNull(result);
        assertEquals("wh-42", result.getWebhookId());
        assertEquals("my webhook", result.getDescription());
        assertEquals("https://example.com/wh", result.getUrl());
        assertNotNull(result.getEventTypes());
        assertEquals(Arrays.asList("permanent_fail", "temporary_fail"), result.getEventTypes());
        assertEquals("2024-03-01T12:00:00Z", result.getCreatedAt());
    }

    @Test
    void createWebhookSuccessTest() {
        String body = "{\"webhook_id\":\"wh-new\"}";
        stubFor(post(urlPathEqualTo(WEBHOOKS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        AccountWebhookRequest request = AccountWebhookRequest.builder()
                .eventType(WebhookName.CLICKED.getValue())
                .eventType(WebhookName.DELIVERED.getValue())
                .url("https://example.com/wh")
                .description("my webhook")
                .build();
        AccountWebhookCreatedResult result = api.createWebhook(request);

        assertNotNull(result);
        assertEquals("wh-new", result.getWebhookId());
    }

    @Test
    void createWebhookWithAcceptedEventTypeSuccessTest() {
        String body = "{\"webhook_id\":\"wh-accepted\"}";
        stubFor(post(urlPathEqualTo(WEBHOOKS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        AccountWebhookRequest request = AccountWebhookRequest.builder()
                .eventType(WebhookName.ACCEPTED.getValue())
                .url("https://example.com/wh")
                .build();
        AccountWebhookCreatedResult result = api.createWebhook(request);

        assertNotNull(result);
        assertEquals("wh-accepted", result.getWebhookId());
    }

    @Test
    void updateWebhookSuccessTest() {
        String webhookId = "wh-42";
        String body = "{\"webhook_id\":\"wh-42\"}";
        stubFor(put(urlPathEqualTo(WEBHOOKS_PATH + "/" + webhookId))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        AccountWebhookRequest request = AccountWebhookRequest.builder()
                .eventType(WebhookName.OPENED.getValue())
                .url("https://example.com/updated")
                .description("updated description")
                .build();
        AccountWebhookCreatedResult result = api.updateWebhook(webhookId, request);

        assertNotNull(result);
        assertEquals("wh-42", result.getWebhookId());
    }

    @Test
    void deleteWebhookByIdSuccessTest() {
        String webhookId = "wh-42";
        stubFor(delete(urlPathEqualTo(WEBHOOKS_PATH + "/" + webhookId))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)));

        api.deleteWebhook(webhookId);
    }

    @Test
    void deleteWebhooksByIdsSuccessTest() {
        stubFor(delete(urlEqualTo(WEBHOOKS_PATH + "?webhook_ids=wh-1%2Cwh-2"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)));

        AccountWebhooksDeleteQuery query = AccountWebhooksDeleteQuery.builder()
                .webhook_ids("wh-1,wh-2")
                .build();
        api.deleteWebhooks(query);
    }

    @Test
    void deleteAllWebhooksSuccessTest() {
        stubFor(delete(urlEqualTo(WEBHOOKS_PATH + "?all=true"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)));

        AccountWebhooksDeleteQuery query = AccountWebhooksDeleteQuery.builder()
                .all(true)
                .build();
        api.deleteWebhooks(query);
    }

}
