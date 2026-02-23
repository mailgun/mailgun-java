package com.mailgun.model.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response for get messages queue status (default and scheduled queues).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/get-v3-domains--name--sending-queues">Get messages queue status</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendingQueuesResponse {

    /**
     * Regular (default) message queue.
     */
    SendingQueueInfo regular;

    /**
     * Scheduled message queue.
     */
    SendingQueueInfo scheduled;
}
