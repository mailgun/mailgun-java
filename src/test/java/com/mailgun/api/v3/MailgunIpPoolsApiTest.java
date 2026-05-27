package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.ippools.AddIpPoolIpsRequest;
import com.mailgun.model.ippools.CreateIpPoolRequest;
import com.mailgun.model.ippools.DeleteIpPoolQuery;
import com.mailgun.model.ippools.IpPoolCreateResponse;
import com.mailgun.model.ippools.IpPoolDelegationRequest;
import com.mailgun.model.ippools.IpPoolDetailsResponse;
import com.mailgun.model.ippools.IpPoolDomainsQuery;
import com.mailgun.model.ippools.IpPoolDomainsResponse;
import com.mailgun.model.ippools.IpPoolsListResponse;
import com.mailgun.model.ippools.UpdateIpPoolRequest;
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
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_IP_1;
import static com.mailgun.constants.TestConstants.TEST_IP_2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MailgunIpPoolsApiTest extends WireMockBaseTest {

    private static final String API_BASE = "/" + MailgunApi.getApiVersion().getValue();
    private static final String IP_POOLS_PATH = API_BASE + "/ip_pools";
    private static final String POOL_ID = "658041ae44842b99ee2eaa1b";
    private static final String POOL_PATH = IP_POOLS_PATH + "/" + POOL_ID;

    private MailgunIpPoolsApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunIpPoolsApi.class);
    }

    @Test
    void listIpPoolsTest() {
        String body = "{\"ip_pools\":[{\"description\":\"pool desc\",\"ips\":[\"1.0.0.1\"],"
                + "\"is_inherited\":false,\"is_linked\":true,\"name\":\"my-pool\",\"pool_id\":\"" + POOL_ID + "\"}],"
                + "\"message\":\"success\"}";
        stubFor(get(urlEqualTo(IP_POOLS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        IpPoolsListResponse result = api.listIpPools();

        assertNotNull(result);
        assertEquals("success", result.getMessage());
        assertEquals(1, result.getIpPools().size());
        assertEquals(POOL_ID, result.getIpPools().get(0).getPoolId());
        assertTrue(result.getIpPools().get(0).getIsLinked());
    }

    @Test
    void createIpPoolTest() {
        String body = "{\"message\":\"success\",\"pool_id\":\"" + POOL_ID + "\"}";
        stubFor(post(urlPathEqualTo(IP_POOLS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        CreateIpPoolRequest request = CreateIpPoolRequest.builder()
                .name("my-pool")
                .description("pool desc")
                .ip(Collections.singletonList(TEST_IP_1))
                .build();
        IpPoolCreateResponse result = api.createIpPool(request);

        assertNotNull(result);
        assertEquals("success", result.getMessage());
        assertEquals(POOL_ID, result.getPoolId());
    }

    @Test
    void getIpPoolTest() {
        String body = "{\"details\":{\"description\":\"desc\",\"ips\":[\"1.2.3.4\"],\"is_linked\":true,"
                + "\"linked_domains\":[\"a.example.com\"],\"name\":\"pool\",\"pool_id\":\"" + POOL_ID + "\"},"
                + "\"message\":\"success\"}";
        stubFor(get(urlEqualTo(POOL_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        IpPoolDetailsResponse result = api.getIpPool(POOL_ID);

        assertNotNull(result);
        assertEquals("success", result.getMessage());
        assertEquals(POOL_ID, result.getDetails().getPoolId());
        assertEquals(1, result.getDetails().getLinkedDomains().size());
    }

    @Test
    void deleteIpPoolWithReplacementQueryTest() {
        String body = "{\"message\":\"success\"}";
        stubFor(delete(urlPathEqualTo(POOL_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("ip", equalTo(TEST_IP_1))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        DeleteIpPoolQuery query = DeleteIpPoolQuery.builder().ip(TEST_IP_1).build();
        ResponseWithMessage result = api.deleteIpPool(POOL_ID, query);

        assertEquals("success", result.getMessage());
    }

    @Test
    void updateIpPoolTest() {
        String body = "{\"message\":\"success\"}";
        stubFor(patch(urlPathEqualTo(POOL_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        UpdateIpPoolRequest request = UpdateIpPoolRequest.builder()
                .name("renamed")
                .addIp(Collections.singletonList(TEST_IP_2))
                .build();
        ResponseWithMessage result = api.updateIpPool(POOL_ID, request);

        assertEquals("success", result.getMessage());
    }

    @Test
    void getIpPoolDomainsTest() {
        String body = "{\"domains\":[{\"id\":\"68928b8f90d20beb59afdc95\",\"name\":\"example.com\"}],"
                + "\"paging\":{\"first\":\"\",\"next\":\"\"}}";
        stubFor(get(urlPathEqualTo(POOL_PATH + "/domains"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("limit", equalTo("10"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        IpPoolDomainsQuery query = IpPoolDomainsQuery.builder().limit(10).build();
        IpPoolDomainsResponse result = api.getIpPoolDomains(POOL_ID, query);

        assertNotNull(result.getDomains());
        assertEquals(1, result.getDomains().size());
        assertEquals("example.com", result.getDomains().get(0).getName());
    }

    @Test
    void addIpToIpPoolTest() {
        String body = "{\"message\":\"started\"}";
        stubFor(put(urlEqualTo(POOL_PATH + "/ips/" + TEST_IP_1))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        ResponseWithMessage result = api.addIpToIpPool(POOL_ID, TEST_IP_1);

        assertEquals("started", result.getMessage());
    }

    @Test
    void removeIpFromIpPoolTest() {
        String body = "{\"message\":\"started\"}";
        stubFor(delete(urlEqualTo(POOL_PATH + "/ips/" + TEST_IP_1))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        ResponseWithMessage result = api.removeIpFromIpPool(POOL_ID, TEST_IP_1);

        assertEquals("started", result.getMessage());
    }

    @Test
    void addIpsToIpPoolTest() {
        String body = "{\"message\":\"started\"}";
        stubFor(post(urlEqualTo(POOL_PATH + "/ips.json"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        AddIpPoolIpsRequest request = AddIpPoolIpsRequest.builder()
                .ips(java.util.Arrays.asList(TEST_IP_1, TEST_IP_2))
                .build();
        ResponseWithMessage result = api.addIpsToIpPool(POOL_ID, request);

        assertEquals("started", result.getMessage());
    }

    @Test
    void delegateIpPoolTest() {
        String body = "{\"message\":\"success\"}";
        stubFor(put(urlPathEqualTo(POOL_PATH + "/delegate"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        IpPoolDelegationRequest request = IpPoolDelegationRequest.builder()
                .subaccountId("sub-123")
                .build();
        ResponseWithMessage result = api.delegateIpPool(POOL_ID, request);

        assertEquals("success", result.getMessage());
    }

    @Test
    void revokeIpPoolDelegationTest() {
        String body = "{\"message\":\"success\"}";
        stubFor(delete(urlPathEqualTo(POOL_PATH + "/delegate"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        IpPoolDelegationRequest request = IpPoolDelegationRequest.builder()
                .subaccountId("sub-123")
                .build();
        ResponseWithMessage result = api.revokeIpPoolDelegation(POOL_ID, request);

        assertEquals("success", result.getMessage());
    }

}
