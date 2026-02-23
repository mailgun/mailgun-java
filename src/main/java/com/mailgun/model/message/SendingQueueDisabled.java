package com.mailgun.model.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Disabled state of a sending queue (regular or scheduled).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/get-v3-domains--name--sending-queues">Get messages queue status</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendingQueueDisabled {

    /**
     * End date in RFC-822 date format.
     */
    String until;

    /**
     * Cause description.
     */
    String reason;
}
