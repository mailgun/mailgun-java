package com.mailgun.api.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailgun.api.ResponseSampleLoaderUtil;
import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.metrics.AccountMetrics;
import com.mailgun.model.metrics.AccountUsageMetrics;
import com.mailgun.model.metrics.MetricsRequest;
import com.mailgun.model.metrics.MetricsResponse;
import com.mailgun.util.ObjectMapperUtil;
import com.mailgun.utils.TestHeadersUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MailgunAccountMetricsApiTest extends WireMockBaseTest {

    private MailgunAccountMetricsApi mailgunAccountMetricsApi;

    @BeforeEach
    void setUp() {
        mailgunAccountMetricsApi  = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunAccountMetricsApi.class);
    }

    @Test
    void getAccountMetricsTest() throws JsonProcessingException {
        String responseFromTheFile = ResponseSampleLoaderUtil.fromFile("metrics/account-metrics.json");
        stubFor(post(urlEqualTo("/" + ApiVersion.V_1.getValue() + "/analytics/metrics"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", containing("application/json"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseFromTheFile)));

        ObjectMapper objectMapper = ObjectMapperUtil.getObjectMapper();
        MetricsResponse<AccountMetrics> metrics = mailgunAccountMetricsApi.getMetrics(MetricsRequest.builder().build());

        JsonNode expectedJson = objectMapper.readTree(responseFromTheFile);
        JsonNode actualJson = objectMapper.valueToTree(metrics);

        assertEquals(expectedJson, actualJson);
    }

    @Test
    void getAccountUsageMetricsTest() throws JsonProcessingException {
        String responseFromTheFile = ResponseSampleLoaderUtil.fromFile("metrics/account-usage-metrics.json");
        stubFor(post(urlEqualTo("/" + ApiVersion.V_1.getValue() + "/analytics/usage/metrics"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", containing("application/json"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseFromTheFile)));

        ObjectMapper objectMapper = ObjectMapperUtil.getObjectMapper();
        MetricsResponse<AccountUsageMetrics> metrics = mailgunAccountMetricsApi.getUsageMetrics(MetricsRequest.builder().build());

        JsonNode expectedJson = objectMapper.readTree(responseFromTheFile);
        JsonNode actualJson = objectMapper.valueToTree(metrics);

        assertEquals(expectedJson, actualJson);
    }
}
