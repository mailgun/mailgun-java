package com.mailgun.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Event message info.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html">Events API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventMessageAttachment {

    /**
     * <p>
     * Attachment file name.
     * </p>
     */
    String filename;

    /**
     * <p>
     * Attachment content type.
     * </p>
     */
    @JsonProperty("content-type")
    String contentType;

    /**
     * <p>
     * Attachment size.
     * </p>
     */
    Integer size;

}
