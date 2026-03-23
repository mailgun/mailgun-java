package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.ResolutionPeriod;
import com.mailgun.enums.StatsEventType;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.tags.TagAggregatesResponse;
import com.mailgun.model.tags.TagLimitsResponse;
import com.mailgun.model.tags.TagStatsQuery;
import com.mailgun.model.tags.TagStatsResult;
import com.mailgun.model.tags.TagUpdateQuery;
import com.mailgun.model.tags.TagsItem;
import com.mailgun.model.tags.TagsQuery;
import com.mailgun.model.tags.TagsResult;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static com.mailgun.constants.TestConstants.TEST_TAG_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunTagsApiTest extends WireMockBaseTest {

    private static final String API_BASE = "/" + MailgunApi.getApiVersion().getValue();
    private static final String TAGS_PATH = API_BASE + "/" + TEST_DOMAIN + "/tags";
    private static final String TAG_PATH = API_BASE + "/" + TEST_DOMAIN + "/tag";

    private MailgunTagsApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunTagsApi.class);
    }

    @Test
    void getAllTagsWithQueryTest() {
        String body = "{\"items\":[{\"tag\":\"" + TEST_TAG_1 + "\",\"description\":\"test\"}],\"paging\":{}}";
        stubFor(get(urlPathEqualTo(TAGS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("prefix", equalTo("test"))
                .withQueryParam("limit", equalTo("5"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TagsQuery query = TagsQuery.builder()
                .prefix("test")
                .limit(5)
                .build();
        TagsResult result = api.getAllTags(TEST_DOMAIN, query);

        assertNotNull(result);
        assertNotNull(result.getItems());
        assertEquals(1, result.getItems().size());
        assertEquals(TEST_TAG_1, result.getItems().get(0).getTag());
    }

    @Test
    void getTagByQueryTest() {
        String body = "{\"tag\":\"" + TEST_TAG_1 + "\",\"description\":\"my tag\"}";
        stubFor(get(urlEqualTo(TAG_PATH + "?tag=" + TEST_TAG_1))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TagsItem result = api.getTagByQuery(TEST_DOMAIN, TEST_TAG_1);

        assertNotNull(result);
        assertEquals(TEST_TAG_1, result.getTag());
        assertEquals("my tag", result.getDescription());
    }

    @Test
    void updateTagByQueryTest() {
        String body = "{\"message\":\"Tag updated\"}";
        stubFor(put(urlPathEqualTo(TAG_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("tag", equalTo(TEST_TAG_1))
                .withQueryParam("description", equalTo("new description"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TagUpdateQuery query = TagUpdateQuery.builder()
                .tag(TEST_TAG_1)
                .description("new description")
                .build();
        ResponseWithMessage result = api.updateTagByQuery(TEST_DOMAIN, query);

        assertNotNull(result);
        assertEquals("Tag updated", result.getMessage());
    }

    @Test
    void deleteTagByQueryTest() {
        String body = "{\"message\":\"Tag deleted\"}";
        stubFor(delete(urlEqualTo(TAG_PATH + "?tag=" + TEST_TAG_1))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        ResponseWithMessage result = api.deleteTagByQuery(TEST_DOMAIN, TEST_TAG_1);

        assertNotNull(result);
        assertEquals("Tag deleted", result.getMessage());
    }

    @Test
    void getTagStatsByQueryTest() {
        String body = "{\"tag\":\"" + TEST_TAG_1 + "\",\"description\":\"stats\",\"resolution\":\"day\",\"stats\":[]}";
        stubFor(get(urlPathEqualTo(TAG_PATH + "/stats"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("tag", equalTo(TEST_TAG_1))
                .withQueryParam("event", equalTo("clicked"))
                .withQueryParam("resolution", equalTo("day"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TagStatsQuery query = TagStatsQuery.builder()
                .tag(TEST_TAG_1)
                .event(StatsEventType.CLICKED)
                .resolution(ResolutionPeriod.DAY)
                .build();
        TagStatsResult result = api.getTagStatsByQuery(TEST_DOMAIN, query);

        assertNotNull(result);
        assertEquals(TEST_TAG_1, result.getTag());
        assertEquals(ResolutionPeriod.DAY, result.getResolution());
        assertNotNull(result.getStats());
    }

    @Test
    void getTagAggregatesTest() {
        String body = "{\"tag\":\"" + TEST_TAG_1 + "\",\"provider\":{\"Gmail\":{\"opened\":10}}}";
        stubFor(get(urlPathEqualTo(TAG_PATH + "/stats/aggregates"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("tag", equalTo(TEST_TAG_1))
                .withQueryParam("type", equalTo("provider"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TagAggregatesResponse result = api.getTagAggregates(TEST_DOMAIN, TEST_TAG_1, "provider");

        assertNotNull(result);
        assertEquals(TEST_TAG_1, result.getTag());
        assertNotNull(result.getProvider());
        assertEquals(1, result.getProvider().size());
    }

    @Test
    void getTagLimitsTest() {
        String body = "{\"id\":\"tag-limit-1\",\"limit\":1000,\"count\":42}";
        stubFor(get(urlPathEqualTo(API_BASE + "/domains/" + TEST_DOMAIN + "/limits/tag"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TagLimitsResponse result = api.getTagLimits(TEST_DOMAIN);

        assertNotNull(result);
        assertEquals("tag-limit-1", result.getId());
        assertEquals(1000, result.getLimit());
        assertEquals(42, result.getCount());
    }

    @Test
    void getAllTagsWithPaginationNextPageTest() {
        String body = "{\"items\":[],\"paging\":{}}";
        stubFor(get(urlPathEqualTo(TAGS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("page", equalTo("next"))
                .withQueryParam("tag", equalTo(TEST_TAG_1))
                .withQueryParam("limit", equalTo("10"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TagsQuery query = TagsQuery.builder()
                .page("next")
                .tag(TEST_TAG_1)
                .limit(10)
                .build();
        TagsResult result = api.getAllTags(TEST_DOMAIN, query);

        assertNotNull(result);
        assertEquals(Collections.emptyList(), result.getItems());
    }
}
