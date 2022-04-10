package com.mailgun.integration;

import com.mailgun.api.v3.suppression.MailgunSuppressionWhitelistsApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.suppression.whitelists.WhitelistsItem;
import com.mailgun.model.suppression.whitelists.WhitelistsItemResponse;
import com.mailgun.model.suppression.whitelists.WhitelistsRemoveRecordResponse;
import com.mailgun.model.suppression.whitelists.WhitelistsRequest;
import feign.FeignException;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mailgun.constants.IntegrationTestConstants.MAIN_DOMAIN;
import static com.mailgun.constants.IntegrationTestConstants.PRIVATE_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Use these tests as examples for a real implementation.")
class MailgunSuppressionWhitelistsApiIntegrationTest {

    private static MailgunSuppressionWhitelistsApi suppressionWhitelistsApi;

    @BeforeAll
    static void beforeAll() {
        suppressionWhitelistsApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunSuppressionWhitelistsApi.class);
    }

    @Test
    void getAllWhitelistsSuccessTest() {
        WhitelistsItemResponse result = suppressionWhitelistsApi.getAllWhitelists(MAIN_DOMAIN);

        List<WhitelistsItem> items = result.getItems();
        assertTrue(CollectionUtils.isNotEmpty(items));
        assertTrue(items.size() > 2);
        WhitelistsItem whitelistsItem = items.get(0);
        assertNotNull(whitelistsItem.getValue());
        assertNotNull(whitelistsItem.getReason());
        assertNotNull(whitelistsItem.getType());
        assertNotNull(whitelistsItem.getCreatedAt());
        assertNotNull(result.getPaging());
    }

    @Test
    void getAllWhitelistsLimitSuccessTest() {
        WhitelistsItemResponse result = suppressionWhitelistsApi.getAllWhitelists(MAIN_DOMAIN, 2);

        List<WhitelistsItem> items = result.getItems();
        assertTrue(CollectionUtils.isNotEmpty(items));
        assertEquals(2, items.size());
        WhitelistsItem whitelistsItem = items.get(0);
        assertNotNull(whitelistsItem.getValue());
        assertNotNull(whitelistsItem.getReason());
        assertNotNull(whitelistsItem.getType());
        assertNotNull(whitelistsItem.getCreatedAt());
        assertNotNull(result.getPaging());
    }

    @Test
    void getSingleWhitelistRecordSuccessTest() {
        WhitelistsItem result = suppressionWhitelistsApi.getSingleWhitelistRecord(MAIN_DOMAIN, TEST_EMAIL_1);

        assertNotNull(result.getValue());
        assertNotNull(result.getReason());
        assertNotNull(result.getType());
        assertNotNull(result.getCreatedAt());
    }

    @Test
    void addSingleWhitelistRecordAddressSuccessTest() {
        WhitelistsRequest whitelistsRequest = WhitelistsRequest.builder()
                .address(TEST_EMAIL_1)
                .reason("Reason 1")
                .build();

        ResponseWithMessage result = suppressionWhitelistsApi.addSingleWhitelistRecord(MAIN_DOMAIN, whitelistsRequest);

        String expected = "Address/Domain has been added to the whitelists table";
        assertEquals(expected, result.getMessage());
    }

    @Test
    void addSingleWhitelistRecordDomainSuccessTest() {
        WhitelistsRequest whitelistsRequest = WhitelistsRequest.builder()
                .domain(TEST_DOMAIN)
                .reason("Reason 2")
                .build();

        ResponseWithMessage result = suppressionWhitelistsApi.addSingleWhitelistRecord(MAIN_DOMAIN, whitelistsRequest);

        String expected = "Address/Domain has been added to the whitelists table";
        assertEquals(expected, result.getMessage());
    }

    @Test
    void removeRecordFromWhitelistsSuccessTest() {
        WhitelistsRemoveRecordResponse result = suppressionWhitelistsApi.removeRecordFromWhitelists(MAIN_DOMAIN, TEST_EMAIL_1);

        assertEquals("Whitelist address/domain has been removed", result.getMessage());
        assertEquals(TEST_EMAIL_1, result.getValue());

        FeignException exception = assertThrows(FeignException.class, () ->
                suppressionWhitelistsApi.getSingleWhitelistRecord(MAIN_DOMAIN, TEST_EMAIL_1)
        );

        assertTrue(exception.getMessage().contains("Address/Domain not found in whitelists table"));
        assertEquals(404, exception.status());
    }

}
