package com.mailgun.model.message;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * JSON body for {@code GET /v3/domains/{domain_name}/messages/{storage_key}} (HTTP 200).
 * <p>
 * The {@code storage_key} comes from {@code storage.key} on sending-related events (for example accepted or delivered).
 * Keys remain valid for your domain's message retention window.
 * </p>
 * <p>
 * Error responses (HTTP 400, 404) use {@code {"message":"..."}}; decode with {@link com.mailgun.model.ResponseWithMessage}.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/get-v3-domains--domain-name--messages--storage-key">Retrieve a stored email</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreMessageResponse {

    @JsonProperty("Content-Transfer-Encoding")
    String contentTransferEncoding;

    @JsonProperty("Content-Type")
    String contentType;

    @JsonProperty("From")
    String from;

    @JsonProperty("Message-Id")
    String messageId;

    @JsonProperty("Mime-Version")
    String mimeVersion;

    @JsonProperty("Subject")
    @JsonAlias("subject")
    String subject;

    @JsonProperty("To")
    String to;

    @JsonProperty("X-Mailgun-Tag")
    String xMailgunTag;

    String sender;

    String recipients;

    @JsonProperty("body-html")
    String bodyHtml;

    @JsonProperty("body-plain")
    String bodyPlain;

    @JsonProperty("stripped-html")
    String strippedHtml;

    @JsonProperty("stripped-text")
    String strippedText;

    @JsonProperty("stripped-signature")
    String strippedSignature;

    /**
     * Header name/value pairs as returned by the API (each element is typically a two-element list).
     */
    @JsonProperty("message-headers")
    List<List<String>> headers;

    @JsonProperty("X-Mailgun-Template-Name")
    String xMailgunTemplateName;

    @JsonProperty("X-Mailgun-Template-Variables")
    String xMailgunTemplateVariables;

    /**
     * Attachments metadata when present (not listed in all OpenAPI summaries; ignored if absent).
     */
    List<StoreMessageAttachments> attachments;

}
