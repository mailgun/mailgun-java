package com.mailgun.api.v3;

import java.util.concurrent.CompletableFuture;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.message.MailgunMimeMessage;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import com.mailgun.model.message.SendingQueuesResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

/**
 * <p>
 * Messages API: send emails, retrieve/resend stored messages, manage queue status.
 * </p>
 * <p>
 * Send endpoints require one of: text, html, amp-html, or template. Send options (o:, h:, v:, t:) are limited to 16KB total.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/post-v3--domain-name--messages">Send an email</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/post-v3--domain-name--messages-mime">Send an email in MIME format</a>
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
     * @return {@link CompletableFuture} wrapped {@link MessageResponse}
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
     * @return {@link CompletableFuture} wrapped {@link Response}
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
     * @return {@link CompletableFuture} wrapped {@link MessageResponse}
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
     * @return {@link CompletableFuture} wrapped {@link Response}
     */
    @RequestLine("POST /{domain}/messages.mime")
    CompletableFuture<Response> sendMIMEMessageFeignResponseAsync(@Param("domain") String domain, MailgunMimeMessage message);

    /**
     * <p>
     * Get default and scheduled message queue status for the domain.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link SendingQueuesResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/get-v3-domains--name--sending-queues">Get messages queue status</a>
     */
    @RequestLine("GET /domains/{domain}/sending_queues")
    SendingQueuesResponse getSendingQueues(@Param("domain") String domain);

    /**
     * <p>
     * Get default and scheduled message queue status for the domain.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link Response}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/get-v3-domains--name--sending-queues">Get messages queue status</a>
     */
    @RequestLine("GET /domains/{domain}/sending_queues")
    Response getSendingQueuesFeignResponse(@Param("domain") String domain);

    /**
     * <p>
     * Delete all scheduled and undelivered mail from the domain queue. Must be called on the same storage API host as the mail's storage URL (e.g. https://storage-us-east4.api.mailgun.net).
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/delete-v3--domain-name--envelopes">Delete scheduled and undelivered mail</a>
     */
    @RequestLine("DELETE /{domain}/envelopes")
    ResponseWithMessage deleteEnvelopes(@Param("domain") String domain);

    /**
     * <p>
     * Delete all scheduled and undelivered mail from the domain queue. Must be called on the same storage API host as the mail's storage URL.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link Response}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/delete-v3--domain-name--envelopes">Delete scheduled and undelivered mail</a>
     */
    @RequestLine("DELETE /{domain}/envelopes")
    Response deleteEnvelopesFeignResponse(@Param("domain") String domain);

}
