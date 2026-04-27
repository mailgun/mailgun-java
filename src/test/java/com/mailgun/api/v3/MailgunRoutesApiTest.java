package com.mailgun.api.v3;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.routes.Route;
import com.mailgun.model.routes.RoutesListResponse;
import com.mailgun.model.routes.RoutesMatchRequest;
import com.mailgun.model.routes.RoutesPageRequest;
import com.mailgun.model.routes.RoutesRequest;
import com.mailgun.model.routes.RoutesResponse;
import com.mailgun.model.routes.SingleRouteResponse;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunRoutesApiTest extends WireMockBaseTest {

    private static final String BASE = "/" + ApiVersion.V_3.getValue() + "/routes";

    private MailgunRoutesApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunRoutesApi.class);
    }

    @Test
    void getRoutesListSuccessTest() {
        stubFor(get(urlPathEqualTo(BASE))
                .withQueryParam("limit", equalTo("10"))
                .withQueryParam("skip", equalTo("0"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"items\":[{\"id\":\"r1\",\"priority\":0,\"description\":\"inbox\",\"expression\":\"match_recipient('.*@ex.com')\",\"actions\":[\"forward(\\\"a@b.com\\\")\"]}],\"total_count\":1}")));

        RoutesListResponse r = api.getRoutesList(RoutesPageRequest.builder()
                .limit(10)
                .skip(0)
                .build());

        assertEquals(Integer.valueOf(1), r.getTotalCount());
        assertNotNull(r.getItems());
        assertEquals("r1", r.getItems().get(0).getId());
    }

    @Test
    void getSingleRouteAndMatchRouteSuccessTest() {
        stubFor(get(urlPathEqualTo(BASE + "/r1"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"route\":{\"id\":\"r1\",\"priority\":0,\"description\":\"d\",\"expression\":\"e\",\"actions\":[\"store()\"]}}")));

        SingleRouteResponse single = api.getSingleRoute("r1");
        assertNotNull(single.getRoute());
        assertEquals("r1", single.getRoute().getId());

        stubFor(get(urlPathEqualTo(BASE + "/match"))
                .withQueryParam("address", equalTo("user@example.com"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"route\":{\"id\":\"r1\",\"priority\":0,\"description\":\"d\",\"expression\":\"e\",\"actions\":[\"store()\"]}}")));

        SingleRouteResponse match = api.matchRoute(RoutesMatchRequest.builder()
                .address("user@example.com")
                .build());
        assertEquals("r1", match.getRoute().getId());
    }

    @Test
    void createUpdateDeleteRouteSuccessTest() {
        stubFor(post(urlPathEqualTo(BASE))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"message\":\"created\",\"route\":{\"id\":\"new-r\",\"priority\":0,\"description\":\"x\",\"expression\":\"match_recipient('.*')\",\"actions\":[\"store()\"]}}")));

        RoutesResponse created = api.createRoute(RoutesRequest.builder()
                .description("x")
                .expression("match_recipient('.*')")
                .action("store()")
                .build());
        assertEquals("new-r", created.getRoute().getId());

        stubFor(put(urlPathEqualTo(BASE + "/new-r"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"id\":\"new-r\",\"priority\":1,\"description\":\"y\",\"expression\":\"match_recipient('.*')\",\"actions\":[\"forward(\\\"z@z.com\\\")\"]}")));

        Route updated = api.updateRoute("new-r", RoutesRequest.builder()
                .priority(1)
                .description("y")
                .action("forward(\"z@z.com\")")
                .build());
        assertEquals(Integer.valueOf(1), updated.getPriority());

        stubFor(delete(urlPathEqualTo(BASE + "/new-r"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"message\":\"deleted\"}")));

        ResponseWithMessage del = api.deleteRoute("new-r");
        assertEquals("deleted", del.getMessage());
    }

}
