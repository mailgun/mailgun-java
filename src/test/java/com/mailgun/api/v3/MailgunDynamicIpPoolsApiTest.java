package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.dynamicpools.AssignableDomainsQuery;
import com.mailgun.model.dynamicpools.AssignableDomainsResponse;
import com.mailgun.model.dynamicpools.DynamicPoolsListResponse;
import com.mailgun.model.dynamicpools.EnrollAllDomainsInDynamicPoolsQuery;
import com.mailgun.model.dynamicpools.RemoveDomainFromDynamicPoolQuery;
import com.mailgun.model.dynamicpools.UpdateDynamicPoolRequest;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.patch;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static com.mailgun.constants.TestConstants.TEST_IP_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunDynamicIpPoolsApiTest extends WireMockBaseTest {

    private static final String API_BASE = "/" + MailgunApi.getApiVersion().getValue();
    private static final String DOMAIN_DYNAMIC_POOLS = API_BASE + "/domains/" + TEST_DOMAIN + "/dynamic_pools";
    private static final String POOL_NAME = "dynamic_good";

    private MailgunDynamicIpPoolsApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunDynamicIpPoolsApi.class);
    }

    @Test
    void enrollDomainTest() {
        stubFor(post(urlPathEqualTo(DOMAIN_DYNAMIC_POOLS))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"success\"}")));

        ResponseWithMessage result = api.enrollDomain(TEST_DOMAIN);

        assertEquals("success", result.getMessage());
    }

    @Test
    void removeDomainWithReplacementPoolTest() {
        String poolId = "658041ae44842b99ee2eaa1b";
        stubFor(delete(urlPathEqualTo(DOMAIN_DYNAMIC_POOLS))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("replacement_pool_id", equalTo(poolId))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"success\"}")));

        RemoveDomainFromDynamicPoolQuery query = RemoveDomainFromDynamicPoolQuery.builder()
                .replacement_pool_id(poolId)
                .build();
        ResponseWithMessage result = api.removeDomain(TEST_DOMAIN, query);

        assertEquals("success", result.getMessage());
    }

    @Test
    void listAssignableDomainsTest() {
        String body = "{\"items\":[{\"name\":\"other.example.com\"}],\"message\":\"success\"}";
        stubFor(get(urlPathEqualTo(API_BASE + "/domains/dynamic_pools/assignable"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("domain", equalTo("example"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        AssignableDomainsResponse result = api.listAssignableDomains(
                AssignableDomainsQuery.builder().domain("example").build());

        assertNotNull(result.getItems());
        assertEquals(1, result.getItems().size());
    }

    @Test
    void enrollAllDomainsTest() {
        stubFor(post(urlPathEqualTo(API_BASE + "/domains/all/dynamic_pools/enroll"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("include_subaccounts", equalTo("true"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"started\"}")));

        ResponseWithMessage result = api.enrollAllDomains(
                EnrollAllDomainsInDynamicPoolsQuery.builder().include_subaccounts(true).build());

        assertEquals("started", result.getMessage());
    }

    @Test
    void listDynamicPoolsTest() {
        String body = "{\"dynamic_good\":[\"" + TEST_IP_1 + "\"],\"message\":\"success\"}";
        stubFor(get(urlEqualTo(API_BASE + "/dynamic_pools"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        DynamicPoolsListResponse result = api.listDynamicPools();

        assertEquals("success", result.getMessage());
        assertEquals(1, result.getDynamicGood().size());
    }

    @Test
    void addIpToDynamicPoolTest() {
        stubFor(post(urlEqualTo(API_BASE + "/dynamic_pools/" + POOL_NAME + "/" + TEST_IP_1))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"success\"}")));

        ResponseWithMessage result = api.addIpToDynamicPool(POOL_NAME, TEST_IP_1);

        assertEquals("success", result.getMessage());
    }

    @Test
    void updateDynamicPoolTest() {
        stubFor(patch(urlPathEqualTo(API_BASE + "/dynamic_pools/" + POOL_NAME))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"success\"}")));

        UpdateDynamicPoolRequest request = UpdateDynamicPoolRequest.builder()
                .addIp(Collections.singletonList(TEST_IP_1))
                .build();
        ResponseWithMessage result = api.updateDynamicPool(POOL_NAME, request);

        assertEquals("success", result.getMessage());
    }

    @Test
    void removeAllDynamicPoolsTest() {
        stubFor(delete(urlEqualTo(API_BASE + "/dynamic_pools/all"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"success\"}")));

        ResponseWithMessage result = api.removeAllDynamicPools();

        assertEquals("success", result.getMessage());
    }

}
