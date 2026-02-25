package com.mailgun.api.v1;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.dkimsecurity.DkimRotationDomain;
import com.mailgun.model.dkimsecurity.DkimRotationRecord;
import com.mailgun.model.dkimsecurity.DkimRotationUpdateRequest;
import com.mailgun.model.dkimsecurity.DkimRotationUpdateResponse;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunDkimSecurityApiTest extends WireMockBaseTest {

    private MailgunDkimSecurityApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunDkimSecurityApi.class);
    }

    @Test
    void updateRotationSuccessTest() {
        String path = "/" + MailgunDkimSecurityApi.getApiVersion().getValue() + "/dkim_management/domains/" + TEST_DOMAIN + "/rotation";
        String body = "{\"domain\":{\"id\":\"dom-1\",\"account_id\":\"acc-1\",\"sid\":\"sid-1\",\"name\":\"" + TEST_DOMAIN + "\",\"state\":\"active\",\"active_selector\":\"mg\",\"rotation_enabled\":\"true\",\"rotation_interval\":\"5d\",\"records\":[{\"type\":\"CNAME\",\"identifier\":\"selector._domainkey\",\"value\":\"selector.example.com\",\"comment\":\"DKIM\"}]}}";
        stubFor(put(urlPathEqualTo(path))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        DkimRotationUpdateRequest request = DkimRotationUpdateRequest.builder()
                .rotationEnabled(true)
                .rotationInterval("5d")
                .build();
        DkimRotationUpdateResponse result = api.updateRotation(TEST_DOMAIN, request);

        assertNotNull(result);
        DkimRotationDomain domain = result.getDomain();
        assertNotNull(domain);
        assertEquals("dom-1", domain.getId());
        assertEquals("acc-1", domain.getAccountId());
        assertEquals(TEST_DOMAIN, domain.getName());
        assertEquals("active", domain.getState());
        assertEquals("mg", domain.getActiveSelector());
        assertEquals("true", domain.getRotationEnabled());
        assertEquals("5d", domain.getRotationInterval());
        assertNotNull(domain.getRecords());
        assertEquals(1, domain.getRecords().size());
        DkimRotationRecord record = domain.getRecords().get(0);
        assertEquals("CNAME", record.getType());
        assertEquals("selector._domainkey", record.getIdentifier());
        assertEquals("selector.example.com", record.getValue());
        assertEquals("DKIM", record.getComment());
    }

    @Test
    void rotateSuccessTest() {
        String path = "/" + MailgunDkimSecurityApi.getApiVersion().getValue() + "/dkim_management/domains/" + TEST_DOMAIN + "/rotate";
        String body = "{\"message\":\"DKIM key rotation completed successfully\"}";
        stubFor(post(urlPathEqualTo(path))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        ResponseWithMessage result = api.rotate(TEST_DOMAIN);

        assertNotNull(result);
        assertEquals("DKIM key rotation completed successfully", result.getMessage());
    }
}
