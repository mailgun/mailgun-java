package com.mailgun.api.v3.suppression;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.suppression.SuppressionResponse;
import com.mailgun.model.suppression.unsubscribe.UnsubscribeItemResponse;
import com.mailgun.model.suppression.unsubscribe.UnsubscribesListQuery;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunSuppressionUnsubscribeApiTest extends WireMockBaseTest {

    private static final String API_VERSION = ApiVersion.V_3.getValue();
    private static final String DOMAIN = "mg.example.com";
    private static final String BASE = "/" + API_VERSION + "/" + DOMAIN + "/unsubscribes";

    private MailgunSuppressionUnsubscribeApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunSuppressionUnsubscribeApi.class);
    }

    @Test
    void getAllUnsubscribeWithQueryMapSuccessTest() {
        stubFor(get(urlEqualTo(BASE + "?limit=50&term=user"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"items\":[{\"address\":\"user@example.com\",\"tags\":[\"*\"]}],"
                                + "\"paging\":{\"next\":\"n\",\"previous\":\"p\",\"first\":\"f\",\"last\":\"l\"}}")));

        UnsubscribeItemResponse r = api.getAllUnsubscribe(DOMAIN, UnsubscribesListQuery.builder()
                .limit(50)
                .term("user")
                .build());

        assertNotNull(r.getItems());
        assertEquals(1, r.getItems().size());
        assertEquals("user@example.com", r.getItems().get(0).getAddress());
        assertNotNull(r.getPaging());
        assertEquals("n", r.getPaging().getNext());
    }

    @Test
    void clearAllUnsubscribesSuccessTest() {
        stubFor(delete(urlPathEqualTo(BASE))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"message\":\"cleared\"}")));

        ResponseWithMessage r = api.clearAllUnsubscribes(DOMAIN);
        assertEquals("cleared", r.getMessage());
    }

    @Test
    void removeAddressFromUnsubscribeTagUsesQueryParamSuccessTest() {
        String address = "unsub@example.com";
        String tag = "news";
        stubFor(delete(urlEqualTo(BASE + "/unsub%40example.com?tag=" + tag))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"message\":\"ok\",\"address\":\"unsub@example.com\"}")));

        SuppressionResponse r = api.removeAddressFromUnsubscribeTag(DOMAIN, address, tag);
        assertEquals("ok", r.getMessage());
        assertEquals(address, r.getAddress());
    }

}
