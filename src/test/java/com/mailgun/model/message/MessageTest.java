package com.mailgun.model.message;

import com.mailgun.enums.YesNo;
import com.mailgun.enums.YesNoHtml;
import com.mailgun.util.EmailUtil;
import feign.form.FormData;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.mailgun.constants.TestConstants.*;
import static com.mailgun.util.Constants.FIELD_CANNOT_BE_NULL_OR_EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MessageTest {

    @Test
    void messageMinimumFieldsSuccessTest() {
        Message result = Message.builder()
                .from(TEST_EMAIL_1)
                .to(TEST_EMAIL_2)
                .build();

        assertNotNull(result);
        assertEquals(TEST_EMAIL_1, result.getFrom());
        assertEquals(1, result.getTo().size());
        assertNull(result.getCc());
        assertNull(result.getBcc());
        assertNull(result.getSubject());
        assertNull(result.getText());
        assertNull(result.getAttachment());
        assertNull(result.getInline());
        assertNull(result.getTemplateVersion());
        assertNull(result.getRenderTemplate());
        assertNull(result.getTag());
        assertNull(result.getDkim());
        assertNull(result.getDeliveryTime());
        assertNull(result.getDeliverWithin());
        assertNull(result.getXMailgunDeliverWithin());
        assertNull(result.getTestMode());
        assertNull(result.getTracking());
        assertNull(result.getTrackingClicks());
        assertNull(result.getTrackingOpens());
        assertNull(result.getRequireTls());
        assertNull(result.getSkipVerification());
        assertNull(result.getReplyTo());
        assertNull(result.getRecipientVariables());
        assertNull(result.getArchiveTo());
    }

    @Test
    void messageAllFieldsSuccessTest() throws IOException {
        File file1 = getTempFile("temp.1");
        File file2 = getTempFile("temp.2");
        File file3 = getTempFile("temp.3");
        File file4 = getTempFile("temp.4");
        Map<String, Object> recipientVariables = new HashMap<>();
        Map<String, String> aliceVars = new HashMap<>();
        aliceVars.put("Alice", "1");
        Map<String, String> bobVars = new HashMap<>();
        bobVars.put("Bob", "2");
        recipientVariables.put("firstEmail", aliceVars);
        recipientVariables.put("secondEmail", bobVars);

        Message result = Message.builder()
                .from(TEST_EMAIL_1)
                .to(TEST_EMAIL_2)
                .to(EmailUtil.nameWithEmail(TEST_USER_NAME, TEST_EMAIL_3))
                .to(Arrays.asList(TEST_EMAIL_4, TEST_EMAIL_5))
                .cc(TEST_EMAIL_2)
                .cc(TEST_EMAIL_3)
                .cc(Arrays.asList(TEST_EMAIL_4, TEST_EMAIL_5))
                .bcc(Arrays.asList(TEST_EMAIL_4, TEST_EMAIL_5))
                .bcc(TEST_EMAIL_2)
                .bcc(TEST_EMAIL_3)
                .subject(TEST_EMAIL_SUBJECT)
                .text(TEST_EMAIL_TEXT)
                .attachment(file1)
                .attachment(file2)
                .attachment(Arrays.asList(file3, file4))
                .inline(file1)
                .inline(file2)
                .inline(Arrays.asList(file3, file4))
                .templateVersion(TEMPLATE_VERSION)
                .renderTemplate(true)
                .tag(TEST_TAG_1)
                .tag(TEST_TAG_2)
                .tag(Arrays.asList(TEST_TAG_3, TEST_TAG_4))
                .dkim(true)
                .deliveryTime(ZONED_DATE_TIME_2018_2_3_GMT)
                .deliverWithin("1h")
                .xMailgunDeliverWithin("30m")
                .testMode(true)
                .tracking(true)
                .trackingClicks(YesNoHtml.YES)
                .trackingOpens(true)
                .requireTls(true)
                .skipVerification(true)
                .replyTo(TEST_EMAIL_2)
                .recipientVariables(recipientVariables)
                .archiveTo(TEST_DOMAIN)
                .build();

        assertNotNull(result);
        assertEquals(TEST_EMAIL_1, result.getFrom());
        assertEquals(4, result.getTo().size());
        assertEquals(TEST_EMAIL_SUBJECT, result.getSubject());
        assertEquals(TEST_EMAIL_TEXT, result.getText());
        assertEquals(4, result.getAttachment().size());
        assertEquals(4, result.getInline().size());
        assertEquals(TEMPLATE_VERSION, result.getTemplateVersion());
        assertEquals(YesNo.YES.getValue(), result.getRenderTemplate());
        assertEquals(4, result.getTag().size());
        assertEquals(YesNo.YES.getValue(), result.getDkim());
        assertEquals(ZONED_DATE_TIME_2018_2_3_GMT_STRING, result.getDeliveryTime());
        assertEquals("1h", result.getDeliverWithin());
        assertEquals("30m", result.getXMailgunDeliverWithin());
        assertEquals(YesNo.YES.getValue(), result.getTestMode());
        assertEquals(YesNo.YES.getValue(), result.getTracking());
        assertEquals(YesNoHtml.YES.getValue(), result.getTrackingClicks());
        assertEquals(YesNo.YES.getValue(), result.getTrackingOpens());
        assertEquals(YesNo.YES.getValue(), result.getRequireTls());
        assertEquals(YesNo.YES.getValue(), result.getSkipVerification());
        assertEquals(TEST_EMAIL_2, result.getReplyTo());
        assertEquals("{\"firstEmail\":{\"Alice\":\"1\"},\"secondEmail\":{\"Bob\":\"2\"}}", result.getRecipientVariables());
        assertEquals(TEST_DOMAIN, result.getArchiveTo());
    }

    @Test
    void messageAllFieldsSetFalseSuccessTest() {
        Message result = Message.builder()
                .from(TEST_EMAIL_1)
                .to(TEST_EMAIL_2)
                .html(TEST_EMAIL_HTML)
                .renderTemplate(false)
                .dkim(false)
                .testMode(false)
                .tracking(false)
                .trackingClicks(YesNoHtml.HTML_ONLY)
                .trackingOpens(false)
                .requireTls(false)
                .skipVerification(false)
                .build();

        assertNotNull(result);

        assertEquals(TEST_EMAIL_HTML, result.getHtml());
        assertNull(result.getRenderTemplate());
        assertEquals(YesNo.NO.getValue(), result.getDkim());
        assertNull(result.getTestMode());
        assertEquals(YesNo.NO.getValue(), result.getTracking());
        assertEquals(YesNoHtml.HTML_ONLY.getValue(), result.getTrackingClicks());
        assertEquals(YesNo.NO.getValue(), result.getTrackingOpens());
        assertEquals(YesNo.NO.getValue(), result.getRequireTls());
        assertEquals(YesNo.NO.getValue(), result.getSkipVerification());

    }

    @Test
    void messageFieldFromNullExceptionTest() {
        Message.MessageBuilder messageBuilder = Message.builder();

        Exception exception = assertThrows(IllegalArgumentException.class, messageBuilder::build);

        assertEquals(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "from"), exception.getMessage());
    }

    @Test
    void messageFieldFromEmptyExceptionTest() {
        Message.MessageBuilder messageBuilder = Message.builder()
                .from(StringUtils.EMPTY);

        Exception exception = assertThrows(IllegalArgumentException.class, messageBuilder::build);

        assertEquals(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "from"), exception.getMessage());
    }

    @Test
    void messageFieldFromSpaceExceptionTest() {
        Message.MessageBuilder messageBuilder = Message.builder()
                .from(StringUtils.SPACE);

        Exception exception = assertThrows(IllegalArgumentException.class, messageBuilder::build);

        assertEquals(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "from"), exception.getMessage());
    }

    @Test
    void messageFieldToNullExceptionTest() {
        Message.MessageBuilder messageBuilder = Message.builder()
                .from(TEST_EMAIL_1);

        Exception exception = assertThrows(IllegalArgumentException.class, messageBuilder::build);

        assertEquals(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "to"), exception.getMessage());
    }

    @Test
    void messageFieldToEmptyListExceptionTest() {
        Message.MessageBuilder messageBuilder = Message.builder()
                .from(TEST_EMAIL_1)
                .to(Collections.emptyList());

        Exception exception = assertThrows(IllegalArgumentException.class, messageBuilder::build);

        assertEquals(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "to"), exception.getMessage());
    }

    @Test
    void messageFieldToEmptyExceptionTest() {
        Message.MessageBuilder messageBuilder = Message.builder()
                .from(TEST_EMAIL_1)
                .to(Arrays.asList(StringUtils.SPACE, StringUtils.EMPTY));

        Exception exception = assertThrows(IllegalArgumentException.class, messageBuilder::build);

        assertEquals(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "to"), exception.getMessage());
    }


    @Test
    void messageAttachmentAndFromDataTogetherExceptionTest() throws IOException {
        File file = getTempFile("temp.1");
        InputStream inputStream = new FileInputStream(getTempFile("temp.2"));
        byte[] txtBytes = IOUtils.toByteArray(inputStream);
        FormData formData = new FormData("text/plain", "temp.txt", txtBytes);

        Message.MessageBuilder messageBuilder = Message.builder()
            .from(TEST_EMAIL_1)
            .to(TEST_EMAIL_2)
            .attachment(file)
            .formData(formData);

        Exception exception = assertThrows(IllegalArgumentException.class, messageBuilder::build);

        assertEquals("You cannot use 'attachment' and 'formData' together", exception.getMessage());
    }

    private File getTempFile(String prefix) throws IOException {
        File file = File.createTempFile(prefix, ".tmp", new File("src/test/java/com/mailgun/utils"));
        file.deleteOnExit();
        return file;
    }

}
