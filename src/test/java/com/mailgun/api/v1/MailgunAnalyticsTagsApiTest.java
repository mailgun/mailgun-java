package com.mailgun.api.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailgun.api.ResponseSampleLoaderUtil;
import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.tags.AnalyticsTagDeleteRequest;
import com.mailgun.model.tags.AnalyticsTagLimitsResponse;
import com.mailgun.model.tags.AnalyticsTagListPagination;
import com.mailgun.model.tags.AnalyticsTagListRequest;
import com.mailgun.model.tags.AnalyticsTagListResponse;
import com.mailgun.model.tags.AnalyticsTagUpdateRequest;
import com.mailgun.util.ObjectMapperUtil;
import com.mailgun.utils.TestHeadersUtils;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunAnalyticsTagsApiTest extends WireMockBaseTest {

    private static final String API_VERSION = ApiVersion.V_1.getValue();
    private static final String TAGS_PATH = "/" + API_VERSION + "/analytics/tags";
    private static final String TAGS_LIMITS_PATH = TAGS_PATH + "/limits";

    private MailgunAnalyticsTagsApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunAnalyticsTagsApi.class);
    }

    @Test
    void listTagsTest() throws JsonProcessingException, JSONException {
        String expectedResponse = ResponseSampleLoaderUtil.fromFile("tags/analytics-tag-list-response.json");
        stubFor(post(urlEqualTo(TAGS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", containing("application/json"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(expectedResponse)));

        AnalyticsTagListRequest request = AnalyticsTagListRequest.builder()
                .pagination(AnalyticsTagListPagination.builder()
                        .sort("lastseen:desc")
                        .skip(0)
                        .limit(10)
                        .includeTotal(true)
                        .build())
                .includeMetrics(true)
                .build();

        ObjectMapper objectMapper = ObjectMapperUtil.getObjectMapper();
        AnalyticsTagListResponse response = api.listTags(request);

        String actualResponse = objectMapper.writeValueAsString(response);
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);

        assertNotNull(response.getItems());
        assertEquals(1, response.getItems().size());
        assertEquals("welcome-email", response.getItems().get(0).getTag());
        assertEquals("acc-1", response.getItems().get(0).getAccountId());
        assertNotNull(response.getItems().get(0).getMetrics());
    }

    @Test
    void listTagsWithTagFilterTest() throws JsonProcessingException, JSONException {
        String expectedResponse = ResponseSampleLoaderUtil.fromFile("tags/analytics-tag-list-response.json");
        stubFor(post(urlEqualTo(TAGS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(expectedResponse)));

        AnalyticsTagListRequest request = AnalyticsTagListRequest.builder()
                .tag("welcome")
                .build();

        ObjectMapper objectMapper = ObjectMapperUtil.getObjectMapper();
        AnalyticsTagListResponse response = api.listTags(request);

        String actualResponse = objectMapper.writeValueAsString(response);
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }

    @Test
    void updateTagTest() {
        String responseBody = "{\"message\":\"Tag description updated\"}";
        stubFor(put(urlEqualTo(TAGS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", containing("application/json"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)));

        AnalyticsTagUpdateRequest request = AnalyticsTagUpdateRequest.builder()
                .tag("welcome-email")
                .description("Updated welcome email description")
                .build();

        ResponseWithMessage response = api.updateTag(request);

        assertNotNull(response);
        assertEquals("Tag description updated", response.getMessage());
    }

    @Test
    void deleteTagTest() {
        String responseBody = "{\"message\":\"Tag deleted\"}";
        stubFor(delete(urlEqualTo(TAGS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", containing("application/json"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)));

        AnalyticsTagDeleteRequest request = AnalyticsTagDeleteRequest.builder()
                .tag("welcome-email")
                .build();

        ResponseWithMessage response = api.deleteTag(request);

        assertNotNull(response);
        assertEquals("Tag deleted", response.getMessage());
    }

    @Test
    void getTagLimitsTest() throws JsonProcessingException, JSONException {
        String expectedResponse = ResponseSampleLoaderUtil.fromFile("tags/analytics-tag-limits-response.json");
        stubFor(get(urlEqualTo(TAGS_LIMITS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(expectedResponse)));

        ObjectMapper objectMapper = ObjectMapperUtil.getObjectMapper();
        AnalyticsTagLimitsResponse response = api.getTagLimits();

        String actualResponse = objectMapper.writeValueAsString(response);
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);

        assertNotNull(response);
        assertEquals(1000, response.getLimit());
        assertEquals(42, response.getCount());
        assertFalse(response.getLimitReached());
    }

}
