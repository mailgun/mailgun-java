package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import com.mailgun.utils.MessageUtils;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.mailgun.constants.TestConstants.EMAIL_RESPONSE_ID;
import static com.mailgun.constants.TestConstants.EMAIL_RESPONSE_MESSAGE;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_1;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_2;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_SUBJECT;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_TEXT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MailgunMessagesApiTest extends WireMockBaseTest {

    private MailgunMessagesApi mailgunMessagesApi;

    @BeforeEach
    void setUp() {
        mailgunMessagesApi = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunMessagesApi.class);
    }

    @Test
    void sendMessageWithDomainSuccessTest() {
        stubFor(post(urlEqualTo("/" + MailgunApi.getApiVersion().getValue() + "/" + TEST_DOMAIN + "/messages"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type",
                        containing("multipart/form-data"))
                .withHeader("Accept",
                        equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(MessageUtils.getMessageResponseJsonString())));

        Message message = Message.builder()
                .from(TEST_EMAIL_1)
                .to(TEST_EMAIL_2)
                .subject(TEST_EMAIL_SUBJECT)
                .text(TEST_EMAIL_TEXT)
                .build();

        MessageResponse result = mailgunMessagesApi.sendMessage(TEST_DOMAIN, message);

        assertEquals(EMAIL_RESPONSE_ID, result.getId());
        assertEquals(EMAIL_RESPONSE_MESSAGE, result.getMessage());
    }

}
