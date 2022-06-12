package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.message.MailgunMimeMessage;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

/**
 * <p>
 * This API allows you to send emails.
 * </p>
 * <p>
 * When you submit messages for delivery Mailgun places them in a message queue.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-sending.html">Send emails</a>
 */
public interface MailgunMessagesApi extends MailgunApi {

    /**
     * Send email(s)
     *
     * @param domain  Name of the domain
     * @param message {@link Message}
     * @return {@link MessageResponse}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("POST /{domain}/messages")
    MessageResponse sendMessage(@Param("domain") String domain, Message message);

    /**
     * Send email(s)
     *
     * @param domain  Name of the domain
     * @param message {@link Message}
     * @return {@link Response}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("POST /{domain}/messages")
    Response sendMessageFeignResponse(@Param("domain") String domain, Message message);

    /**
     * Send email(s) in MIME format
     *
     * @param domain  Name of the domain
     * @param message {@link MailgunMimeMessage}
     * @return {@link MessageResponse}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("POST /{domain}/messages.mime")
    MessageResponse sendMIMEMessage(@Param("domain") String domain, MailgunMimeMessage message);

    /**
     * Send email(s) in MIME format
     *
     * @param domain  Name of the domain
     * @param message {@link MailgunMimeMessage}
     * @return {@link Response}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("POST /{domain}/messages.mime")
    Response sendMIMEMessageFeignResponse(@Param("domain") String domain, MailgunMimeMessage message);

}
