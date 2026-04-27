package com.mailgun.api.v3.suppression;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.suppression.complaints.ComplaintsItemResponse;
import com.mailgun.model.suppression.complaints.ComplaintsListQuery;
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

class MailgunSuppressionComplaintsApiTest extends WireMockBaseTest {

    private static final String API_VERSION = ApiVersion.V_3.getValue();
    private static final String DOMAIN = "mg.example.com";
    private static final String BASE = "/" + API_VERSION + "/" + DOMAIN + "/complaints";

    private MailgunSuppressionComplaintsApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunSuppressionComplaintsApi.class);
    }

    @Test
    void getAllComplaintsWithQueryMapSuccessTest() {
        stubFor(get(urlEqualTo(BASE + "?limit=20&term=spam"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"items\":[{\"address\":\"spam@example.com\"}],"
                                + "\"paging\":{\"next\":\"n\",\"previous\":\"p\",\"first\":\"f\",\"last\":\"l\"}}")));

        ComplaintsItemResponse r = api.getAllComplaints(DOMAIN, ComplaintsListQuery.builder()
                .limit(20)
                .term("spam")
                .build());

        assertNotNull(r.getItems());
        assertEquals(1, r.getItems().size());
        assertEquals("spam@example.com", r.getItems().get(0).getAddress());
        assertNotNull(r.getPaging());
        assertEquals("n", r.getPaging().getNext());
    }

    @Test
    void clearAllComplaintsSuccessTest() {
        stubFor(delete(urlPathEqualTo(BASE))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"message\":\"cleared\"}")));

        ResponseWithMessage r = api.clearAllComplaints(DOMAIN);
        assertEquals("cleared", r.getMessage());
    }

}
