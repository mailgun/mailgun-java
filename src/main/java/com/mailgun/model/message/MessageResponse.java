package com.mailgun.model.message;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;


/**
 * Response body for {@code POST /v3/{domain_name}/messages} (HTTP 200, {@code application/json}).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/post-v3--domain-name--messages">Send an email</a>
 */
@Value
@Jacksonized
@Builder
public class MessageResponse {

    /**
     * <p>
     * Message id.
     * </p>
     */
    String id;

    /**
     * <p>
     * Result status message.
     * </p>
     */
    String message;

}
