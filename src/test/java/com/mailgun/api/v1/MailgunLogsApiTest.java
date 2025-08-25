package com.mailgun.api.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailgun.api.ResponseSampleLoaderUtil;
import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.logs.LogsRequest;
import com.mailgun.model.logs.LogsResponse;
import com.mailgun.util.ObjectMapperUtil;
import com.mailgun.utils.TestHeadersUtils;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;

class MailgunLogsApiTest extends WireMockBaseTest {

    private MailgunLogsApi mailgunLogsApi;

    @BeforeEach
    void setUp() {
        mailgunLogsApi = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunLogsApi.class);
    }

    @Test
    void getLogsTest() throws JsonProcessingException, JSONException {
        String expectedResponse = ResponseSampleLoaderUtil.fromFile("logs/logs-response.json");
        stubFor(post(urlEqualTo("/" + ApiVersion.V_1.getValue() + "/analytics/logs"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", containing("application/json"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(expectedResponse)));

        ObjectMapper objectMapper = ObjectMapperUtil.getObjectMapper();
        LogsResponse logs = mailgunLogsApi.getLogs(LogsRequest.builder().build());

        String actualResponse = objectMapper.writeValueAsString(logs);

        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }
}