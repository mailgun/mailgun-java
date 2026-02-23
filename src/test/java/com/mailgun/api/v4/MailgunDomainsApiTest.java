package com.mailgun.api.v4;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.domains.Domain;
import com.mailgun.model.domains.DomainListResponse;
import com.mailgun.model.domains.DomainResponse;
import com.mailgun.model.domains.DomainUpdateRequest;
import com.mailgun.model.domains.DomainsParametersFilter;
import com.mailgun.model.domains.SingleDomainResponse;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunDomainsApiTest extends WireMockBaseTest {

    private MailgunDomainsApi mailgunDomainsApi;

    @BeforeEach
    void setUp() {
        mailgunDomainsApi = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunDomainsApi.class);
    }

    @Test
    void getDomainsListSuccessTest() {
        String body = "{\"total_count\":1,\"items\":[{\"id\":\"id1\",\"name\":\"example.com\",\"state\":\"active\",\"type\":\"custom\"}]}";
        stubFor(get(urlPathEqualTo("/" + MailgunDomainsApi.getApiVersion().getValue() + "/domains"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        DomainListResponse result = mailgunDomainsApi.getDomainsList();

        assertNotNull(result);
        assertEquals(1, result.getTotalCount());
        assertEquals(1, result.getItems().size());
        Domain domain = result.getItems().get(0);
        assertEquals("id1", domain.getId());
        assertEquals("example.com", domain.getName());
        assertEquals("active", domain.getState());
    }

    @Test
    void getDomainsListWithFilterSuccessTest() {
        String body = "{\"total_count\":0,\"items\":[]}";
        stubFor(get(urlPathMatching("/" + MailgunDomainsApi.getApiVersion().getValue() + "/domains.*"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        DomainsParametersFilter filter = DomainsParametersFilter.builder()
                .state(com.mailgun.enums.DomainState.ACTIVE)
                .limit(100)
                .search("test")
                .includeSubaccounts(true)
                .build();
        DomainListResponse result = mailgunDomainsApi.getDomainsList(filter);

        assertNotNull(result);
        assertEquals(0, result.getTotalCount());
    }

    @Test
    void getSingleDomainSuccessTest() {
        String body = "{\"domain\":{\"id\":\"id1\",\"name\":\"example.com\",\"state\":\"active\"},\"receiving_dns_records\":[],\"sending_dns_records\":[]}";
        stubFor(get(urlPathEqualTo("/" + MailgunDomainsApi.getApiVersion().getValue() + "/domains/" + TEST_DOMAIN))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        SingleDomainResponse result = mailgunDomainsApi.getSingleDomain(TEST_DOMAIN);

        assertNotNull(result);
        assertNotNull(result.getDomain());
        assertEquals("example.com", result.getDomain().getName());
    }

    @Test
    void updateDomainSuccessTest() {
        String body = "{\"message\":\"Domain updated\",\"domain\":{\"name\":\"" + TEST_DOMAIN + "\"},\"receiving_dns_records\":[],\"sending_dns_records\":[]}";
        stubFor(put(urlPathEqualTo("/" + MailgunDomainsApi.getApiVersion().getValue() + "/domains/" + TEST_DOMAIN))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        DomainUpdateRequest request = DomainUpdateRequest.builder()
                .requireTls(true)
                .webScheme(com.mailgun.enums.WebScheme.HTTPS)
                .build();
        DomainResponse result = mailgunDomainsApi.updateDomain(TEST_DOMAIN, request);

        assertNotNull(result);
        assertEquals("Domain updated", result.getMessage());
        assertNotNull(result.getDomain());
        assertEquals(TEST_DOMAIN, result.getDomain().getName());
    }
}
