package com.mailgun.integration;

import com.mailgun.api.v3.suppression.MailgunSuppressionComplaintsApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.suppression.SuppressionResponse;
import com.mailgun.model.suppression.bounces.ComplaintsListImportRequest;
import com.mailgun.model.suppression.complaints.ComplaintsItem;
import com.mailgun.model.suppression.complaints.ComplaintsItemResponse;
import com.mailgun.model.suppression.complaints.ComplaintsSingleItemRequest;
import com.mailgun.utils.FileUtils;
import feign.FeignException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import static com.mailgun.constants.IntegrationTestConstants.MAIN_DOMAIN;
import static com.mailgun.constants.IntegrationTestConstants.PRIVATE_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_1;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_2;
import static com.mailgun.constants.TestConstants.ZONED_DATE_TIME_2018;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Use these tests as examples for a real implementation.")
class MailgunSuppressionComplaintsApiIntegrationTest {

    private static MailgunSuppressionComplaintsApi suppressionComplaintsApi;

    @BeforeAll
    static void beforeAll() {
        suppressionComplaintsApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunSuppressionComplaintsApi.class);
    }

    @Test
    void getAllComplaintsSuccessTest() {
        ComplaintsItemResponse result = suppressionComplaintsApi.getAllComplaints(MAIN_DOMAIN);

        List<ComplaintsItem> items = result.getItems();
        assertTrue(CollectionUtils.isNotEmpty(items));
        assertTrue(items.size() > 2);
        ComplaintsItem complaintsItem = items.get(0);
        assertNotNull(complaintsItem.getAddress());
        assertNotNull(complaintsItem.getCreatedAt());
        assertNotNull(result.getPaging());
    }

    @Test
    void getAllComplaintsLimitSuccessTest() {
        ComplaintsItemResponse result = suppressionComplaintsApi.getAllComplaints(MAIN_DOMAIN, 2);

        List<ComplaintsItem> items = result.getItems();
        assertTrue(CollectionUtils.isNotEmpty(items));
        assertEquals(2, items.size());
        ComplaintsItem complaintsItem = items.get(0);
        assertNotNull(complaintsItem.getAddress());
        assertNotNull(complaintsItem.getCreatedAt());
        assertNotNull(result.getPaging());
    }

    @Test
    void getSingleComplaintSuccessTest() {
        ComplaintsItem result = suppressionComplaintsApi.getSingleComplaint(MAIN_DOMAIN, TEST_EMAIL_1);

        assertEquals(TEST_EMAIL_1, result.getAddress());
        assertNotNull(result.getCreatedAt());
        assertTrue(ZONED_DATE_TIME_2018.isEqual(result.getCreatedAt()));
    }

    @Test
    void addAddressToComplaintsListSuccessTest() {
        ComplaintsSingleItemRequest complaintsSingleItemRequest = ComplaintsSingleItemRequest.builder()
                .address(TEST_EMAIL_1)
                .createdAt(ZONED_DATE_TIME_2018)
                .build();

        SuppressionResponse result = suppressionComplaintsApi.addAddressToComplaintsList(MAIN_DOMAIN, complaintsSingleItemRequest);

        assertEquals("Address has been added to the complaints table", result.getMessage());
        assertEquals(TEST_EMAIL_1, result.getAddress());
    }

    @Test
    void addAddressesToComplaintsListSuccessTest() {
        ComplaintsItem complaintsItem1 = ComplaintsItem.builder()
                .address(TEST_EMAIL_1)
                .createdAt(ZONED_DATE_TIME_2018)
                .build();

        ComplaintsItem complaintsItem2 = ComplaintsItem.builder()
                .address(TEST_EMAIL_2)
                .build();

        ResponseWithMessage result = suppressionComplaintsApi.addAddressesToComplaintsList(MAIN_DOMAIN,
                Arrays.asList(complaintsItem1, complaintsItem2));

        assertEquals("2 complaint addresses have been added to the complaints table", result.getMessage());
    }

    @Test
    void importComplaintsListSuccessTest() throws IOException {
        List<String[]> dataLines = Arrays.asList(
            new String[] { "address", "created_at" },
            new String[] { "fake-address-1@fake.com", StringUtils.EMPTY},
            new String[] { "fake-address-2@fake.com", StringUtils.EMPTY }
        );

        File csvOutputFile = File.createTempFile("complaints_list", ".csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                .map(FileUtils::convertToCSV)
                .forEach(pw::println);
        }

        assertTrue(csvOutputFile.exists());
        csvOutputFile.deleteOnExit();

        ComplaintsListImportRequest request = ComplaintsListImportRequest.builder()
            .file(csvOutputFile)
            .build();

        ResponseWithMessage result = suppressionComplaintsApi.importComplaintsList(MAIN_DOMAIN, request);

        assertEquals("file uploaded successfully", result.getMessage());
    }

    @Test
    void removeAddressFromComplaintsSuccessTest() {
        SuppressionResponse result = suppressionComplaintsApi.removeAddressFromComplaints(MAIN_DOMAIN, TEST_EMAIL_1);

        assertEquals("Spam complaint has been removed", result.getMessage());
        assertEquals(TEST_EMAIL_1, result.getAddress());

        FeignException exception = assertThrows(FeignException.class, () ->
                suppressionComplaintsApi.getSingleComplaint(MAIN_DOMAIN, TEST_EMAIL_1)
        );

        assertTrue(exception.getMessage().contains("No spam complaints found for this address"));
        assertEquals(404, exception.status());
    }

}
