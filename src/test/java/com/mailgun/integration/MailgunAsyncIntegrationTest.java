package com.mailgun.integration;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import feign.AsyncClient;
import feign.Client;

import static com.mailgun.constants.IntegrationTestConstants.EMAIL_FROM;
import static com.mailgun.constants.IntegrationTestConstants.EMAIL_TO;
import static com.mailgun.constants.IntegrationTestConstants.MAIN_DOMAIN;
import static com.mailgun.constants.IntegrationTestConstants.PRIVATE_API_KEY;
import static com.mailgun.constants.TestConstants.EMAIL_RESPONSE_MESSAGE;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_TEXT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled("Use these tests as examples for a real implementation.")
class MailgunAsyncIntegrationTest {

    @Test
    void sendMessageAsyncTest() throws ExecutionException, InterruptedException {
        Message message = getMessage("Async message example");

        MailgunMessagesApi mailgunAsyncMessagesApi = MailgunClient.config(PRIVATE_API_KEY)
            .createAsyncApi(MailgunMessagesApi.class);

        CompletableFuture<MessageResponse> result = mailgunAsyncMessagesApi.sendMessageAsync(MAIN_DOMAIN, message);

        MessageResponse messageResponse = result.get();
        assertNotNull(messageResponse.getId());
        assertEquals(EMAIL_RESPONSE_MESSAGE, messageResponse.getMessage());
    }


    @Test
    void sendMessageAsync_TwoThreads_Test() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        AsyncClient.Default<Object> asyncClient = new AsyncClient.Default<>(
            new Client.Default(null, null), executor);

        MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(PRIVATE_API_KEY)
            .client(asyncClient)
            .createAsyncApi(MailgunMessagesApi.class);

        Message message1 = getMessage("Async message example 1");
        Message message2 = getMessage("Async message example 2");

        List<CompletableFuture<MessageResponse>> futures = Stream.of(message1, message2)
            .map(message -> mailgunMessagesApi.sendMessageAsync(MAIN_DOMAIN, message))
            .collect(Collectors.toList());

        List<MessageResponse> results = futures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());

        assertEquals(2, results.size());
        results.forEach(messageResponse -> {
            assertNotNull(messageResponse.getId());
            assertEquals(EMAIL_RESPONSE_MESSAGE, messageResponse.getMessage());
        });
    }

    private Message getMessage(String subject) {
        return Message.builder()
            .from(EMAIL_FROM)
            .to(EMAIL_TO)
            .subject(subject)
            .text(TEST_EMAIL_TEXT)
            .build();
    }

}