package com.mailgun.api.v5;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.custommessagelimit.CustomMessageLimitResponse;
import com.mailgun.model.custommessagelimit.CustomMessageLimitSuccessResponse;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MailgunCustomMessageLimitApiTest extends WireMockBaseTest {

    private static final String LIMIT_PATH = "/" + ApiVersion.V_5.getValue() + "/accounts/limit/custom";
    private static final String MONTHLY_LIMIT_PATH = LIMIT_PATH + "/monthly";

    private MailgunCustomMessageLimitApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunCustomMessageLimitApi.class);
    }

    @Test
    void getCustomMessageLimitTest() {
        stubFor(get(urlEqualTo(MONTHLY_LIMIT_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(jsonResponse("{\"limit\":10000,\"current\":2500,\"period\":\"1m\"}")));

        CustomMessageLimitResponse result = api.getCustomMessageLimit();

        assertEquals(new BigDecimal("10000"), result.getLimit());
        assertEquals(new BigDecimal("2500"), result.getCurrent());
        assertEquals("1m", result.getPeriod());
    }

    @Test
    void setCustomMessageLimitTest() {
        stubFor(put(urlPathEqualTo(MONTHLY_LIMIT_PATH))
                .withQueryParam("limit", equalTo("5000"))
                .willReturn(jsonResponse("{\"success\":true}")));

        CustomMessageLimitSuccessResponse result = api.setCustomMessageLimit(new BigDecimal("5000"));

        assertTrue(result.getSuccess());
    }

    @Test
    void deleteCustomMessageLimitTest() {
        stubFor(delete(urlEqualTo(MONTHLY_LIMIT_PATH))
                .willReturn(jsonResponse("{\"success\":true}")));

        CustomMessageLimitSuccessResponse result = api.deleteCustomMessageLimit();

        assertTrue(result.getSuccess());
    }

    @Test
    void enableAccountTest() {
        stubFor(put(urlEqualTo(LIMIT_PATH + "/enable"))
                .willReturn(jsonResponse("{\"success\":true}")));

        CustomMessageLimitSuccessResponse result = api.enableAccount();

        assertTrue(result.getSuccess());
    }

    private static com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder jsonResponse(String body) {
        return aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(body);
    }

}
