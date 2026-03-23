package com.mailgun.api.v2;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.bounceclassification.BounceClassificationItem;
import com.mailgun.model.bounceclassification.BounceClassificationPagination;
import com.mailgun.model.bounceclassification.BounceClassificationRequest;
import com.mailgun.model.bounceclassification.BounceClassificationResponse;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunBounceClassificationApiTest extends WireMockBaseTest {

    private static final String API_VERSION = MailgunBounceClassificationApi.getApiVersion().getValue();
    private static final String METRICS_PATH = "/" + API_VERSION + "/bounce-classification/metrics";

    private MailgunBounceClassificationApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunBounceClassificationApi.class);
    }

    @Test
    void getMetricsSuccessTest() {
        String body = "{" +
                "\"start\":\"Mon, 01 Jan 2024 00:00:00 +0000\"," +
                "\"end\":\"Mon, 08 Jan 2024 00:00:00 +0000\"," +
                "\"resolution\":\"day\"," +
                "\"duration\":\"7d\"," +
                "\"dimensions\":[\"domain.name\"]," +
                "\"pagination\":{\"sort\":\"classified_failures_count:desc\",\"skip\":0,\"limit\":10,\"total\":2}," +
                "\"items\":[" +
                "{\"dimensions\":[{\"dimension\":\"domain.name\",\"value\":\"example.com\",\"display_value\":\"example.com\"}]," +
                "\"metrics\":{\"classified_failures_count\":42}}," +
                "{\"dimensions\":[{\"dimension\":\"domain.name\",\"value\":\"other.com\",\"display_value\":\"other.com\"}]," +
                "\"metrics\":{\"classified_failures_count\":7}}" +
                "]}";

        stubFor(post(urlPathEqualTo(METRICS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", containing("application/json"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        BounceClassificationRequest request = BounceClassificationRequest.builder()
                .start("Mon, 01 Jan 2024 00:00:00 +0000")
                .end("Mon, 08 Jan 2024 00:00:00 +0000")
                .dimensions(List.of("domain.name"))
                .metrics(List.of("classified_failures_count"))
                .pagination(BounceClassificationPagination.builder()
                        .sort("classified_failures_count:desc")
                        .skip(0)
                        .limit(10)
                        .build())
                .build();

        BounceClassificationResponse result = api.getMetrics(request);

        assertNotNull(result);
        assertEquals("Mon, 01 Jan 2024 00:00:00 +0000", result.getStart());
        assertEquals("Mon, 08 Jan 2024 00:00:00 +0000", result.getEnd());
        assertEquals("day", result.getResolution());
        assertEquals("7d", result.getDuration());
        assertEquals(List.of("domain.name"), result.getDimensions());

        assertNotNull(result.getPagination());
        assertEquals(0, result.getPagination().getSkip());
        assertEquals(10, result.getPagination().getLimit());
        assertEquals(2, result.getPagination().getTotal());

        assertNotNull(result.getItems());
        assertEquals(2, result.getItems().size());

        BounceClassificationItem first = result.getItems().get(0);
        assertNotNull(first.getDimensions());
        assertEquals(1, first.getDimensions().size());
        assertEquals("domain.name", first.getDimensions().get(0).getDimension());
        assertEquals("example.com", first.getDimensions().get(0).getValue());
        assertNotNull(first.getMetrics());
        assertEquals(42, ((Number) first.getMetrics().get("classified_failures_count")).intValue());
    }

    @Test
    void getMetricsMinimalRequestSuccessTest() {
        String body = "{" +
                "\"start\":\"Mon, 01 Jan 2024 00:00:00 +0000\"," +
                "\"end\":\"Mon, 08 Jan 2024 00:00:00 +0000\"," +
                "\"pagination\":{\"sort\":\"classified_failures_count:desc\",\"skip\":0,\"limit\":100,\"total\":0}," +
                "\"items\":[]" +
                "}";

        stubFor(post(urlPathEqualTo(METRICS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        BounceClassificationResponse result = api.getMetrics(BounceClassificationRequest.builder().build());

        assertNotNull(result);
        assertNotNull(result.getItems());
        assertEquals(0, result.getItems().size());
        assertEquals(0, result.getPagination().getTotal());
    }

    @Test
    void getMetricsWithSubaccountsSuccessTest() {
        String body = "{" +
                "\"start\":\"Mon, 01 Jan 2024 00:00:00 +0000\"," +
                "\"end\":\"Mon, 08 Jan 2024 00:00:00 +0000\"," +
                "\"pagination\":{\"sort\":\"classified_failures_count:desc\",\"skip\":0,\"limit\":10,\"total\":1}," +
                "\"items\":[{\"dimensions\":[{\"dimension\":\"account.name\",\"value\":\"sub1\",\"display_value\":\"sub1\"}]," +
                "\"metrics\":{\"classified_failures_count\":5}}]" +
                "}";

        stubFor(post(urlPathEqualTo(METRICS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        BounceClassificationRequest request = BounceClassificationRequest.builder()
                .dimensions(Arrays.asList("account.name"))
                .includeSubaccounts(true)
                .build();

        BounceClassificationResponse result = api.getMetrics(request);

        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertEquals("sub1", result.getItems().get(0).getDimensions().get(0).getValue());
    }

}
