package com.mailgun.api.v3;

import java.util.concurrent.CompletableFuture;

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
@Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
public interface MailgunMessagesApi extends MailgunApi {

    /**
     * <p>
     * Send email(s).
     * </p>
     *
     * @param domain  Name of the domain
     * @param message {@link Message}
     * @return {@link MessageResponse}
     */
    @RequestLine("POST /{domain}/messages")
    MessageResponse sendMessage(@Param("domain") String domain, Message message);

    /**
     * <p>
     * Send email(s).
     * </p>
     *
     * @param domain  Name of the domain
     * @param message {@link Message}
     * @return {@link Response}
     */
    @RequestLine("POST /{domain}/messages")
    Response sendMessageFeignResponse(@Param("domain") String domain, Message message);

    /**
     * <p>
     * Asynchronously send email(s).
     * </p>
     * <p>
     * Note: Use the asynchronous Mailgun client for this method.
     * <pre>
     * <code>MailgunClient.config(PRIVATE_API_KEY)
     *             .createAsyncApi(MailgunMessagesApi.class);</code>
     * </pre>
     *
     * @param domain  Name of the domain
     * @param message {@link Message}
     * @return {@link CompletableFuture<MessageResponse>}
     */
    @RequestLine("POST /{domain}/messages")
    CompletableFuture<MessageResponse> sendMessageAsync(@Param("domain") String domain, Message message);

    /**
     * <p>
     * Asynchronously send email(s).
     * </p>
     * <p>
     * Note: Use the asynchronous Mailgun client for this method.
     * <pre>
     * <code>MailgunClient.config(PRIVATE_API_KEY)
     *             .createAsyncApi(MailgunMessagesApi.class);</code>
     * </pre>
     *
     * @param domain  Name of the domain
     * @param message {@link Message}
     * @return {@link CompletableFuture<Response>}
     */
    @RequestLine("POST /{domain}/messages")
    CompletableFuture<Response> sendMessageFeignResponseAsync(@Param("domain") String domain, Message message);

    /**
     * <p>
     * Send email(s) in MIME format.
     * </p>
     *
     * @param domain  Name of the domain
     * @param message {@link MailgunMimeMessage}
     * @return {@link MessageResponse}
     */
    @RequestLine("POST /{domain}/messages.mime")
    MessageResponse sendMIMEMessage(@Param("domain") String domain, MailgunMimeMessage message);

    /**
     * <p>
     * Send email(s) in MIME format.
     * </p>
     *
     * @param domain  Name of the domain
     * @param message {@link MailgunMimeMessage}
     * @return {@link Response}
     */
    @RequestLine("POST /{domain}/messages.mime")
    Response sendMIMEMessageFeignResponse(@Param("domain") String domain, MailgunMimeMessage message);

    /**
     * <p>
     * Asynchronously send email(s) in MIME format.
     * </p>
     * <p>
     * Note: Use the asynchronous Mailgun client for this method.
     * <pre>
     * <code>MailgunClient.config(PRIVATE_API_KEY)
     *             .createAsyncApi(MailgunMessagesApi.class);</code>
     * </pre>
     *
     * @param domain  Name of the domain
     * @param message {@link MailgunMimeMessage}
     * @return {@link CompletableFuture<MessageResponse>}
     */
    @RequestLine("POST /{domain}/messages.mime")
    CompletableFuture<MessageResponse> sendMIMEMessageAsync(@Param("domain") String domain, MailgunMimeMessage message);

    /**
     * <p>
     * Asynchronously send email(s) in MIME format.
     * </p>
     * <p>
     * Note: Use the asynchronous Mailgun client for this method.
     * <pre>
     * <code>MailgunClient.config(PRIVATE_API_KEY)
     *             .createAsyncApi(MailgunMessagesApi.class);</code>
     * </pre>
     *
     * @param domain  Name of the domain
     * @param message {@link MailgunMimeMessage}
     * @return {@link CompletableFuture<Response>}
     */
    @RequestLine("POST /{domain}/messages.mime")
    CompletableFuture<Response> sendMIMEMessageFeignResponseAsync(@Param("domain") String domain, MailgunMimeMessage message);

}
