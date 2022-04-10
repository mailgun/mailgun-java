package com.mailgun.model.webhooks;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.mailgun.constants.IntegrationTestConstants.WEBHOOK_URL_2;
import static com.mailgun.constants.IntegrationTestConstants.WEBHOOK_URL_3;
import static com.mailgun.constants.IntegrationTestConstants.WEBHOOK_URL_4;
import static com.mailgun.util.Constants.FIELD_CANNOT_BE_NULL_OR_EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WebhookUpdateRequestTest {

    @Test
    void webhookUpdateRequestBuilderSuccessTest() {
        WebhookUpdateRequest webhookUpdateRequest = WebhookUpdateRequest.builder()
                .url(WEBHOOK_URL_4)
                .urls(Arrays.asList(WEBHOOK_URL_2, WEBHOOK_URL_3))
                .build();

        assertTrue(webhookUpdateRequest.getUrls().containsAll(Arrays.asList(WEBHOOK_URL_2, WEBHOOK_URL_3, WEBHOOK_URL_4)));
    }

    @Test
    void messageFieldUrlsNullExceptionTest() {
        WebhookUpdateRequest.WebhookUpdateRequestBuilder webhookUpdateRequestBuilder = WebhookUpdateRequest.builder();

        Exception exception = assertThrows(IllegalArgumentException.class, webhookUpdateRequestBuilder::build);

        assertEquals(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "url(s)"), exception.getMessage());
    }

    @Test
    void messageFieldUrlsEmptyListExceptionTest() {
        WebhookUpdateRequest.WebhookUpdateRequestBuilder webhookUpdateRequestBuilder = WebhookUpdateRequest.builder()
                .urls(Collections.emptyList());

        Exception exception = assertThrows(IllegalArgumentException.class, webhookUpdateRequestBuilder::build);

        assertEquals(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "url(s)"), exception.getMessage());
    }

    @Test
    void messageFieldUrlsEmptyExceptionTest() {
        WebhookUpdateRequest.WebhookUpdateRequestBuilder webhookUpdateRequestBuilder = WebhookUpdateRequest.builder()
                .url(StringUtils.SPACE)
                .urls(Collections.singletonList(StringUtils.EMPTY));

        Exception exception = assertThrows(IllegalArgumentException.class, webhookUpdateRequestBuilder::build);

        assertEquals(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "url(s)"), exception.getMessage());
    }

}
