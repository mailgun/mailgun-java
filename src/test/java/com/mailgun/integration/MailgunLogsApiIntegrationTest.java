package com.mailgun.integration;

import com.mailgun.api.v1.MailgunLogsApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.logs.LogItem;
import com.mailgun.model.logs.LogsRequest;
import com.mailgun.model.logs.LogsResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.mailgun.constants.IntegrationTestConstants.PRIVATE_API_KEY;
import static org.junit.jupiter.api.Assertions.*;

@Disabled("Use these tests as examples for a real implementation.")
class MailgunLogsApiIntegrationTest {

    private static MailgunLogsApi mailgunLogsApi;

    @BeforeAll
    static void beforeAll() {
        mailgunLogsApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunLogsApi.class);
    }

    @Test
    void getLogsBasicSuccessTest() {
        LogsRequest logsRequest = LogsRequest.builder()
                .build();
        LogsResponse result = mailgunLogsApi.getLogs(logsRequest);
        assertNotNull(result);
        assertNotNull(result.getItems());
        assertTrue(CollectionUtils.isNotEmpty(result.getItems()));
        assertNotNull(result.getPagination());
    }

    @Test
    void getLogsFilteredByEventSuccessTest() {
        LogsRequest logsRequest = LogsRequest.builder()
                .events(Collections.singletonList("delivered"))
                .build();
        LogsResponse result = mailgunLogsApi.getLogs(logsRequest);
        assertNotNull(result);
        List<LogItem> items = result.getItems();
        assertTrue(CollectionUtils.isNotEmpty(items));
        items.forEach(item -> assertEquals("delivered", item.getEvent()));
    }

    @Test
    void getLogsPaginationSuccessTest() {
        LogsRequest logsRequest = LogsRequest.builder()
                .pagination(LogsRequest.Pagination.builder().limit(1).build())
                .build();
        LogsResponse firstPage = mailgunLogsApi.getLogs(logsRequest);
        assertNotNull(firstPage);
        assertNotNull(firstPage.getPagination());
        assertTrue(CollectionUtils.isNotEmpty(firstPage.getItems()));
        String nextToken = firstPage.getPagination().getNext();
        assertNotNull(nextToken);

        LogsRequest nextPageRequest = LogsRequest.builder()
                .pagination(LogsRequest.Pagination.builder().limit(1).token(nextToken).build())
                .build();
        LogsResponse nextPage = mailgunLogsApi.getLogs(nextPageRequest);
        assertNotNull(nextPage);
        assertNotNull(nextPage.getPagination());
        assertTrue(CollectionUtils.isNotEmpty(nextPage.getItems()));
        // Optionally, check that the items are different
        assertNotEquals(firstPage.getItems().get(0).getId(), nextPage.getItems().get(0).getId());
    }
} 