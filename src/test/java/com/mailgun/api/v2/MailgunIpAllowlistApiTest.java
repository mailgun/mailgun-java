package com.mailgun.api.v2;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ipallowlist.IpAllowlistEntryRequest;
import com.mailgun.model.ipallowlist.IpAllowlistResponse;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MailgunIpAllowlistApiTest extends WireMockBaseTest {

    private static final String ALLOWLIST_PATH = "/v2/ip_whitelist";
    private static final String ADDRESS = "192.0.2.10";
    private static final String DESCRIPTION = "Office gateway";

    private MailgunIpAllowlistApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunIpAllowlistApi.class);
    }

    @Test
    void getIpAllowlistTest() {
        stubFor(get(urlEqualTo(ALLOWLIST_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(allowlistResponse(DESCRIPTION)));

        IpAllowlistResponse result = api.getIpAllowlist();

        assertEquals(1, result.getAddresses().size());
        assertEquals(ADDRESS, result.getAddresses().get(0).getIpAddress());
        assertEquals(DESCRIPTION, result.getAddresses().get(0).getDescription());
    }

    @Test
    void updateIpAllowlistEntryTest() {
        stubFor(put(urlPathEqualTo(ALLOWLIST_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", containing("multipart/form-data"))
                .withRequestBody(containing("name=\"address\""))
                .withRequestBody(containing(ADDRESS))
                .withRequestBody(containing("name=\"description\""))
                .withRequestBody(containing(DESCRIPTION))
                .willReturn(allowlistResponse(DESCRIPTION)));

        IpAllowlistResponse result = api.updateIpAllowlistEntry(request());

        assertEquals(DESCRIPTION, result.getAddresses().get(0).getDescription());
    }

    @Test
    void addIpAllowlistEntryTest() {
        stubFor(post(urlPathEqualTo(ALLOWLIST_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", containing("multipart/form-data"))
                .withRequestBody(containing("name=\"address\""))
                .withRequestBody(containing(ADDRESS))
                .withRequestBody(containing("name=\"description\""))
                .withRequestBody(containing(DESCRIPTION))
                .willReturn(allowlistResponse(DESCRIPTION)));

        IpAllowlistResponse result = api.addIpAllowlistEntry(request());

        assertEquals(ADDRESS, result.getAddresses().get(0).getIpAddress());
    }

    @Test
    void deleteIpAllowlistEntryTest() {
        stubFor(delete(urlEqualTo(ALLOWLIST_PATH + "?address=" + ADDRESS))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"addresses\":[]}")));

        IpAllowlistResponse result = api.deleteIpAllowlistEntry(ADDRESS);

        assertEquals(0, result.getAddresses().size());
    }

    private static IpAllowlistEntryRequest request() {
        return IpAllowlistEntryRequest.builder()
                .address(ADDRESS)
                .description(DESCRIPTION)
                .build();
    }

    private static com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder allowlistResponse(String description) {
        return aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"addresses\":[{\"ip_address\":\"" + ADDRESS
                        + "\",\"description\":\"" + description + "\"}]}");
    }
}
