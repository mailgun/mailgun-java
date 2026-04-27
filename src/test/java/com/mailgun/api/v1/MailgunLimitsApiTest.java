package com.mailgun.api.v1;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.LimitMetric;
import com.mailgun.enums.SendAlertDimension;
import com.mailgun.enums.SendAlertFilterComparator;
import com.mailgun.enums.SendAlertThresholdComparator;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.limits.AccountLimitThreshold;
import com.mailgun.model.limits.AccountLimitThresholdRequest;
import com.mailgun.model.limits.AccountLimitsListResult;
import com.mailgun.model.sendalerts.SendAlertFilter;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunLimitsApiTest extends WireMockBaseTest {

    private static final String BASE = "/" + MailgunLimitsApi.getApiVersion().getValue() + "/thresholds/limits";

    private MailgunLimitsApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunLimitsApi.class);
    }

    @Test
    void listLimitThresholdsSuccessTest() {
        String body = "{\"items\":[{\"id\":\"lim-1\",\"name\":\"preview-cap\",\"created_at\":\"2024-01-01T00:00:00Z\","
                + "\"metric\":\"email_preview_success_count\",\"comparator\":\">=\",\"limit\":\"1000\",\"dimension\":\"subaccount\"}],"
                + "\"total\":1}";
        stubFor(get(urlPathEqualTo(BASE))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        AccountLimitsListResult r = api.listLimitThresholds();

        assertEquals(Integer.valueOf(1), r.getTotal());
        assertNotNull(r.getItems());
        assertEquals(1, r.getItems().size());
        AccountLimitThreshold t = r.getItems().get(0);
        assertEquals("lim-1", t.getId());
        assertEquals(LimitMetric.EMAIL_PREVIEW_SUCCESS_COUNT.getValue(), t.getMetric());
        assertEquals(SendAlertThresholdComparator.GE.getValue(), t.getComparator());
        assertEquals(SendAlertDimension.SUBACCOUNT.getValue(), t.getDimension());
    }

    @Test
    void createGetUpdateDeleteLimitThresholdSuccessTest() {
        String createBody = "{\"id\":\"new-lim\",\"name\":\"seed-cap\",\"created_at\":\"2024-02-01T00:00:00Z\","
                + "\"metric\":\"seed_test_count\",\"comparator\":\"<\",\"limit\":\"50\",\"dimension\":\"domain\","
                + "\"filters\":[{\"dimension\":\"domain\",\"comparator\":\"contains\",\"values\":[\"mg.example.com\"]}]}";
        stubFor(post(urlPathEqualTo(BASE))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(createBody)));

        AccountLimitThresholdRequest req = AccountLimitThresholdRequest.builder()
                .name("seed-cap")
                .metric(LimitMetric.SEED_TEST_COUNT.getValue())
                .comparator(SendAlertThresholdComparator.LT.getValue())
                .limit("50")
                .dimension(SendAlertDimension.DOMAIN.getValue())
                .filters(List.of(SendAlertFilter.builder()
                        .dimension(SendAlertDimension.DOMAIN.getValue())
                        .comparator(SendAlertFilterComparator.CONTAINS.getValue())
                        .values(List.of("mg.example.com"))
                        .build()))
                .period("1d")
                .description("cap seed tests")
                .build();

        AccountLimitThreshold created = api.createLimitThreshold(req);
        assertEquals("new-lim", created.getId());
        assertEquals(1, created.getFilters().size());

        String name = "seed-cap";
        stubFor(get(urlPathEqualTo(BASE + "/" + name))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(createBody)));

        assertEquals(name, api.getLimitThreshold(name).getName());

        stubFor(put(urlPathEqualTo(BASE + "/" + name))
                .willReturn(aResponse().withStatus(200).withBody("{\"message\":\"updated\"}")));

        ResponseWithMessage upd = api.updateLimitThreshold(name, req);
        assertEquals("updated", upd.getMessage());

        stubFor(delete(urlPathEqualTo(BASE + "/" + name))
                .willReturn(aResponse().withStatus(200).withBody("{\"message\":\"deleted\"}")));

        assertEquals("deleted", api.deleteLimitThreshold(name).getMessage());
    }

}
