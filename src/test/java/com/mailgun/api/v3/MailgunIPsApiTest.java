package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.ippools.DomainIpRemovalQuery;
import com.mailgun.model.ippools.IpPoolConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static com.mailgun.constants.TestConstants.TEST_IP_1;
import com.mailgun.utils.TestHeadersUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MailgunIPsApiTest extends WireMockBaseTest {

    private static final String API_BASE = "/" + MailgunApi.getApiVersion().getValue();
    private static final String DOMAIN_IPS_PATH = API_BASE + "/domains/" + TEST_DOMAIN + "/ips";
    private static final String DOMAIN_POOL_PATH = API_BASE + "/domains/" + TEST_DOMAIN + "/pool";

    private MailgunIPsApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunIPsApi.class);
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
