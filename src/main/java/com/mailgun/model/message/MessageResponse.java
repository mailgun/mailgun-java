package com.mailgun.model.message;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;


/**
 * <p>
 * The object is used for sending messages(emails) using Mailgun API.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-sending.html#sending">field-explanation</a>
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
