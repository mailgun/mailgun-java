package com.mailgun.model.webhooks;

import com.mailgun.enums.WebhookName;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.mailgun.constants.IntegrationTestConstants.WEBHOOK_URL_1;
import static com.mailgun.constants.IntegrationTestConstants.WEBHOOK_URL_2;
import static com.mailgun.constants.IntegrationTestConstants.WEBHOOK_URL_3;
import static com.mailgun.util.Constants.FIELD_CANNOT_BE_NULL_OR_EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WebhookRequestTest {

    @Test
    void webhookRequestBuilderSuccessTest() {
        WebhookRequest webhookRequest = WebhookRequest.builder()
                .webhookName(WebhookName.CLICKED)
                .url(WEBHOOK_URL_1)
                .urls(Arrays.asList(WEBHOOK_URL_2, WEBHOOK_URL_3))
                .build();

        assertEquals(WebhookName.CLICKED.getValue(), webhookRequest.getWebhookName());
        assertTrue(webhookRequest.getUrls().containsAll(Arrays.asList(WEBHOOK_URL_1, WEBHOOK_URL_2, WEBHOOK_URL_3)));
    }

    @Test
    void messageFieldUrlsNullExceptionTest() {
        WebhookRequest.WebhookRequestBuilder webhookRequestBuilder = WebhookRequest.builder()
                .webhookName(WebhookName.CLICKED);

        Exception exception = assertThrows(IllegalArgumentException.class, webhookRequestBuilder::build);

        assertEquals(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "url(s)"), exception.getMessage());
    }

    @Test
    void messageFieldUrlsEmptyListExceptionTest() {
        WebhookRequest.WebhookRequestBuilder webhookRequestBuilder = WebhookRequest.builder()
                .webhookName(WebhookName.CLICKED)
                .urls(Collections.emptyList());

        Exception exception = assertThrows(IllegalArgumentException.class, webhookRequestBuilder::build);

        assertEquals(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "url(s)"), exception.getMessage());
    }

    @Test
    void messageFieldUrlsEmptyExceptionTest() {
        WebhookRequest.WebhookRequestBuilder webhookRequestBuilder = WebhookRequest.builder()
                .webhookName(WebhookName.CLICKED)
                .url(StringUtils.SPACE)
                .urls(Collections.singletonList(StringUtils.EMPTY));

        Exception exception = assertThrows(IllegalArgumentException.class, webhookRequestBuilder::build);

        assertEquals(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "url(s)"), exception.getMessage());
    }

}
