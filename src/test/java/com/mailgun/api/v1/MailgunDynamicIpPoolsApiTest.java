package com.mailgun.api.v1;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.dynamicpools.DynamicPoolDomainsQuery;
import com.mailgun.model.dynamicpools.DynamicPoolDomainsResponse;
import com.mailgun.model.dynamicpools.DynamicPoolDomainPreviewResponse;
import com.mailgun.model.dynamicpools.DynamicPoolHistoryListResponse;
import com.mailgun.model.dynamicpools.DynamicPoolHistoryQuery;
import com.mailgun.model.dynamicpools.DynamicPoolHistoryRecord;
import com.mailgun.model.dynamicpools.DynamicPoolOverrideRequest;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunDynamicIpPoolsApiTest extends WireMockBaseTest {

    private static final String BASE = "/" + MailgunDynamicIpPoolsApi.getApiVersion().getValue() + "/dynamic_pools";

    private MailgunDynamicIpPoolsApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunDynamicIpPoolsApi.class);
    }

    @Test
    void listDomainsTest() {
        String body = "{\"items\":[{\"id\":\"d1\",\"account_id\":\"a1\",\"account_name\":\"acct\","
                + "\"name\":\"" + TEST_DOMAIN + "\",\"registered_at\":\"2024-01-01T00:00:00Z\","
                + "\"pool\":\"dynamic_good\",\"override\":false}],\"total_items\":1,"
                + "\"paging\":{\"First\":\"\",\"Last\":\"\",\"Next\":\"\",\"Previous\":\"\"}}";
        stubFor(get(urlPathEqualTo(BASE + "/domains"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("limit", equalTo("10"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        DynamicPoolDomainsResponse result = api.listDomains(
                DynamicPoolDomainsQuery.builder().limit(10).build());

        assertEquals(Integer.valueOf(1), result.getTotalItems());
        assertEquals(TEST_DOMAIN, result.getItems().get(0).getName());
    }

    @Test
    void previewDomainTest() {
        String body = "{\"pool\":\"dynamic_good\",\"message\":\"success\"}";
        stubFor(get(urlPathEqualTo(BASE + "/domains/" + TEST_DOMAIN + "/preview"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        DynamicPoolDomainPreviewResponse result = api.previewDomain(TEST_DOMAIN);

        assertEquals("dynamic_good", result.getPool());
    }

    @Test
    void getDomainHistoryTest() {
        String body = "[{\"id\":\"h1\",\"owning_account_id\":\"o1\",\"account_id\":\"a1\","
                + "\"account_name\":\"acct\",\"domain_id\":\"d1\",\"domain_name\":\"" + TEST_DOMAIN + "\","
                + "\"new_band\":\"dynamic_good\",\"prev_band\":\"dynamic_new\",\"reason\":\"health\","
                + "\"bounce_rate\":0.01,\"timestamp\":\"2024-01-01T00:00:00Z\"}]";
        stubFor(get(urlPathEqualTo(BASE + "/domains/" + TEST_DOMAIN + "/history"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        List<DynamicPoolHistoryRecord> result = api.getDomainHistory(TEST_DOMAIN);

        assertEquals(1, result.size());
        assertEquals("dynamic_good", result.get(0).getNewBand());
    }

    @Test
    void overrideDomainPoolTest() {
        stubFor(put(urlPathEqualTo(BASE + "/domains/" + TEST_DOMAIN + "/override"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"success\"}")));

        DynamicPoolOverrideRequest request = DynamicPoolOverrideRequest.builder()
                .pool("dynamic_good")
                .build();
        ResponseWithMessage result = api.overrideDomainPool(TEST_DOMAIN, request);

        assertEquals("success", result.getMessage());
    }

    @Test
    void removeDomainOverrideTest() {
        stubFor(delete(urlPathEqualTo(BASE + "/domains/" + TEST_DOMAIN + "/override"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"success\"}")));

        ResponseWithMessage result = api.removeDomainOverride(TEST_DOMAIN);

        assertEquals("success", result.getMessage());
    }

    @Test
    void listHistoryTest() {
        String body = "{\"items\":[{\"id\":\"h1\",\"owning_account_id\":\"o1\",\"account_id\":\"a1\","
                + "\"account_name\":\"acct\",\"domain_id\":\"d1\",\"domain_name\":\"" + TEST_DOMAIN + "\","
                + "\"new_band\":\"dynamic_good\",\"prev_band\":\"dynamic_new\",\"reason\":\"health\","
                + "\"bounce_rate\":0.01,\"timestamp\":\"2024-01-01T00:00:00Z\"}],\"total_items\":1,"
                + "\"paging\":{\"First\":\"\",\"Last\":\"\",\"Next\":\"\",\"Previous\":\"\"}}";
        stubFor(get(urlPathEqualTo(BASE + "/history"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("Limit", equalTo("25"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        DynamicPoolHistoryListResponse result = api.listHistory(
                DynamicPoolHistoryQuery.builder().Limit(25).build());

        assertNotNull(result.getItems());
        assertEquals(1, result.getItems().size());
    }

}
