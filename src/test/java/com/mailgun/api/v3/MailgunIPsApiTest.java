package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.ips.IpDomainsOperationResponse;
import com.mailgun.model.ips.IpDomainsQuery;
import com.mailgun.model.ips.IpDomainsResult;
import com.mailgun.model.ips.IPsResult;
import com.mailgun.model.ips.IpsDetailsAllResult;
import com.mailgun.model.ips.IpsListQuery;
import com.mailgun.model.ips.PlaceIpInBandRequest;
import com.mailgun.model.ips.RemoveIpFromAllDomainsQuery;
import com.mailgun.model.ippools.DomainIpRemovalQuery;
import com.mailgun.model.ippools.IpPoolConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static com.mailgun.constants.TestConstants.TEST_IP_1;
import static com.mailgun.constants.TestConstants.TEST_IP_2;
import com.mailgun.utils.TestHeadersUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MailgunIPsApiTest extends WireMockBaseTest {

    private static final String API_BASE = "/" + MailgunApi.getApiVersion().getValue();
    private static final String DOMAIN_IPS_PATH = API_BASE + "/domains/" + TEST_DOMAIN + "/ips";
    private static final String DOMAIN_POOL_PATH = API_BASE + "/domains/" + TEST_DOMAIN + "/pool";
    private static final String IPS_PATH = API_BASE + "/ips";

    private MailgunIPsApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunIPsApi.class);
    }

    @Test
    void getAllIPsWithQueryTest() {
        String body = "{\"assignable_to_pools\":[\"5.6.7.8\"],\"items\":[\"" + TEST_IP_1 + "\"],\"total_count\":1}";
        stubFor(get(urlPathEqualTo(IPS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("dedicated", equalTo("true"))
                .withQueryParam("enabled", equalTo("true"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        IPsResult result = api.getAllIPs(IpsListQuery.builder().dedicated(true).enabled(true).build());

        assertEquals(1, result.getTotalCount());
        assertNotNull(result.getAssignableToPools());
    }

    @Test
    void getIpDomainsTest() {
        String body = "{\"items\":[{\"domain\":\"" + TEST_DOMAIN + "\",\"ips\":[\"" + TEST_IP_1 + "\"]}],"
                + "\"total_count\":1}";
        stubFor(get(urlPathEqualTo(IPS_PATH + "/" + TEST_IP_1 + "/domains"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("limit", equalTo("10"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        IpDomainsResult result = api.getIpDomains(TEST_IP_1, IpDomainsQuery.builder().limit(10).build());

        assertEquals(1, result.getTotalCount());
        assertEquals(TEST_DOMAIN, result.getItems().get(0).getDomain());
    }

    @Test
    void assignIpToAllDomainsTest() {
        String body = "{\"message\":\"started\",\"reference_id\":\"ref-1\"}";
        stubFor(post(urlEqualTo(IPS_PATH + "/" + TEST_IP_1 + "/domains"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        IpDomainsOperationResponse result = api.assignIpToAllDomains(TEST_IP_1);

        assertEquals("started", result.getMessage());
        assertEquals("ref-1", result.getReferenceId());
    }

    @Test
    void removeIpFromAllDomainsWithAlternativeTest() {
        String body = "{\"message\":\"started\",\"reference_id\":\"ref-2\"}";
        stubFor(delete(urlPathEqualTo(IPS_PATH + "/" + TEST_IP_1 + "/domains"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("alternative", equalTo(TEST_IP_2))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        RemoveIpFromAllDomainsQuery query = RemoveIpFromAllDomainsQuery.builder()
                .alternative(TEST_IP_2)
                .build();
        IpDomainsOperationResponse result = api.removeIpFromAllDomains(TEST_IP_1, query);

        assertEquals("ref-2", result.getReferenceId());
    }

    @Test
    void placeIpInBandTest() {
        stubFor(post(urlPathEqualTo(IPS_PATH + "/" + TEST_IP_1 + "/ip_band"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"success\"}")));

        ResponseWithMessage result = api.placeIpInBand(TEST_IP_1,
                PlaceIpInBandRequest.builder().ipBand("band-a").build());

        assertEquals("success", result.getMessage());
    }

    @Test
    void getAllIpsDetailsTest() {
        String body = "{\"items\":[{\"address\":\"" + TEST_IP_1 + "\",\"parent_account_id\":\"p1\","
                + "\"account_id\":\"a1\",\"pool_ids\":[],\"dedicated\":true,"
                + "\"pool_last_modified_at\":\"2024-01-01T00:00:00Z\","
                + "\"domains_last_modified_at\":\"2024-01-01T00:00:00Z\"}],\"total_count\":1}";
        stubFor(get(urlEqualTo(IPS_PATH + "/details/all"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        IpsDetailsAllResult result = api.getAllIpsDetails();

        assertEquals(1, result.getTotalCount());
        assertEquals(TEST_IP_1, result.getItems().get(0).getAddress());
    }

    @Test
    void unassignIPFromDomainTest() {
        String body = "{\"message\":\"success\"}";
        stubFor(delete(urlPathEqualTo(DOMAIN_IPS_PATH + "/" + TEST_IP_1))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        ResponseWithMessage result = api.unassignIPFromDomain(TEST_DOMAIN, TEST_IP_1);

        assertEquals("success", result.getMessage());
    }

    @Test
    void unassignIPFromDomainWithUnlinkQueryTest() {
        String body = "{\"message\":\"success\"}";
        String replacementPoolId = "658041ae44842b99ee2eaa1b";
        stubFor(delete(urlPathEqualTo(DOMAIN_IPS_PATH + "/" + IpPoolConstants.DOMAIN_POOL_UNLINK_IP_POOL))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("pool_id", equalTo(replacementPoolId))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        DomainIpRemovalQuery query = DomainIpRemovalQuery.builder()
                .pool_id(replacementPoolId)
                .build();
        ResponseWithMessage result = api.unassignIPFromDomain(TEST_DOMAIN,
                IpPoolConstants.DOMAIN_POOL_UNLINK_IP_POOL, query);

        assertEquals("success", result.getMessage());
    }

    @Test
    void unassignIPFromDomainPoolTest() {
        String body = "{\"message\":\"success\"}";
        stubFor(delete(urlPathEqualTo(DOMAIN_POOL_PATH + "/" + IpPoolConstants.DOMAIN_POOL_REMOVE_ALL))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        ResponseWithMessage result = api.unassignIPFromDomainPool(TEST_DOMAIN,
                IpPoolConstants.DOMAIN_POOL_REMOVE_ALL);

        assertEquals("success", result.getMessage());
    }

}
