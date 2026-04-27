package com.mailgun.api.v3;

import java.util.concurrent.CompletableFuture;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.message.MailgunMimeMessage;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import com.mailgun.model.message.SendingQueuesResponse;
import com.mailgun.model.message.StoreMessageResponse;
import feign.FeignException;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

/**
 * Messages API: send emails, retrieve/resend stored messages, manage queue status.
 * <p>
 * {@code POST /v3/{domain_name}/messages} (API version 3.0.0) uses {@code multipart/form-data} and HTTP Basic authentication.
 * You must provide at least one of {@code text}, {@code html}, {@code amp-html}, or {@code template}.
 * Send options (parameter names starting with {@code o:}, {@code h:}, {@code v:}, or {@code t:}) are limited to
 * {@value com.mailgun.util.Constants#MAILGUN_SEND_OPTIONS_MAX_BYTES} bytes in total.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/post-v3--domain-name--messages">Send an email</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/post-v3--domain-name--messages-mime">Send an email in MIME format</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/get-v3-domains--domain-name--messages--storage-key">Retrieve a stored email</a>
 */
@Headers("Accept: application/json")
public interface MailgunMessagesApi extends MailgunApi {

    /**
     * Retrieve a stored outbound message: {@code GET /v3/domains/{domain_name}/messages/{storage_key}}.
     * Use {@code storage.key} from the related sending event (for example accepted or delivered).
     *
     * @param domain      Domain that sent the message ({@code domain_name})
     * @param storageKey  Storage key from the event ({@code storage_key})
     * @return {@link StoreMessageResponse}
     * @throws FeignException on HTTP 400, 404, etc.; decode error JSON as {@link ResponseWithMessage}
     */
    @RequestLine("GET /domains/{domain}/messages/{storageKey}")
    StoreMessageResponse getStoredMessage(@Param("domain") String domain, @Param("storageKey") String storageKey);

    /**
     * Same as {@link #getStoredMessage(String, String)} returning the raw Feign {@link Response}.
     */
    @RequestLine("GET /domains/{domain}/messages/{storageKey}")
    Response getStoredMessageFeignResponse(@Param("domain") String domain, @Param("storageKey") String storageKey);

    /**
     * Send email(s): {@code POST /v3/{domain_name}/messages}, {@code multipart/form-data}.
     *
     * @param domain  Sending domain name ({@code domain_name})
     * @param message {@link Message}
     * @return {@link MessageResponse}
     * @throws FeignException on HTTP 400, 429, 500, etc.; response JSON is {@code {"message":"..."}} and can be decoded as {@link ResponseWithMessage}
     */
    @Headers("Content-Type: multipart/form-data")
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
    @Headers("Content-Type: multipart/form-data")
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
    @Headers("Content-Type: multipart/form-data")
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
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/messages")
    CompletableFuture<Response> sendMessageFeignResponseAsync(@Param("domain") String domain, Message message);

    /**
     * Send email(s) in MIME format: {@code POST /v3/{domain_name}/messages.mime}, {@code multipart/form-data}.
     * The MIME document is posted as the {@code message} form field (see {@link MailgunMimeMessage}).
     * Send options (names starting with {@code o:}, {@code h:}, {@code v:}, or {@code t:}) are limited to
     * {@value com.mailgun.util.Constants#MAILGUN_SEND_OPTIONS_MAX_BYTES} bytes in total.
     *
     * @param domain  Sending domain name ({@code domain_name})
     * @param message {@link MailgunMimeMessage}
     * @return {@link MessageResponse}
     * @throws FeignException on HTTP 400, 429, 500, etc.; response JSON is {@code {"message":"..."}} and can be decoded as {@link ResponseWithMessage}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/messages.mime")
    MessageResponse sendMIMEMessage(@Param("domain") String domain, MailgunMimeMessage message);

    /**
     * Same as {@link #sendMIMEMessage(String, MailgunMimeMessage)} returning the raw Feign {@link Response}.
     *
     * @param domain  Sending domain name ({@code domain_name})
     * @param message {@link MailgunMimeMessage}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/messages.mime")
    Response sendMIMEMessageFeignResponse(@Param("domain") String domain, MailgunMimeMessage message);

    /**
     * Asynchronously send MIME email(s). Use the asynchronous Mailgun client for this method.
     * <pre>
     * <code>MailgunClient.config(PRIVATE_API_KEY)
     *             .createAsyncApi(MailgunMessagesApi.class);</code>
     * </pre>
     *
     * @param domain  Sending domain name ({@code domain_name})
     * @param message {@link MailgunMimeMessage}
     * @return {@link CompletableFuture} wrapping {@link MessageResponse}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/messages.mime")
    CompletableFuture<MessageResponse> sendMIMEMessageAsync(@Param("domain") String domain, MailgunMimeMessage message);

    /**
     * Asynchronous variant of {@link #sendMIMEMessageFeignResponse(String, MailgunMimeMessage)}.
     *
     * @param domain  Sending domain name ({@code domain_name})
     * @param message {@link MailgunMimeMessage}
     * @return {@link CompletableFuture} wrapping {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
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
