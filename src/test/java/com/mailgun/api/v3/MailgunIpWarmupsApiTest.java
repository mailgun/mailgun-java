package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.ipwarmups.IpWarmupDetailsResponse;
import com.mailgun.model.ipwarmups.IpWarmupsListQuery;
import com.mailgun.model.ipwarmups.IpWarmupsListResponse;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_IP_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunIpWarmupsApiTest extends WireMockBaseTest {

    private static final String API_BASE = "/" + MailgunApi.getApiVersion().getValue();
    private static final String IP_WARMUPS = API_BASE + "/ip_warmups";
    private static final String IP_WARMUP = IP_WARMUPS + "/" + TEST_IP_1;

    private MailgunIpWarmupsApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunIpWarmupsApi.class);
    }

    @Test
    void listIpWarmupsTest() {
        String body = "{\"items\":[{\"ip\":\"" + TEST_IP_1 + "\","
                + "\"sent_within_stage\":\"25%\",\"throttle\":90,\"stage_number\":4,"
                + "\"stage_start_volume\":10000,\"stage_start_time\":\"2025-01-01T00:00:00Z\","
                + "\"stage_volume_limit\":8000}],\"paging\":{\"first\":\"first\",\"next\":\"next\"}}";
        stubFor(get(urlPathEqualTo(IP_WARMUPS))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("page", equalTo("next"))
                .withQueryParam("limit", equalTo("10"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        IpWarmupsListResponse result = api.listIpWarmups(
                IpWarmupsListQuery.builder().page("next").limit("10").build());

        assertEquals(1, result.getItems().size());
        assertEquals(TEST_IP_1, result.getItems().get(0).getIp());
        assertEquals(Instant.parse("2025-01-01T00:00:00Z"), result.getItems().get(0).getStageStartTime());
        assertEquals("next", result.getPaging().getNext());
    }

    @Test
    void listIpWarmupsWithoutQueryTest() {
        stubFor(get(urlEqualTo(IP_WARMUPS))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"items\":[],\"paging\":{\"first\":\"first\"}}")));

        IpWarmupsListResponse result = api.listIpWarmups();

        assertNotNull(result.getItems());
        assertEquals("first", result.getPaging().getFirst());
    }

    @Test
    void getIpWarmupTest() {
        String body = "{\"details\":{\"sent_within_stage\":\"20%\",\"throttle\":78,"
                + "\"start_volume\":10000,\"stage\":2,\"stage_limit\":4000,\"hourly_limit\":100,"
                + "\"stage_started_at\":\"2025-01-01T00:00:00Z\","
                + "\"hour_started_at\":\"2025-01-01T00:00:00Z\","
                + "\"plan_started_at\":\"2025-01-01T00:00:00Z\","
                + "\"plan_last_updated_at\":\"2025-01-01T00:00:00Z\",\"total_stages\":15,"
                + "\"stage_history\":[{\"first_updated_at\":\"0001-01-01T00:00:00Z\","
                + "\"completed_at\":\"2025-06-03T21:33:55.000000123Z\",\"limit\":1000}]}}";
        stubFor(get(urlEqualTo(IP_WARMUP))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        IpWarmupDetailsResponse result = api.getIpWarmup(TEST_IP_1);

        assertEquals(2, result.getDetails().getStage());
        assertEquals(10000, result.getDetails().getStartVolume());
        assertEquals(4000, result.getDetails().getStageLimit());
        assertEquals(15, result.getDetails().getTotalStages());
        assertEquals(1, result.getDetails().getStageHistory().size());
        assertEquals(1000, result.getDetails().getStageHistory().get(0).getLimit());
    }

    @Test
    void createIpWarmupTest() {
        stubFor(post(urlEqualTo(IP_WARMUP))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(successResponse()));

        ResponseWithMessage result = api.createIpWarmup(TEST_IP_1);

        assertEquals("success", result.getMessage());
    }

    @Test
    void cancelIpWarmupTest() {
        stubFor(delete(urlEqualTo(IP_WARMUP))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(successResponse()));

        ResponseWithMessage result = api.cancelIpWarmup(TEST_IP_1);

        assertEquals("success", result.getMessage());
    }

    private static com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder successResponse() {
        return aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"message\":\"success\"}");
    }

}
