package com.mailgun.api.v4;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.domainkeys.DomainKeyActivateResponse;
import com.mailgun.model.domainkeys.DomainKeysListResponseV4;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MailgunDomainKeysApiTest extends WireMockBaseTest {

    private MailgunDomainKeysApi mailgunDomainKeysApi;

    @BeforeEach
    void setUp() {
        mailgunDomainKeysApi = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunDomainKeysApi.class);
    }

    @Test
    void listDomainKeysSuccessTest() {
        String body = "{\"items\":[{\"signing_domain\":\"example.com\",\"selector\":\"k1\",\"active\":true,\"valid\":true}]}";
        stubFor(get(urlPathEqualTo("/" + MailgunDomainKeysApi.getApiVersion().getValue() + "/domains/" + TEST_DOMAIN + "/keys"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        DomainKeysListResponseV4 result = mailgunDomainKeysApi.listDomainKeys(TEST_DOMAIN);

        assertNotNull(result);
        assertNotNull(result.getItems());
        assertEquals(1, result.getItems().size());
        assertEquals("example.com", result.getItems().get(0).getSigningDomain());
        assertEquals("k1", result.getItems().get(0).getSelector());
        assertTrue(result.getItems().get(0).getActive());
        assertTrue(result.getItems().get(0).getValid());
    }

    @Test
    void activateDomainKeySuccessTest() {
        String body = "{\"message\":\"Key activated\",\"authority\":\"" + TEST_DOMAIN + "\",\"selector\":\"k1\",\"active\":true}";
        stubFor(put(urlPathEqualTo("/" + MailgunDomainKeysApi.getApiVersion().getValue() + "/domains/" + TEST_DOMAIN + "/keys/k1/activate"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        DomainKeyActivateResponse result = mailgunDomainKeysApi.activateDomainKey(TEST_DOMAIN, "k1");

        assertNotNull(result);
        assertEquals("Key activated", result.getMessage());
        assertNotNull(result.getAuthority());
        assertEquals("k1", result.getSelector());
        assertTrue(result.getActive());
    }

    @Test
    void deactivateDomainKeySuccessTest() {
        String body = "{\"message\":\"Key deactivated\",\"authority\":\"example.com\",\"selector\":\"k1\",\"active\":false}";
        stubFor(put(urlPathEqualTo("/" + MailgunDomainKeysApi.getApiVersion().getValue() + "/domains/" + TEST_DOMAIN + "/keys/k1/deactivate"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        DomainKeyActivateResponse result = mailgunDomainKeysApi.deactivateDomainKey(TEST_DOMAIN, "k1");

        assertNotNull(result);
        assertEquals("Key deactivated", result.getMessage());
        assertFalse(result.getActive());
    }
}
