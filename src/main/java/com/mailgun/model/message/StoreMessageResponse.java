package com.mailgun.model.message;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * The object returned from a stored message url.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-sending.html#retrieving-stored-messages">Retrieving Stored Messages</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreMessageResponse {

    /**
     * <p>
     * Recipient of the message as reported by MAIL TO during SMTP chat.
     * </p>
     */
    String recipients;

    /**
     * <p>
     * Email address from <code>To</code> header.
     * </p>
     */
    @JsonProperty("To")
    String to;

    /**
     * <p>
     * Sender of the message as reported by MAIL FROM during SMTP chat. Note: this value may differ from From MIME header.
     * </p>
     */
    String sender;

    /**
     * <p>
     * Sender of the message as reported by From message header, for example “Bob Lee <blee@mailgun.net>”.
     * </p>
     */
    @JsonProperty("From")
    String from;

    /**
     * <p>
     * Subject string.
     * </p>
     */
    String subject;

    /**
     * <p>
     * text version of the email. This field is always present. If the incoming message only has HTML body, Mailgun will create a text representation for you.
     * </p>
     */
    @JsonProperty("body-plain")
    String bodyPlain;

    /**
     * <p>
     * Text version of the message without quoted parts and signature block (if found).
     * </p>
     */
    @JsonProperty("stripped-text")
    String strippedText;

    /**
     * <p>
     * The signature block stripped from the plain text message (if found).
     * </p>
     */
    @JsonProperty("stripped-signature")
    String strippedSignature;

    /**
     * <p>
     * HTML version of the message, without quoted parts.
     * </p>
     */
    @JsonProperty("stripped-html")
    String strippedHtml;

    /**
     * <p>
     * Message id.
     * </p>
     */
    @JsonProperty("Message-Id")
    String messageId;

    /**
     * <p>
     * List of {@link StoreMessageAttachments}.
     * </p>
     */
    List<StoreMessageAttachments> attachments;

    /**
     * <p>
     * List of headers.
     * </p>
     */
    @JsonProperty("message-headers")
    List<List<String>> headers;

    /**
     * <p>
     * Content type.
     * </p>
    */
    @JsonProperty("Content-Type")
    String contentType;

     /**
     * <p>
     * Mime-Version.
     * </p>
     */
    @JsonProperty("Mime-Version")
    String mimeVersion;

}
