package com.mailgun.model.message;

import com.mailgun.enums.YesNo;
import com.mailgun.enums.YesNoHtml;
import com.mailgun.form.PojoUtil;
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
import java.util.Map;

import static com.mailgun.constants.TestConstants.TEST_EMAIL_1;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_2;
import static com.mailgun.util.Constants.FIELD_CANNOT_BE_NULL_OR_EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MailgunMimeMessageTest {

    @Test
    void mimeMinimumFieldsSuccessTest() throws IOException {
        File f = tempFile("mime-min");

        MailgunMimeMessage result = MailgunMimeMessage.builder()
                .to(TEST_EMAIL_2)
                .message(f)
                .build();

        assertNotNull(result);
        assertEquals(1, result.getTo().size());
        assertEquals(f, result.getMessage());
        assertNull(result.getMessageFormData());
        assertNull(result.getTemplate());
        assertNull(result.getSecondaryDkim());
        assertNull(result.getUserVariables());
        assertNull(result.getHeaders());
    }

    @Test
    void mimeMessageFormDataSuccessTest() throws IOException {
        File raw = tempFile("mime-bytes");
        byte[] bytes;
        try (InputStream in = new FileInputStream(raw)) {
            bytes = IOUtils.toByteArray(in);
        }
        FormData formData = new FormData("message/rfc822", "message.eml", bytes);

        MailgunMimeMessage result = MailgunMimeMessage.builder()
                .to(TEST_EMAIL_2)
                .messageFormData(formData)
                .build();

        assertNotNull(result);
        assertNull(result.getMessage());
        assertEquals(formData, result.getMessageFormData());
    }

    @Test
    void mimeUserVariablesAndHeadersFormKeysTest() throws IOException {
        File f = tempFile("mime-map");

        MailgunMimeMessage message = MailgunMimeMessage.builder()
                .to(TEST_EMAIL_2)
                .message(f)
                .userVariables(Map.of("campaign", "spring"))
                .headers(Map.of("X-Custom-Header", "abc"))
                .build();

        Map<String, Object> map = PojoUtil.toMap(message);
        assertEquals("spring", map.get("v:campaign"));
        assertEquals("abc", map.get("h:X-Custom-Header"));
    }

    @Test
    void mimeOptionalSendOptionsTest() throws IOException {
        File f = tempFile("mime-opts");

        MailgunMimeMessage result = MailgunMimeMessage.builder()
                .to(TEST_EMAIL_1)
                .to(Arrays.asList(TEST_EMAIL_2))
                .message(f)
                .template("welcome")
                .templateVersion("tag")
                .renderTemplate(true)
                .tag("a")
                .tag(Collections.singletonList("b"))
                .dkim(true)
                .secondaryDkim("example.com/s1")
                .secondaryDkimPublic("pub.example.com/s1")
                .deliveryTimeOptimizePeriod("48h")
                .timeZoneLocalize("10:30")
                .deliverWithin("2h")
                .xMailgunDeliverWithin("45m")
                .testMode(true)
                .tracking(true)
                .trackingClicks(YesNoHtml.HTML_ONLY)
                .trackingOpens(false)
                .requireTls(true)
                .skipVerification(false)
                .sendingIp("1.2.3.4")
                .sendingIpPool("pool-1")
                .trackingPixelLocationTop(YesNoHtml.YES)
                .suppressHeaders("X-Mailgun-Tag")
                .archiveTo("https://example.com/hook")
                .replyTo(TEST_EMAIL_2)
                .mailgunVariables(Map.of("name", "Ann"))
                .myVar("plain")
                .build();

        assertEquals("welcome", result.getTemplate());
        assertEquals("tag", result.getTemplateVersion());
        assertEquals(YesNo.YES.getValue(), result.getRenderTemplate());
        assertEquals(2, result.getTag().size());
        assertEquals(YesNo.YES.getValue(), result.getDkim());
        assertEquals("example.com/s1", result.getSecondaryDkim());
        assertEquals("pub.example.com/s1", result.getSecondaryDkimPublic());
        assertEquals("48h", result.getDeliveryTimeOptimizePeriod());
        assertEquals("10:30", result.getTimeZoneLocalize());
        assertEquals("2h", result.getDeliverWithin());
        assertEquals("45m", result.getXMailgunDeliverWithin());
        assertEquals(YesNo.YES.getValue(), result.getTestMode());
        assertEquals(YesNo.YES.getValue(), result.getTracking());
        assertEquals(YesNoHtml.HTML_ONLY.getValue(), result.getTrackingClicks());
        assertEquals(YesNo.NO.getValue(), result.getTrackingOpens());
        assertEquals(YesNo.YES.getValue(), result.getRequireTls());
        assertEquals(YesNo.NO.getValue(), result.getSkipVerification());
        assertEquals("1.2.3.4", result.getSendingIp());
        assertEquals("pool-1", result.getSendingIpPool());
        assertEquals(YesNoHtml.YES.getValue(), result.getTrackingPixelLocationTop());
        assertEquals("X-Mailgun-Tag", result.getSuppressHeaders());
        assertEquals("https://example.com/hook", result.getArchiveTo());
        assertEquals(TEST_EMAIL_2, result.getReplyTo());
        assertEquals("plain", result.getMyVar());
        assertEquals("{\"name\":\"Ann\"}", result.getMailgunVariables());
    }

    @Test
    void mimeToMissingFailsTest() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> MailgunMimeMessage.builder().build());
        assertEquals(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "to"), ex.getMessage());
    }

    @Test
    void mimeMessageMissingFailsTest() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> MailgunMimeMessage.builder().to(TEST_EMAIL_2).build());
        assertEquals(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "message"), ex.getMessage());
    }

    @Test
    void mimeFileAndFormDataConflictTest() throws IOException {
        File f = tempFile("mime-dup");
        FormData fd = new FormData("message/rfc822", "a.eml", new byte[] {1, 2});

        MailgunMimeMessage.MailgunMimeMessageBuilder b = MailgunMimeMessage.builder()
                .to(TEST_EMAIL_2)
                .message(f)
                .messageFormData(fd);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, b::build);
        assertEquals("You cannot use 'message' (file) and 'messageFormData' together", ex.getMessage());
    }

    @Test
    void mimeToOnlyBlanksFailsTest() {
        MailgunMimeMessage.MailgunMimeMessageBuilder b = MailgunMimeMessage.builder()
                .to(Arrays.asList(StringUtils.SPACE, StringUtils.EMPTY));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, b::build);
        assertEquals(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "to"), ex.getMessage());
    }

    private static File tempFile(String prefix) throws IOException {
        File file = File.createTempFile(prefix, ".eml", new File("src/test/java/com/mailgun/utils"));
        file.deleteOnExit();
        return file;
    }
}
