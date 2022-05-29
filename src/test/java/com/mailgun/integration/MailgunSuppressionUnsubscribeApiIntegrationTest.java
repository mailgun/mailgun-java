package com.mailgun.integration;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.mailgun.api.v3.suppression.MailgunSuppressionUnsubscribeApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.suppression.SuppressionResponse;
import com.mailgun.model.suppression.unsubscribe.UnsubscribeItem;
import com.mailgun.model.suppression.unsubscribe.UnsubscribeItemResponse;
import com.mailgun.model.suppression.unsubscribe.UnsubscribeSingleItemRequest;
import com.mailgun.model.suppression.unsubscribe.UnsubscribesListImportRequest;
import com.mailgun.utils.FileUtils;

import static com.mailgun.constants.IntegrationTestConstants.MAIN_DOMAIN;
import static com.mailgun.constants.IntegrationTestConstants.PRIVATE_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_1;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_2;
import static com.mailgun.constants.TestConstants.TEST_TAG_1;
import static com.mailgun.constants.TestConstants.TEST_TAG_2;
import static com.mailgun.constants.TestConstants.TEST_TAG_3;
import static com.mailgun.constants.TestConstants.ZONED_DATE_TIME_2018;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Use these tests as examples for a real implementation.")
class MailgunSuppressionUnsubscribeApiIntegrationTest {

    private static MailgunSuppressionUnsubscribeApi suppressionUnsubscribeApi;

    @BeforeAll
    static void beforeAll() {
        suppressionUnsubscribeApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunSuppressionUnsubscribeApi.class);
    }

    @Test
    void getAllUnsubscribeSuccessTest() {
        UnsubscribeItemResponse result = suppressionUnsubscribeApi.getAllUnsubscribe(MAIN_DOMAIN);

        List<UnsubscribeItem> items = result.getItems();
        assertTrue(CollectionUtils.isNotEmpty(items));
        UnsubscribeItem unsubscribeItem = items.get(0);
        assertNotNull(unsubscribeItem.getAddress());
        assertTrue(CollectionUtils.isNotEmpty(unsubscribeItem.getTags()));
        assertNotNull(unsubscribeItem.getCreatedAt());
        assertNotNull(result.getPaging());
    }

    @Test
    void getAllUnsubscribeLimitSuccessTest() {
        UnsubscribeItemResponse result = suppressionUnsubscribeApi.getAllUnsubscribe(MAIN_DOMAIN, 2);

        List<UnsubscribeItem> items = result.getItems();
        assertTrue(CollectionUtils.isNotEmpty(items));
        assertEquals(2, items.size());
        UnsubscribeItem unsubscribeItem = items.get(0);
        assertNotNull(unsubscribeItem.getAddress());
        assertTrue(CollectionUtils.isNotEmpty(unsubscribeItem.getTags()));
        assertNotNull(unsubscribeItem.getCreatedAt());
        assertNotNull(result.getPaging());
    }

    @Test
    void getSingleUnsubscribeSuccessTest() {
        UnsubscribeItem result = suppressionUnsubscribeApi.getSingleUnsubscribe(MAIN_DOMAIN, TEST_EMAIL_1);

        assertNotNull(result.getAddress());
        assertTrue(CollectionUtils.isNotEmpty(result.getTags()));
        assertNotNull(result.getCreatedAt());
    }

    @Test
    void addAddressToUnsubscribeTableSuccessTest() {
        UnsubscribeSingleItemRequest unsubscribeSingleItemRequest = UnsubscribeSingleItemRequest.builder()
                .address(TEST_EMAIL_1)
                .tag(TEST_TAG_1)
                .createdAt(ZONED_DATE_TIME_2018)
                .build();

        SuppressionResponse result = suppressionUnsubscribeApi.addAddressToUnsubscribeTable(MAIN_DOMAIN, unsubscribeSingleItemRequest);

        assertEquals("Address has been added to the unsubscribes table", result.getMessage());
        assertEquals(TEST_EMAIL_1, result.getAddress());

        UnsubscribeItem unsubscribedAddress = suppressionUnsubscribeApi.getSingleUnsubscribe(MAIN_DOMAIN, TEST_EMAIL_1);

        assertEquals(TEST_EMAIL_1, unsubscribedAddress.getAddress());
        List<String> tags = unsubscribedAddress.getTags();
        assertEquals(1, tags.size());
        assertTrue(CollectionUtils.containsAny(tags, TEST_TAG_1));
        assertTrue(ZONED_DATE_TIME_2018.isEqual(unsubscribedAddress.getCreatedAt()));
    }

    @Test
    void addAddressesToUnsubscribeTableSuccessTest() {
        UnsubscribeItem unsubscribeItemAllFields = UnsubscribeItem.builder()
                .address(TEST_EMAIL_1)
                .tags(Arrays.asList(TEST_TAG_2, TEST_TAG_3))
                .createdAt(ZONED_DATE_TIME_2018)
                .build();

        UnsubscribeItem unsubscribeItemAddressOnly = UnsubscribeItem.builder()
                .address(TEST_EMAIL_2)
                .build();

        ResponseWithMessage result = suppressionUnsubscribeApi.addAddressesToUnsubscribeTable(MAIN_DOMAIN,
                Arrays.asList(unsubscribeItemAllFields, unsubscribeItemAddressOnly));

        assertEquals("2 addresses have been added to the unsubscribes table", result.getMessage());
    }

    @Test
    void importAddressesToUnsubscribeTableTest() throws IOException {
        List<String[]> dataLines = Arrays.asList(
            new String[] { "address", "tags", "created_at" },
            new String[] { "fake-address-1@fake.com", "*", StringUtils.EMPTY},
            new String[] { "fake-address-2@fake.com", "*", StringUtils.EMPTY }
        );

        File csvOutputFile = File.createTempFile("unsubscribe_list", ".csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                .map(FileUtils::convertToCSV)
                .forEach(pw::println);
        }

        assertTrue(csvOutputFile.exists());
        csvOutputFile.deleteOnExit();

        UnsubscribesListImportRequest request = UnsubscribesListImportRequest.builder()
            .file(csvOutputFile)
            .build();

        ResponseWithMessage result = suppressionUnsubscribeApi.importAddressesToUnsubscribeTable(MAIN_DOMAIN, request);

        assertEquals("file uploaded successfully", result.getMessage());
    }

    @Test
    void removeAddressFromUnsubscribeTagSuccessTest() {
        SuppressionResponse result = suppressionUnsubscribeApi.removeAddressFromUnsubscribeTag(MAIN_DOMAIN, TEST_EMAIL_1, TEST_TAG_2);

        String expected = "Unsubscribe event has been removed";
        assertEquals(expected, result.getMessage());
        assertEquals(TEST_EMAIL_1, result.getAddress());
    }

    @Test
    void removeAddressFromUnsubscribeListSuccessTest() {
        SuppressionResponse result = suppressionUnsubscribeApi.removeAddressFromUnsubscribeList(MAIN_DOMAIN, TEST_EMAIL_1);

        String expected = "Unsubscribe event has been removed";
        assertEquals(expected, result.getMessage());
        assertEquals(TEST_EMAIL_1, result.getAddress());
    }

}
