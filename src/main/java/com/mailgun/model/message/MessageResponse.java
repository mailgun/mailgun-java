package com.mailgun.model.message;

import com.mailgun.model.ResponseWithMessage;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;


/**
 * Response body for successful sends: {@code POST /v3/{domain_name}/messages} and {@code POST /v3/{domain_name}/messages.mime}
 * (HTTP 200, {@code application/json}).
 * <p>
 * Error responses (HTTP 400, 429, 500) use the same JSON shape {@code {"message":"..."}}; decode the body with
 * {@link ResponseWithMessage} when handling {@link feign.FeignException} or a raw {@link feign.Response}.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/post-v3--domain-name--messages">Send an email</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/post-v3--domain-name--messages-mime">Send an email in MIME format</a>
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
