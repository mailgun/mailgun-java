package com.mailgun.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Message information in logs response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/logs/post-v1-analytics-logs#logs/post-v1-analytics-logs/t=response&c=200&path=items/message">Logs message</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemMessage {

    ItemMessageHeaders headers;
    List<ItemMessageAttachment> attachments;
    List<String> recipients;
    Integer size;

    @JsonProperty("scheduled-for")
    Long scheduledFor;
}
