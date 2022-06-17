package com.mailgun.integration;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.MailgunMimeMessage;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import com.mailgun.util.ObjectMapperUtil;
import feign.Request;
import feign.Response;

import static com.mailgun.constants.IntegrationTestConstants.EMAIL_FROM;
import static com.mailgun.constants.IntegrationTestConstants.EMAIL_TO;
import static com.mailgun.constants.IntegrationTestConstants.MAILING_LIST_ADDRESS;
import static com.mailgun.constants.IntegrationTestConstants.MAIN_DOMAIN;
import static com.mailgun.constants.IntegrationTestConstants.PRIVATE_API_KEY;
import static com.mailgun.constants.IntegrationTestConstants.TEMPLATE_NAME;
import static com.mailgun.constants.IntegrationTestConstants.TEMPLATE_VERSION_TAG_1;
import static com.mailgun.constants.TestConstants.EMAIL_RESPONSE_MESSAGE;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_1;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_SUBJECT;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_TEXT;
import static com.mailgun.constants.TestConstants.TEST_USER_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunMessagesIntegrationTest {

    private static MailgunMessagesApi mailgunMessagesApi;

    @BeforeAll
    static void beforeAll() {
        mailgunMessagesApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunMessagesApi.class);
    }

    @Test
    void message_MinimumFields_Test() {
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .subject("Minimum fields example")
                .text(TEST_EMAIL_TEXT)
                .build();

        MessageResponse result = mailgunMessagesApi.sendMessage(MAIN_DOMAIN, message);

        assertNotNull(result.getId());
        assertEquals(EMAIL_RESPONSE_MESSAGE, result.getMessage());
    }

    @Test
    void message_MinimumFields_FeignResponse_Test() throws IOException {
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .subject(TEST_EMAIL_SUBJECT)
                .text(TEST_EMAIL_TEXT)
                .build();

        Response feignResponse = mailgunMessagesApi.sendMessageFeignResponse(MAIN_DOMAIN, message);

        assertEquals(200, feignResponse.status());
        assertEquals("OK", feignResponse.reason());
        Request request = feignResponse.request();
        assertEquals(Request.HttpMethod.POST, request.httpMethod());
        assertNotNull(feignResponse.body());
        MessageResponse messageResponse = ObjectMapperUtil.decode(feignResponse, MessageResponse.class);
        assertNotNull(messageResponse.getId());
        assertEquals(EMAIL_RESPONSE_MESSAGE, messageResponse.getMessage());
    }

    @Test
    void message_Template_Test() {
        Map<String, Object> mailgunVariables = new LinkedHashMap<>();
        mailgunVariables.put("name", TEST_USER_NAME);

        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .subject(TEST_EMAIL_SUBJECT)
                .template(TEMPLATE_NAME)
                .templateVersion(TEMPLATE_VERSION_TAG_1)
                .renderTemplate(true)
                .mailgunVariables(mailgunVariables)
                .build();

        MessageResponse result = mailgunMessagesApi.sendMessage(MAIN_DOMAIN, message);

        assertEquals(EMAIL_RESPONSE_MESSAGE, result.getMessage());
//        Template:
//        "Hey, {{name}}!"
//        Result:
//        Hey, Zarathustra!
    }

    @Test
    void message_Batch_Sending_Template_Test() {
        Map<String, Object> recipientVariables = new HashMap<>();

        Map<String, Object> mVars = new HashMap<>();
        mVars.put("name", "Alice");
        mVars.put("id", 1);
        recipientVariables.put(EMAIL_TO, mVars);

        Map<String, Object> bobVars = new HashMap<>();
        bobVars.put("name", "Bob");
        bobVars.put("id", 2);
        recipientVariables.put(EMAIL_FROM, bobVars);

        Message message = Message.builder()
            .from(EMAIL_FROM)
            .to(Arrays.asList(EMAIL_TO, EMAIL_FROM))
            .subject("Hey %recipient.name%")
            .text("If you wish to unsubscribe, click <https://mailgun.com/unsubscribe/%recipient.id%>")
            .recipientVariables(recipientVariables)
            .build();

        MessageResponse result = mailgunMessagesApi.sendMessage(MAIN_DOMAIN, message);

        assertEquals(EMAIL_RESPONSE_MESSAGE, result.getMessage());
        //        EMAIL_TO recipient result:
        //        subject: Hey Alice
        //        text: If you wish to unsubscribe, click <https://mailgun.com/unsubscribe/1>

        //        EMAIL_FROM recipient result:
        //        subject: Hey Bob
        //        text: If you wish to unsubscribe, click <https://mailgun.com/unsubscribe/2>
    }

    @Test
    void message_HTML_Test() {
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .subject("HTML example.")
                .html("<html>\n" +
                        "<body>\n" +
                        "\t<h1>Sending HTML emails with Mailgun</h1>\n" +
                        "\t<p style=\"color:blue; font-size:30px;\">Hello world</p>\n" +
                        "\t<p style=\"font-size:30px;\">More examples can be found <a href=\"https://documentation.mailgun.com/en/latest/api-sending.html#examples\">here</a></p>\n" +
                        "</body>\n" +
                        "</html>")
                .build();

        MessageResponse result = mailgunMessagesApi.sendMessage(MAIN_DOMAIN, message);

        assertEquals(EMAIL_RESPONSE_MESSAGE, result.getMessage());
    }

    @Test
    void message_Attachment_Test() {
        File helloWorld = new File("src/test/resources/hello_world.txt");
        File mailgunAwesomeness = new File("src/test/resources/mailgun_awesomeness.txt");
        File testImages = new File("src/test/resources/test_images.jpeg");
        File mailgunLogo = new File("src/test/resources/mailgun_logo.png");

        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .subject("Attachments example.")
                .text(TEST_EMAIL_TEXT)
                .attachment(helloWorld)
                .attachment(testImages)
                .attachment(Arrays.asList(mailgunAwesomeness, mailgunLogo))
                .build();

        MessageResponse result = mailgunMessagesApi.sendMessage(MAIN_DOMAIN, message);

        assertEquals(EMAIL_RESPONSE_MESSAGE, result.getMessage());
    }

    @Test
    void message_Inline_Simple_Test() {
        File mailgunLogo = new File("src/test/resources/mailgun_logo.png");

        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .subject("Inline simple example.")
                .text(TEST_EMAIL_TEXT)
                .inline(mailgunLogo)
                .build();

        MessageResponse result = mailgunMessagesApi.sendMessage(MAIN_DOMAIN, message);

        assertEquals(EMAIL_RESPONSE_MESSAGE, result.getMessage());
    }

    @Test
    void message_Inline_Multiple_Files_Test() {
        File mailgunLogo = new File("src/test/resources/mailgun_logo.png");
        File testImages = new File("src/test/resources/test_images.jpeg");

        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .subject("Inline multiple files example.")
                .html("Text above images." +
                        "<div><img height=200 id=\"1\" src=\"cid:mailgun_logo.png\"/></div>" +
                        "Text between images." +
                        "<div><img id=\"2\" src=\"cid:test_images.jpeg\"/></div>" +
                        "Text below images.")
                .inline(mailgunLogo)
                .inline(testImages)
                .build();

        MessageResponse result = mailgunMessagesApi.sendMessage(MAIN_DOMAIN, message);

        assertEquals(EMAIL_RESPONSE_MESSAGE, result.getMessage());
    }

    @Test
    void message_DeliveryTime_Test() {
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .subject("Delivery time example.")
                .text(TEST_EMAIL_TEXT)
                .deliveryTime(ZonedDateTime.now().plusMinutes(2L)) // Two minutes delay.
                .build();

        MessageResponse result = mailgunMessagesApi.sendMessage(MAIN_DOMAIN, message);

        assertEquals(EMAIL_RESPONSE_MESSAGE, result.getMessage());
    }

    @Test
    void message_ReplyTo_Test() {
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .subject("Reply-to example.")
                .text(TEST_EMAIL_TEXT)
                .replyTo(TEST_EMAIL_1)
                .build();

        MessageResponse result = mailgunMessagesApi.sendMessage(MAIN_DOMAIN, message);

        assertEquals(EMAIL_RESPONSE_MESSAGE, result.getMessage());
    }

    @Test
    void message_MailingList_Test() {
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(MAILING_LIST_ADDRESS)
                .subject("Mailing List example")
                .text(TEST_EMAIL_TEXT)
                .build();

        MessageResponse result = mailgunMessagesApi.sendMessage(MAIN_DOMAIN, message);

        assertNotNull(result.getId());
        assertEquals(EMAIL_RESPONSE_MESSAGE, result.getMessage());
    }

    @Test
    void sendMIMEMessage_Test() {
        File mimeMessage = new File("src/test/resources/mime-message.txt");

        MailgunMimeMessage mailgunMimeMessage = MailgunMimeMessage.builder()
            .to(EMAIL_TO)
            .message(mimeMessage)
            .build();

        MessageResponse result = mailgunMessagesApi.sendMIMEMessage(MAIN_DOMAIN, mailgunMimeMessage);

        assertNotNull(result.getId());
        assertEquals(EMAIL_RESPONSE_MESSAGE, result.getMessage());
    }

}
