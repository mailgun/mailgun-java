package com.mailgun.model.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Store Message Attachments.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-sending.html#retrieving-stored-messages">Retrieving Stored Messages</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreMessageAttachments {

    /**
     * <p>
     * Attachments name.
     * </p>
     */
    String name;

    /**
     * <p>
     * Attachments contentType.
     * </p>
     */
    @JsonProperty("content-type")
    String contentType;

    /**
     * <p>
     * Attachments size.
     * </p>
     */
    Integer size;

    /**
     * <p>
     * Attachments url.
     * </p>
     */
    String url;

}
