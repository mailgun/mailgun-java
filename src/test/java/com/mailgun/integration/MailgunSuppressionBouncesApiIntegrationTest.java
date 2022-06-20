package com.mailgun.integration;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.mailgun.api.v3.suppression.MailgunSuppressionBouncesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.suppression.SuppressionResponse;
import com.mailgun.model.suppression.bounces.BouncesItem;
import com.mailgun.model.suppression.bounces.BouncesListImportRequest;
import com.mailgun.model.suppression.bounces.BouncesRequest;
import com.mailgun.model.suppression.bounces.BouncesResponse;
import com.mailgun.utils.FileUtils;
import feign.FeignException;

import static com.mailgun.constants.IntegrationTestConstants.MAIN_DOMAIN;
import static com.mailgun.constants.IntegrationTestConstants.PRIVATE_API_KEY;
import static com.mailgun.constants.TestConstants.NOW;
import static com.mailgun.constants.TestConstants.ZONED_DATE_TIME_2018;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Use these tests as examples for a real implementation.")
class MailgunSuppressionBouncesApiIntegrationTest {

    private static final String ADDRESS = "some-fake-address-1@example.com";
    private static final String ERROR_MESSAGE = "Error message 1";

    private static MailgunSuppressionBouncesApi suppressionBouncesApi;

    @BeforeAll
    static void beforeAll() {
        suppressionBouncesApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunSuppressionBouncesApi.class);
    }

    @Test
    void getBouncesSuccessTest() {
        BouncesResponse result = suppressionBouncesApi.getBounces(MAIN_DOMAIN);

        List<BouncesItem> items = result.getItems();
        assertTrue(CollectionUtils.isNotEmpty(items));
        BouncesItem bouncesItem = items.get(0);
        assertNotNull(bouncesItem.getAddress());
        assertNotNull(bouncesItem.getCode());
        assertNotNull(bouncesItem.getError());
        assertNotNull(bouncesItem.getCreatedAt());
        assertNotNull(bouncesItem.getMessageHash());
        assertNotNull(result.getPaging());
    }

    @Test
    void getBouncesLimitSuccessTest() {
        BouncesResponse result = suppressionBouncesApi.getBounces(MAIN_DOMAIN, 3);

        List<BouncesItem> items = result.getItems();
        assertTrue(CollectionUtils.isNotEmpty(items));
        assertEquals(3, items.size());
        assertNotNull(result.getPaging());
    }

    @Test
    void getBounceSuccessTest() {
        BouncesItem result = suppressionBouncesApi.getBounce(MAIN_DOMAIN, ADDRESS);

        assertNotNull(result.getAddress());
        assertNotNull(result.getCode());
        assertNotNull(result.getError());
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getMessageHash());
    }

    @Test
    void addBounceSuccessTest() {
        BouncesRequest bouncesRequest = BouncesRequest.builder()
                .address(ADDRESS)
                .code("550")
                .error(ERROR_MESSAGE)
                .createdAt(ZONED_DATE_TIME_2018)
                .build();

        SuppressionResponse result = suppressionBouncesApi.addBounce(MAIN_DOMAIN, bouncesRequest);

        assertEquals("Address has been added to the bounces table", result.getMessage());
        assertEquals(ADDRESS, result.getAddress());
    }

    @Test
    void addBouncesSuccessTest() {
        BouncesRequest bouncesRequest1 = BouncesRequest.builder()
                .address(ADDRESS)
                .code("555")
                .error(ERROR_MESSAGE)
                .createdAt(ZONED_DATE_TIME_2018)
                .build();

        BouncesRequest bouncesRequest2 = BouncesRequest.builder()
                .address("2" + ADDRESS)
                .code("552")
                .error(ERROR_MESSAGE)
                .createdAt(NOW)
                .build();

        ResponseWithMessage result = suppressionBouncesApi.addBounces(MAIN_DOMAIN, Arrays.asList(bouncesRequest1, bouncesRequest2));

        assertEquals("2 addresses have been added to the bounces table", result.getMessage());
    }

    @Test
    void importBounceListSuccessTest() throws IOException {
        List<String[]> dataLines = Arrays.asList(
            new String[] { "address", "code", "error", "created_at" },
            new String[] { "fake-address-1@fake.com", "551", "Error message 1", StringUtils.EMPTY },
            new String[] { "fake-address-2@fake.com", "552", "Error message 2", StringUtils.EMPTY }
        );

        File csvOutputFile = File.createTempFile("bounces_list", ".csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                .map(FileUtils::convertToCSV)
                .forEach(pw::println);
        }

        assertTrue(csvOutputFile.exists());
        csvOutputFile.deleteOnExit();

        BouncesListImportRequest request = BouncesListImportRequest.builder()
            .file(csvOutputFile)
            .build();

        ResponseWithMessage result = suppressionBouncesApi.importBounceList(MAIN_DOMAIN, request);

        assertEquals("file uploaded successfully", result.getMessage());
    }

    @Test
    void deleteBounceSuccessTest() {
        ResponseWithMessage result = suppressionBouncesApi.deleteBounce(MAIN_DOMAIN, ADDRESS);

        assertEquals("Bounced address has been removed", result.getMessage());

        FeignException exception = assertThrows(FeignException.class, () ->
                suppressionBouncesApi.getBounce(MAIN_DOMAIN, ADDRESS)
        );

        assertTrue(exception.getMessage().contains("Address not found in bounces table"));
        assertEquals(404, exception.status());
    }

}
