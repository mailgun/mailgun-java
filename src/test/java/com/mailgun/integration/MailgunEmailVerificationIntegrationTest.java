package com.mailgun.integration;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.mailgun.api.v4.MailgunEmailVerificationApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.verification.AddressValidationResponse;
import com.mailgun.model.verification.BulkVerificationDownloadUrl;
import com.mailgun.model.verification.BulkVerificationJobCreatingResponse;
import com.mailgun.model.verification.BulkVerificationJobListResponse;
import com.mailgun.model.verification.BulkVerificationJobStatusRequest;
import com.mailgun.model.verification.BulkVerificationJobStatusResponse;
import com.mailgun.model.verification.BulkVerificationJobStatusSummary;
import com.mailgun.model.verification.BulkVerificationJobStatusSummaryResult;
import com.mailgun.model.verification.BulkVerificationJobStatusSummaryRisk;

import static com.mailgun.constants.IntegrationTestConstants.EMAIL_FROM;
import static com.mailgun.constants.IntegrationTestConstants.EMAIL_TO;
import static com.mailgun.constants.IntegrationTestConstants.PRIVATE_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Use these tests as examples for a real implementation.")
class MailgunEmailVerificationIntegrationTest {

    private static final String TEST_LIST_NAME = "test_list_1";
    private static MailgunEmailVerificationApi mailgunEmailVerificationApi;

    @BeforeAll
    static void beforeAll() {
        mailgunEmailVerificationApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunEmailVerificationApi.class);
    }

    @Test
    void validateAddressSuccessTest() {
        AddressValidationResponse result = mailgunEmailVerificationApi.validateAddress(EMAIL_TO);

        assertTrue(StringUtils.isNotBlank(result.getAddress()));
        assertNotNull(result.getIsDisposableAddress());
        assertNotNull(result.getIsDisposableAddress());
        assertNotNull(result.getReason());
        assertTrue(StringUtils.isNotBlank(result.getResult()));
        assertTrue(StringUtils.isNotBlank(result.getRisk()));
    }

    @Test
    void validateAddressProviderLookupSuccessTest() {
        AddressValidationResponse result = mailgunEmailVerificationApi.validateAddress(EMAIL_TO, true);

        assertTrue(StringUtils.isNotBlank(result.getAddress()));
        assertNotNull(result.getIsDisposableAddress());
        assertNotNull(result.getIsDisposableAddress());
        assertNotNull(result.getReason());
        assertTrue(StringUtils.isNotBlank(result.getResult()));
        assertTrue(StringUtils.isNotBlank(result.getRisk()));
    }

    @Test
    void validateAddressPostSuccessTest() {
        AddressValidationResponse result = mailgunEmailVerificationApi.validateAddressPostRequest(EMAIL_TO);

        assertTrue(StringUtils.isNotBlank(result.getAddress()));
        assertNotNull(result.getIsDisposableAddress());
        assertNotNull(result.getIsDisposableAddress());
        assertNotNull(result.getReason());
        assertTrue(StringUtils.isNotBlank(result.getResult()));
        assertTrue(StringUtils.isNotBlank(result.getRisk()));
    }

    @Test
    void validateAddressPostProviderLookupSuccessTest() {
        AddressValidationResponse result = mailgunEmailVerificationApi.validateAddressPostRequest(EMAIL_TO, true);

        assertTrue(StringUtils.isNotBlank(result.getAddress()));
        assertNotNull(result.getIsDisposableAddress());
        assertNotNull(result.getIsDisposableAddress());
        assertNotNull(result.getReason());
        assertTrue(StringUtils.isNotBlank(result.getResult()));
        assertTrue(StringUtils.isNotBlank(result.getRisk()));
    }

    @Test
    void createBulkVerificationJobSuccessTest() throws IOException {
        List<String> dataLines = Arrays.asList("email", EMAIL_TO, EMAIL_FROM);
        File csvOutputFile = File.createTempFile("emails", ".csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.forEach(pw::println);
        }
        assertTrue(csvOutputFile.exists());
        csvOutputFile.deleteOnExit();

        BulkVerificationJobStatusRequest request = BulkVerificationJobStatusRequest.builder()
            .file(csvOutputFile)
            .build();

        BulkVerificationJobCreatingResponse result = mailgunEmailVerificationApi
            .createBulkVerificationJob(TEST_LIST_NAME, request);

        assertNotNull(result.getId());
        assertNotNull(result.getMessage());
        assertEquals("The validation job was submitted.", result.getMessage());
    }

    @Test
    void getBulkVerificationJobListSuccessTest() {
        BulkVerificationJobListResponse result = mailgunEmailVerificationApi.getBulkVerificationJobList();

        assertNotNull(result.getJobs());
        assertNotNull(result.getTotal());
        assertNotNull(result.getPaging());
    }

    @Test
    void getBulkVerificationJobListLimitSuccessTest() {
        BulkVerificationJobListResponse result = mailgunEmailVerificationApi.getBulkVerificationJobList(1);

        assertNotNull(result.getJobs());
        assertEquals(1, result.getJobs().size());
        assertNotNull(result.getTotal());
        assertNotNull(result.getPaging());
    }

    @Test
    void getBulkVerificationJobStatusSuccessTest() {
        BulkVerificationJobStatusResponse result = mailgunEmailVerificationApi.getBulkVerificationJobStatus(TEST_LIST_NAME);

        assertTrue(StringUtils.isNotBlank(result.getId()));
        assertNotNull(result.getQuantity());
        assertNotNull(result.getRecordsProcessed());
        assertTrue(StringUtils.isNotBlank(result.getStatus()));
        BulkVerificationJobStatusSummary summary = result.getSummary();
        assertNotNull(summary);
        BulkVerificationJobStatusSummaryResult summaryResult = summary.getResult();
        assertNotNull(summaryResult.getCatchAll());
        assertNotNull(summaryResult.getDeliverable());
        assertNotNull(summaryResult.getDoNotSend());
        assertNotNull(summaryResult.getUndeliverable());
        assertNotNull(summaryResult.getUnknown());
        BulkVerificationJobStatusSummaryRisk risk = summary.getRisk();
        assertNotNull(risk);
        assertNotNull(risk.getHigh());
        assertNotNull(risk.getLow());
        assertNotNull(risk.getMedium());
        assertNotNull(risk.getUnknown());
        assertNotNull(result.getCreatedAt());
        BulkVerificationDownloadUrl downloadUrl = result.getDownloadUrl();
        assertNotNull(downloadUrl);
        assertTrue(StringUtils.isNotBlank(downloadUrl.getCsv()));
        assertTrue(StringUtils.isNotBlank(downloadUrl.getJson()));
    }

    @Test
    void cancelBulkVerificationJobSuccessTest() {
        String result = mailgunEmailVerificationApi.cancelBulkVerificationJob(TEST_LIST_NAME);

        assertEquals("Validation job canceled.", result);
    }

}
