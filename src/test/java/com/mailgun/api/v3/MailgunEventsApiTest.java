package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.EventType;
import com.mailgun.enums.Severity;
import com.mailgun.model.events.EventItem;
import com.mailgun.model.events.EventsQueryOptions;
import com.mailgun.model.events.EventsResponse;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MailgunEventsApiTest extends WireMockBaseTest {

    private static final String EVENTS_PATH = "/" + MailgunApi.getApiVersion().getValue() + "/" + TEST_DOMAIN + "/events";

    private MailgunEventsApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunEventsApi.class);
    }

    @Test
    void getAllEventsTest() {
        String body = "{\"items\":[{\"id\":\"evt-1\",\"event\":\"delivered\"}],\"paging\":{\"next\":\"https://api.mailgun.net/v3/next\",\"previous\":\"https://api.mailgun.net/v3/prev\"}}";
        stubFor(get(urlPathEqualTo(EVENTS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        EventsResponse result = api.getAllEvents(TEST_DOMAIN);

        assertNotNull(result);
        assertNotNull(result.getItems());
        assertEquals(1, result.getItems().size());
        assertEquals("evt-1", result.getItems().get(0).getId());
        assertEquals("delivered", result.getItems().get(0).getEvent());
    }

    @Test
    void getEventsWithQueryOptionsTest() {
        String body = "{\"items\":[{\"id\":\"evt-2\",\"event\":\"failed\",\"severity\":\"permanent\",\"recipient-provider\":\"Gmail\",\"template\":{\"name\":\"welcome\",\"version\":\"v1\"},\"batch\":{\"id\":\"batch-99\"}}],\"paging\":{}}";
        stubFor(get(urlPathEqualTo(EVENTS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("event", equalTo("failed"))
                .withQueryParam("severity", equalTo("permanent"))
                .withQueryParam("limit", equalTo("10"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        EventsQueryOptions options = EventsQueryOptions.builder()
                .event(EventType.FAILED)
                .severity(Severity.PERMANENT)
                .limit(10)
                .build();

        EventsResponse result = api.getEvents(TEST_DOMAIN, options);

        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        EventItem item = result.getItems().get(0);
        assertEquals("evt-2", item.getId());
        assertEquals("Gmail", item.getRecipientProvider());
        assertNotNull(item.getTemplate());
        assertEquals("welcome", item.getTemplate().getName());
        assertEquals("v1", item.getTemplate().getVersion());
        assertNotNull(item.getBatch());
        assertEquals("batch-99", item.getBatch().getId());
    }

    @Test
    void getEventsWithListFilterTest() {
        String body = "{\"items\":[{\"id\":\"evt-3\",\"event\":\"delivered\"}],\"paging\":{}}";
        stubFor(get(urlPathEqualTo(EVENTS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("list", equalTo("mylist@" + TEST_DOMAIN))
                .withQueryParam("recipient", equalTo("user@example.com"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        EventsQueryOptions options = EventsQueryOptions.builder()
                .list("mylist@" + TEST_DOMAIN)
                .recipient("user@example.com")
                .build();

        EventsResponse result = api.getEvents(TEST_DOMAIN, options);

        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertEquals("evt-3", result.getItems().get(0).getId());
    }

    @Test
    void getEventsByPageIdTest() {
        String pageId = "W3siYSI6ZmFsc2UsImIiOiIifV0";
        String body = "{\"items\":[],\"paging\":{}}";
        stubFor(get(urlPathEqualTo(EVENTS_PATH + "/" + pageId))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        EventsResponse result = api.getEvents(TEST_DOMAIN, pageId);

        assertNotNull(result);
        assertTrue(result.getItems().isEmpty());
    }

    @Test
    void getEventsWithDeliveryStatusFieldsTest() {
        String body = "{\"items\":[{\"id\":\"evt-4\",\"event\":\"delivered\",\"delivery-status\":{\"code\":250,\"tls\":true,\"enhanced-code\":\"2.0.0\",\"first-delivery-attempt-seconds\":1.5,\"attempt-no\":1}}],\"paging\":{}}";
        stubFor(get(urlPathEqualTo(EVENTS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        EventsResponse result = api.getAllEvents(TEST_DOMAIN);

        assertNotNull(result);
        EventItem item = result.getItems().get(0);
        assertNotNull(item.getDeliveryStatus());
        assertEquals(250, item.getDeliveryStatus().getCode());
        assertEquals("2.0.0", item.getDeliveryStatus().getEnhancedCode());
        assertEquals(1.5, item.getDeliveryStatus().getFirstDeliveryAttemptSeconds());
    }

    @Test
    void getEventsWithFlagsTest() {
        String body = "{\"items\":[{\"id\":\"evt-5\",\"event\":\"stored\",\"flags\":{\"is-authenticated\":true,\"is-amp\":true,\"is-encrypted\":false,\"is-test-mode\":false}}],\"paging\":{}}";
        stubFor(get(urlPathEqualTo(EVENTS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        EventsResponse result = api.getAllEvents(TEST_DOMAIN);

        assertNotNull(result);
        EventItem item = result.getItems().get(0);
        assertNotNull(item.getFlags());
        assertTrue(item.getFlags().getIsAuthenticated());
        assertTrue(item.getFlags().getIsAmp());
        assertEquals(false, item.getFlags().getIsEncrypted());
    }
}
