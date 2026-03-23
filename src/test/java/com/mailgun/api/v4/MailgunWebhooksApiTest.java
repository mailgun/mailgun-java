package com.mailgun.api.v4;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.WebhookName;
import com.mailgun.model.webhooks.DomainWebhooksV4Request;
import com.mailgun.model.webhooks.WebhookListResult;
import com.mailgun.model.webhooks.Webhooks;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunWebhooksApiTest extends WireMockBaseTest {

    private static final String API_VERSION = MailgunWebhooksApi.getApiVersion().getValue();
    private static final String WEBHOOKS_PATH = "/" + API_VERSION + "/domains/" + TEST_DOMAIN + "/webhooks";

    private static final String WEBHOOKS_RESPONSE_BODY =
            "{\"webhooks\":{" +
            "\"accepted\":{\"urls\":[\"https://example.com/accepted\"]}," +
            "\"clicked\":{\"urls\":[\"https://example.com/hook\"]}," +
            "\"delivered\":{\"urls\":[\"https://example.com/hook\"]}," +
            "\"opened\":null," +
            "\"unsubscribed\":null," +
            "\"complained\":null," +
            "\"temporary_fail\":null," +
            "\"permanent_fail\":null" +
            "}}";

    private MailgunWebhooksApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunWebhooksApi.class);
    }

    @Test
    void createWebhooksSuccessTest() {
        stubFor(post(urlPathEqualTo(WEBHOOKS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(WEBHOOKS_RESPONSE_BODY)));

        DomainWebhooksV4Request request = DomainWebhooksV4Request.builder()
                .url("https://example.com/hook")
                .eventType(WebhookName.CLICKED.getValue())
                .eventType(WebhookName.DELIVERED.getValue())
                .build();

        WebhookListResult result = api.createWebhooks(TEST_DOMAIN, request);

        assertNotNull(result);
        Webhooks webhooks = result.getWebhooks();
        assertNotNull(webhooks);
        assertNotNull(webhooks.getClicked());
        assertEquals(1, webhooks.getClicked().getUrls().size());
        assertEquals("https://example.com/hook", webhooks.getClicked().getUrls().get(0));
        assertNotNull(webhooks.getDelivered());
        assertNotNull(webhooks.getAccepted());
        assertEquals("https://example.com/accepted", webhooks.getAccepted().getUrls().get(0));
    }

    @Test
    void createWebhooksWithAcceptedTypeSuccessTest() {
        stubFor(post(urlPathEqualTo(WEBHOOKS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(WEBHOOKS_RESPONSE_BODY)));

        DomainWebhooksV4Request request = DomainWebhooksV4Request.builder()
                .url("https://example.com/accepted")
                .eventType(WebhookName.ACCEPTED.getValue())
                .build();

        WebhookListResult result = api.createWebhooks(TEST_DOMAIN, request);

        assertNotNull(result);
        assertNotNull(result.getWebhooks());
        assertNotNull(result.getWebhooks().getAccepted());
    }

    @Test
    void updateWebhooksSuccessTest() {
        stubFor(put(urlPathEqualTo(WEBHOOKS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(WEBHOOKS_RESPONSE_BODY)));

        DomainWebhooksV4Request request = DomainWebhooksV4Request.builder()
                .url("https://example.com/hook")
                .eventType(WebhookName.CLICKED.getValue())
                .eventType(WebhookName.DELIVERED.getValue())
                .build();

        WebhookListResult result = api.updateWebhooks(TEST_DOMAIN, request);

        assertNotNull(result);
        assertNotNull(result.getWebhooks());
        assertNotNull(result.getWebhooks().getClicked());
    }

    @Test
    void deleteWebhooksSuccessTest() {
        stubFor(delete(urlPathEqualTo(WEBHOOKS_PATH))
                .withQueryParam("url", equalTo("https://example.com/hook"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(WEBHOOKS_RESPONSE_BODY)));

        WebhookListResult result = api.deleteWebhooks(TEST_DOMAIN, "https://example.com/hook");

        assertNotNull(result);
        assertNotNull(result.getWebhooks());
    }

    @Test
    void deleteMultipleWebhooksSuccessTest() {
        stubFor(delete(urlPathEqualTo(WEBHOOKS_PATH))
                .withQueryParam("url", equalTo("https://example.com/hook1,https://example.com/hook2"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(WEBHOOKS_RESPONSE_BODY)));

        WebhookListResult result = api.deleteWebhooks(TEST_DOMAIN, "https://example.com/hook1,https://example.com/hook2");

        assertNotNull(result);
        assertNotNull(result.getWebhooks());
    }

}
