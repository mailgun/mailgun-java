package com.mailgun.api.v1;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.domainkeys.CreateDomainKeyRequest;
import com.mailgun.model.domainkeys.CreateDomainKeyResponse;
import com.mailgun.model.domainkeys.DomainKeysListQuery;
import com.mailgun.model.domainkeys.DomainKeysListResponse;
import com.mailgun.utils.TestHeadersUtils;
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
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunDomainKeysApiTest extends WireMockBaseTest {

    private MailgunDomainKeysApi mailgunDomainKeysApi;

    @BeforeEach
    void setUp() {
        mailgunDomainKeysApi = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunDomainKeysApi.class);
    }

    @Test
    void listDomainKeysSuccessTest() {
        String body = "{\"items\":[{\"signing_domain\":\"example.com\",\"selector\":\"s1\"}],\"paging\":{\"next\":\"next-url\"}}";
        stubFor(get(urlPathEqualTo("/" + MailgunDomainKeysApi.getApiVersion().getValue() + "/dkim/keys"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        DomainKeysListResponse result = mailgunDomainKeysApi.listDomainKeys();

        assertNotNull(result);
        assertNotNull(result.getItems());
        assertEquals(1, result.getItems().size());
        assertEquals("example.com", result.getItems().get(0).getSigningDomain());
        assertEquals("s1", result.getItems().get(0).getSelector());
        assertNotNull(result.getPaging());
        assertEquals("next-url", result.getPaging().getNext());
    }

    @Test
    void listDomainKeysWithQuerySuccessTest() {
        String body = "{\"items\":[],\"paging\":{}}";
        stubFor(get(urlPathEqualTo("/" + MailgunDomainKeysApi.getApiVersion().getValue() + "/dkim/keys"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        DomainKeysListQuery query = DomainKeysListQuery.builder()
                .limit(50)
                .signing_domain(TEST_DOMAIN)
                .build();
        DomainKeysListResponse result = mailgunDomainKeysApi.listDomainKeys(query);

        assertNotNull(result);
        assertEquals(0, result.getItems().size());
    }

    @Test
    void createDomainKeySuccessTest() {
        String body = "{\"signing_domain\":\"example.com\",\"selector\":\"k1\",\"dns_record\":{\"name\":\"k1._domainkey\"}}";
        stubFor(post(urlPathEqualTo("/" + MailgunDomainKeysApi.getApiVersion().getValue() + "/dkim/keys"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        CreateDomainKeyRequest request = CreateDomainKeyRequest.builder()
                .signingDomain("example.com")
                .selector("k1")
                .bits(2048)
                .build();
        CreateDomainKeyResponse result = mailgunDomainKeysApi.createDomainKey(request);

        assertNotNull(result);
        assertEquals("example.com", result.getSigningDomain());
        assertEquals("k1", result.getSelector());
        assertNotNull(result.getDnsRecord());
    }

    @Test
    void deleteDomainKeySuccessTest() {
        stubFor(delete(urlEqualTo("/" + MailgunDomainKeysApi.getApiVersion().getValue() + "/dkim/keys?signing_domain=example.com&selector=k1"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"Key deleted\"}")));

        ResponseWithMessage result = mailgunDomainKeysApi.deleteDomainKey("example.com", "k1");

        assertNotNull(result);
        assertEquals("Key deleted", result.getMessage());
    }
}
