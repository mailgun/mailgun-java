package com.mailgun.api.v5;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.ApiVersion;
import com.mailgun.enums.SubaccountStatus;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.custommessagelimit.CustomMessageLimitResponse;
import com.mailgun.model.custommessagelimit.CustomMessageLimitSuccessResponse;
import com.mailgun.model.subaccounts.DisableSubaccountQuery;
import com.mailgun.model.subaccounts.SubaccountFeaturesResponse;
import com.mailgun.model.subaccounts.SubaccountResponse;
import com.mailgun.model.subaccounts.SubaccountsListQuery;
import com.mailgun.model.subaccounts.SubaccountsListResponse;
import com.mailgun.model.subaccounts.UpdateSubaccountFeaturesRequest;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZoneOffset;

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
import static com.mailgun.util.Constants.HEADER_ON_BEHALF_OF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MailgunSubaccountsApiTest extends WireMockBaseTest {

    private static final String SUBACCOUNT_ID = "subaccount-123";
    private static final String SUBACCOUNTS_PATH = "/" + ApiVersion.V_5.getValue() + "/accounts/subaccounts";
    private static final String SUBACCOUNT_PATH = SUBACCOUNTS_PATH + "/" + SUBACCOUNT_ID;
    private static final String SUBACCOUNT_BODY = "{\"subaccount\":{\"id\":\"" + SUBACCOUNT_ID + "\","
            + "\"name\":\"My subaccount\",\"created_at\":\"Wed, 06 Nov 2024 19:48:29 GMT\","
            + "\"updated_at\":\"Wed, 28 May 2025 20:03:05 GMT\",\"status\":\"open\","
            + "\"features\":{\"sending\":{\"enabled\":true}}}}";

    private MailgunSubaccountsApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunSubaccountsApi.class);
    }

    @Test
    void getSubaccountTest() {
        stubFor(get(urlEqualTo(SUBACCOUNT_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(jsonResponse(SUBACCOUNT_BODY)));

        SubaccountResponse result = api.getSubaccount(SUBACCOUNT_ID);

        assertEquals(SUBACCOUNT_ID, result.getSubaccount().getId());
        assertEquals(ZoneOffset.UTC, result.getSubaccount().getCreatedAt().getOffset());
        assertTrue(result.getSubaccount().getFeatures().getSending().getEnabled());
    }

    @Test
    void listSubaccountsTest() {
        String body = "{\"subaccounts\":[" + SUBACCOUNT_BODY.substring(14, SUBACCOUNT_BODY.length() - 1)
                + "],\"total\":1}";
        stubFor(get(urlPathEqualTo(SUBACCOUNTS_PATH))
                .withQueryParam("sort", equalTo("asc"))
                .withQueryParam("filter", equalTo("My"))
                .withQueryParam("limit", equalTo("10"))
                .withQueryParam("skip", equalTo("0"))
                .withQueryParam("enabled", equalTo("true"))
                .withQueryParam("closed", equalTo("false"))
                .willReturn(jsonResponse(body)));

        SubaccountsListResponse result = api.listSubaccounts(SubaccountsListQuery.builder()
                .sort("asc")
                .filter("My")
                .limit(10)
                .skip(0)
                .enabled(true)
                .closed(false)
                .build());

        assertEquals(1, result.getTotal());
        assertEquals(SUBACCOUNT_ID, result.getSubaccounts().get(0).getId());
    }

    @Test
    void listSubaccountsWithoutQueryTest() {
        stubFor(get(urlEqualTo(SUBACCOUNTS_PATH))
                .willReturn(jsonResponse("{\"subaccounts\":[],\"total\":0}")));

        SubaccountsListResponse result = api.listSubaccounts();

        assertEquals(0, result.getTotal());
        assertTrue(result.getSubaccounts().isEmpty());
    }

    @Test
    void createSubaccountTest() {
        stubFor(post(urlPathEqualTo(SUBACCOUNTS_PATH))
                .withQueryParam("name", equalTo("My subaccount"))
                .willReturn(jsonResponse(SUBACCOUNT_BODY)));

        SubaccountResponse result = api.createSubaccount("My subaccount");

        assertEquals("My subaccount", result.getSubaccount().getName());
    }

    @Test
    void deleteSubaccountTest() {
        stubFor(delete(urlEqualTo(SUBACCOUNTS_PATH))
                .withHeader(HEADER_ON_BEHALF_OF, equalTo(SUBACCOUNT_ID))
                .willReturn(jsonResponse("{\"message\":\"Subaccount successfully deleted\"}")));

        ResponseWithMessage result = api.deleteSubaccount(SUBACCOUNT_ID);

        assertEquals("Subaccount successfully deleted", result.getMessage());
    }

    @Test
    void disableSubaccountTest() {
        String disabledBody = SUBACCOUNT_BODY.replace("\"status\":\"open\"", "\"status\":\"disabled\"");
        stubFor(post(urlPathEqualTo(SUBACCOUNT_PATH + "/disable"))
                .withQueryParam("reason", equalTo("compliance"))
                .withQueryParam("note", equalTo("manual review"))
                .willReturn(jsonResponse(disabledBody)));

        SubaccountResponse result = api.disableSubaccount(SUBACCOUNT_ID, DisableSubaccountQuery.builder()
                .reason("compliance")
                .note("manual review")
                .build());

        assertEquals("disabled", result.getSubaccount().getStatus());
        assertEquals(SubaccountStatus.DISABLED, result.getSubaccount().getStatusEnum().orElseThrow());
    }

    @Test
    void disableSubaccountWithoutQueryTest() {
        String disabledBody = SUBACCOUNT_BODY.replace("\"status\":\"open\"", "\"status\":\"disabled\"");
        stubFor(post(urlEqualTo(SUBACCOUNT_PATH + "/disable"))
                .willReturn(jsonResponse(disabledBody)));

        SubaccountResponse result = api.disableSubaccount(SUBACCOUNT_ID);

        assertEquals("disabled", result.getSubaccount().getStatus());
    }

    @Test
    void enableSubaccountTest() {
        stubFor(post(urlEqualTo(SUBACCOUNT_PATH + "/enable"))
                .willReturn(jsonResponse(SUBACCOUNT_BODY)));

        SubaccountResponse result = api.enableSubaccount(SUBACCOUNT_ID);

        assertEquals("open", result.getSubaccount().getStatus());
    }

    @Test
    void getCustomSendingLimitTest() {
        stubFor(get(urlEqualTo(SUBACCOUNT_PATH + "/limit/custom/monthly"))
                .willReturn(jsonResponse("{\"limit\":10000,\"current\":0,\"period\":\"1m\"}")));

        CustomMessageLimitResponse result = api.getCustomSendingLimit(SUBACCOUNT_ID);

        assertEquals(new BigDecimal("10000"), result.getLimit());
        assertEquals(new BigDecimal("0"), result.getCurrent());
        assertEquals("1m", result.getPeriod());
    }

    @Test
    void setCustomSendingLimitTest() {
        stubFor(put(urlPathEqualTo(SUBACCOUNT_PATH + "/limit/custom/monthly"))
                .withQueryParam("limit", equalTo("5000"))
                .willReturn(jsonResponse("{\"success\":true}")));

        CustomMessageLimitSuccessResponse result = api.setCustomSendingLimit(
                SUBACCOUNT_ID, new BigDecimal("5000"));

        assertTrue(result.getSuccess());
    }

    @Test
    void deleteCustomSendingLimitTest() {
        stubFor(delete(urlEqualTo(SUBACCOUNT_PATH + "/limit/custom/monthly"))
                .willReturn(jsonResponse("{\"success\":true}")));

        CustomMessageLimitSuccessResponse result = api.deleteCustomSendingLimit(SUBACCOUNT_ID);

        assertTrue(result.getSuccess());
    }

    @Test
    void updateSubaccountFeaturesTest() {
        String body = "{\"features\":{\"email_preview\":{\"enabled\":false},"
                + "\"inbox_placement\":{\"enabled\":false},\"sending\":{\"enabled\":true},"
                + "\"validations\":{\"enabled\":false},\"validations_bulk\":{\"enabled\":false}}}";
        stubFor(put(urlEqualTo(SUBACCOUNT_PATH + "/features"))
                .withHeader("Content-Type", containing("application/x-www-form-urlencoded"))
                .withRequestBody(containing("email_preview="))
                .willReturn(jsonResponse(body)));
        UpdateSubaccountFeaturesRequest request = UpdateSubaccountFeaturesRequest.builder()
                .emailPreview("{\"enabled\":false}")
                .inboxPlacement("{\"enabled\":false}")
                .sending("{\"enabled\":true}")
                .validations("{\"enabled\":false}")
                .validationsBulk("{\"enabled\":false}")
                .build();

        SubaccountFeaturesResponse result = api.updateSubaccountFeatures(SUBACCOUNT_ID, request);

        assertFalse(result.getFeatures().getEmailPreview().getEnabled());
        assertTrue(result.getFeatures().getSending().getEnabled());
    }

    private static com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder jsonResponse(String body) {
        return aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(body);
    }

}
