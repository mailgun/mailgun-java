package com.mailgun.integration;

import java.io.IOException;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.mailgun.api.v3.MailgunEventsApi;
import com.mailgun.api.v3.MailgunStoreMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.events.EventsQueryOptions;
import com.mailgun.model.events.EventsResponse;
import com.mailgun.model.message.MessageResponse;
import com.mailgun.model.message.StoreMessageResponse;
import com.mailgun.util.ObjectMapperUtil;
import feign.Response;

import static com.mailgun.constants.IntegrationTestConstants.EMAIL_TO;
import static com.mailgun.constants.IntegrationTestConstants.MAIN_DOMAIN;
import static com.mailgun.constants.IntegrationTestConstants.PRIVATE_API_KEY;
import static com.mailgun.constants.TestConstants.EMAIL_RESPONSE_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Use these tests as examples for a real implementation.")
class MailgunStoreMessagesIntegrationTest {

    private static String storedMessageUrl;

    @BeforeAll
    static void beforeAll() {
        MailgunEventsApi mailgunEventsApi = MailgunClient.config(PRIVATE_API_KEY)
            .createApi(MailgunEventsApi.class);

        EventsQueryOptions eventsQueryOptions = EventsQueryOptions.builder()
            .messageId("the id of the required message")
            .build();

        EventsResponse events = mailgunEventsApi.getEvents(MAIN_DOMAIN, eventsQueryOptions);

        storedMessageUrl = events.getItems().get(0).getStorage().getUrl();
    }

    @Test
    void resendMessage_UnsupportedOperationException_Test() {
        assertThrows(UnsupportedOperationException.class, () ->
            MailgunClient.config(storedMessageUrl, PRIVATE_API_KEY)
                .createApi(MailgunStoreMessagesApi.class)
        );
    }

    @Test
    void resendMessageTest() {
        MailgunStoreMessagesApi mailgunStoreMessagesApi = MailgunClient.config(storedMessageUrl, PRIVATE_API_KEY)
            .createApiWithAbsoluteUrl(MailgunStoreMessagesApi.class);

        MessageResponse result = mailgunStoreMessagesApi.resendMessage(EMAIL_TO);

        assertNotNull(result.getId());
        assertEquals(EMAIL_RESPONSE_MESSAGE, result.getMessage());
    }

    @Test
    void retrieveMessageTest() {
        MailgunStoreMessagesApi mailgunStoreMessagesApi = MailgunClient.config(storedMessageUrl, PRIVATE_API_KEY)
            .createApiWithAbsoluteUrl(MailgunStoreMessagesApi.class);

        StoreMessageResponse result = mailgunStoreMessagesApi.retrieveMessage();

        assertTrue(StringUtils.isNotEmpty(result.getFrom()));
        assertTrue(StringUtils.isNotEmpty(result.getTo()));
    }

    @Test
    void retrieveMessage_FeignResponse_JsonNode_Test() throws IOException {
        MailgunStoreMessagesApi mailgunStoreMessagesApi = MailgunClient.config(storedMessageUrl, PRIVATE_API_KEY)
            .createApiWithAbsoluteUrl(MailgunStoreMessagesApi.class);

        Response feignResponse = mailgunStoreMessagesApi.retrieveMessageFeignResponse();

        JsonNode jsonNode = ObjectMapperUtil.decode(feignResponse, JsonNode.class);
        assertTrue(jsonNode.size() > 10);
        assertNotNull(jsonNode.get("From"));
        assertNotNull(jsonNode.get("To"));
        assertNotNull(jsonNode.get("Message-Id"));
        JsonNode messageHeaders = jsonNode.get("message-headers");
        assertTrue(messageHeaders.isArray());
        Double mimeVersion = StreamSupport.stream(messageHeaders.spliterator(), false)
            .filter(node -> node.get(0).asText().equals("Mime-Version"))
            .map(node -> node.get(1))
            .map(JsonNode::asDouble)
            .findFirst()
            .orElse(null);
        assertNotNull(mimeVersion);
        assertTrue(mimeVersion >= 1.0);
    }

}