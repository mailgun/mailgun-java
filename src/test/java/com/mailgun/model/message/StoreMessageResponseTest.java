package com.mailgun.model.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mailgun.util.ObjectMapperUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StoreMessageResponseTest {

    @Test
    void deserializeRetrieveStoredEmailResponseTest() throws JsonProcessingException {
        String json = "{"
            + "\"Content-Transfer-Encoding\":\"quoted-printable\","
            + "\"Content-Type\":\"multipart/alternative; boundary=abc\","
            + "\"From\":\"Bob <bob@example.com>\","
            + "\"Message-Id\":\"<20130809162912.42289.52534@example.com>\","
            + "\"Mime-Version\":\"1.0\","
            + "\"Subject\":\"Re: hello\","
            + "\"To\":\"Alice <alice@example.com>\","
            + "\"X-Mailgun-Tag\":\"newsletter\","
            + "\"sender\":\"bob@example.com\","
            + "\"recipients\":\"alice@example.com\","
            + "\"body-html\":\"<p>Full HTML</p>\","
            + "\"body-plain\":\"Full plain\","
            + "\"stripped-html\":\"<p>Stripped</p>\","
            + "\"stripped-text\":\"Stripped\","
            + "\"stripped-signature\":\"--sig\","
            + "\"message-headers\":[[\"Subject\",\"Re: hello\"]],"
            + "\"X-Mailgun-Template-Name\":\"welcome\","
            + "\"X-Mailgun-Template-Variables\":\"{}\""
            + "}";

        StoreMessageResponse r = ObjectMapperUtil.getObjectMapper().readValue(json, StoreMessageResponse.class);

        assertEquals("quoted-printable", r.getContentTransferEncoding());
        assertEquals("multipart/alternative; boundary=abc", r.getContentType());
        assertEquals("Bob <bob@example.com>", r.getFrom());
        assertEquals("<20130809162912.42289.52534@example.com>", r.getMessageId());
        assertEquals("1.0", r.getMimeVersion());
        assertEquals("Re: hello", r.getSubject());
        assertEquals("Alice <alice@example.com>", r.getTo());
        assertEquals("newsletter", r.getXMailgunTag());
        assertEquals("bob@example.com", r.getSender());
        assertEquals("alice@example.com", r.getRecipients());
        assertEquals("<p>Full HTML</p>", r.getBodyHtml());
        assertEquals("Full plain", r.getBodyPlain());
        assertEquals("<p>Stripped</p>", r.getStrippedHtml());
        assertEquals("Stripped", r.getStrippedText());
        assertEquals("--sig", r.getStrippedSignature());
        assertNotNull(r.getHeaders());
        assertEquals(List.of("Subject", "Re: hello"), r.getHeaders().get(0));
        assertEquals("welcome", r.getXMailgunTemplateName());
        assertEquals("{}", r.getXMailgunTemplateVariables());
    }

    @Test
    void deserializeSubjectAliasLowercaseTest() throws JsonProcessingException {
        String json = "{\"subject\":\"Hello\",\"To\":\"a@b.com\",\"From\":\"c@d.com\",\"Message-Id\":\"<id>\","
            + "\"Mime-Version\":\"1.0\",\"Content-Type\":\"text/plain\",\"Content-Transfer-Encoding\":\"7bit\","
            + "\"X-Mailgun-Tag\":\"\",\"sender\":\"c@d.com\",\"recipients\":\"a@b.com\","
            + "\"body-html\":\"\",\"body-plain\":\"\",\"stripped-html\":\"\",\"stripped-text\":\"\","
            + "\"stripped-signature\":\"\",\"message-headers\":[],\"X-Mailgun-Template-Name\":\"\","
            + "\"X-Mailgun-Template-Variables\":\"\"}";

        StoreMessageResponse r = ObjectMapperUtil.getObjectMapper().readValue(json, StoreMessageResponse.class);
        assertEquals("Hello", r.getSubject());
    }
}
