package com.mailgun.api.v2;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.domains.X509CertificateOperationResponse;
import com.mailgun.model.domains.X509CertificateStatusResponse;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunTrackingCertificateApiTest extends WireMockBaseTest {

    private MailgunTrackingCertificateApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunTrackingCertificateApi.class);
    }

    @Test
    void getCertificateStatusSuccessTest() {
        String body = "{\"status\":\"active\",\"error\":null,\"certificate\":\"-----BEGIN CERTIFICATE-----\\n...\"}";
        stubFor(get(urlPathEqualTo("/" + MailgunTrackingCertificateApi.getApiVersion().getValue() + "/x509/" + TEST_DOMAIN + "/status"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        X509CertificateStatusResponse result = api.getCertificateStatus(TEST_DOMAIN);

        assertNotNull(result);
        assertEquals("active", result.getStatus());
        assertEquals("-----BEGIN CERTIFICATE-----\\n...", result.getCertificate());
    }

    @Test
    void generateCertificateSuccessTest() {
        String body = "{\"message\":\"Certificate generation enqueued\",\"location\":\"/v2/x509/example.com/status\"}";
        stubFor(post(urlPathEqualTo("/" + MailgunTrackingCertificateApi.getApiVersion().getValue() + "/x509/" + TEST_DOMAIN))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(202)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        X509CertificateOperationResponse result = api.generateCertificate(TEST_DOMAIN);

        assertNotNull(result);
        assertEquals("Certificate generation enqueued", result.getMessage());
        assertEquals("/v2/x509/example.com/status", result.getLocation());
    }

    @Test
    void regenerateCertificateSuccessTest() {
        String body = "{\"message\":\"Certificate regeneration enqueued\",\"location\":\"/v2/x509/example.com/status\"}";
        stubFor(put(urlPathEqualTo("/" + MailgunTrackingCertificateApi.getApiVersion().getValue() + "/x509/" + TEST_DOMAIN))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(202)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        X509CertificateOperationResponse result = api.regenerateCertificate(TEST_DOMAIN);

        assertNotNull(result);
        assertEquals("Certificate regeneration enqueued", result.getMessage());
        assertNotNull(result.getLocation());
    }
}
