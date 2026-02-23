package com.mailgun.model.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Message queue info (regular or scheduled).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/get-v3-domains--name--sending-queues">Get messages queue status</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendingQueueInfo {

    /**
     * Flag indicating no new messages are able to be submitted.
     */
    @JsonProperty("is_disabled")
    Boolean isDisabled;

    /**
     * Disabled state details when the queue is disabled.
     */
    SendingQueueDisabled disabled;
}
