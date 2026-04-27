package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.mailing.lists.MailingListDataResponse;
import com.mailgun.model.mailing.lists.MailingListMembersIndexQuery;
import com.mailgun.model.mailing.lists.MailingListMembersResponse;
import com.mailgun.model.mailing.lists.MailingListsQuery;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunMailingListApiTest extends WireMockBaseTest {

    private static final String BASE = "/" + MailgunApi.getApiVersion().getValue() + "/lists";

    private MailgunMailingListApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunMailingListApi.class);
    }

    @Test
    void getMailingListsWithQuerySuccessTest() {
        String body = "{\"total_count\":1,\"items\":[{\"access_level\":\"readonly\","
                + "\"address\":\"devs@lists.example.com\",\"created_at\":\"Mon, 01 Jan 2024 00:00:00 +0000\","
                + "\"description\":\"Team\",\"members_count\":0,\"name\":\"Developers\"}],\"paging\":{}}";
        stubFor(get(urlPathEqualTo(BASE))
                .withQueryParam("limit", equalTo("5"))
                .withQueryParam("skip", equalTo("10"))
                .withQueryParam("address", equalTo("devs@lists.example.com"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        MailingListDataResponse r = api.getMailingLists(MailingListsQuery.builder()
                .limit(5)
                .skip(10)
                .address("devs@lists.example.com")
                .build());

        assertEquals(Integer.valueOf(1), r.getTotalCount());
        assertNotNull(r.getItems());
        assertEquals(1, r.getItems().size());
        assertEquals("devs@lists.example.com", r.getItems().get(0).getAddress());
    }

    @Test
    void listMailingListMembersWithQuerySuccessTest() {
        String listAddress = "newsletter@example.com";
        String body = "{\"total_count\":2,\"items\":["
                + "{\"address\":\"a@example.com\",\"name\":\"A\",\"subscribed\":true},"
                + "{\"address\":\"b@example.com\",\"subscribed\":false}],\"paging\":{}}";
        stubFor(get(urlPathMatching("/v3/lists/.+/members"))
                .withQueryParam("limit", equalTo("20"))
                .withQueryParam("subscribed", equalTo("yes"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        MailingListMembersResponse r = api.listMailingListMembers(listAddress,
                MailingListMembersIndexQuery.builder()
                        .subscribed(true)
                        .limit(20)
                        .build());

        assertEquals(Integer.valueOf(2), r.getTotalCount());
        assertNotNull(r.getItems());
        assertEquals(2, r.getItems().size());
        assertEquals("a@example.com", r.getItems().get(0).getAddress());
        assertEquals(Boolean.TRUE, r.getItems().get(0).getSubscribed());
    }
}
