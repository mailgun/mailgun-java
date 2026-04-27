package com.mailgun.api.v3;

import java.util.concurrent.CompletableFuture;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.message.MessageResponse;
import com.mailgun.model.message.StoreMessageResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

/**
 * Client for the <strong>absolute</strong> per-message storage URL from {@code storage.url} on events (often a regional storage host).
 * For {@code GET /v3/domains/{domain_name}/messages/{storage_key}} on the main API host, use
 * {@link com.mailgun.api.v3.MailgunMessagesApi#getStoredMessage(String, String)} with {@code storage.key}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/get-v3-domains--domain-name--messages--storage-key">Retrieve a stored email</a>
 */
@Headers("Accept: application/json")
public interface MailgunStoreMessagesApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        throw new UnsupportedOperationException("Please use 'MailgunClient.config(storedMessageUrl, PRIVATE_API_KEY)"
            + ".createApiWithAbsoluteUrl(MailgunStoreMessagesApi.class)' configuration for this method");
    }

    /**
     * <p>
     * Resend email.
     * </p>
     *
     * @param recipientEmail Email address of the recipient
     * @return {@link MessageResponse}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST")
    MessageResponse resendMessage(@Param("to") String recipientEmail);

    /**
     * <p>
     * Resend message.
     * </p>
     *
     * @param recipientEmail Email address of the recipient
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST")
    Response resendMessageFeignResponse(@Param("to") String recipientEmail);

    /**
     * <p>
     * Asynchronously resend email.
     * </p>
     * <p>
     * Note: Use the asynchronous Mailgun client for this method.
     * <pre>
     * <code>MailgunClient.config(PRIVATE_API_KEY)
     *             .createAsyncApi(MailgunStoreMessagesApi.class);</code>
     * </pre>
     *
     * @param recipientEmail Email address of the recipient
     * @return {@link MessageResponse}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST")
    CompletableFuture<MessageResponse> resendMessageAsync(@Param("to") String recipientEmail);

    /**
     * <p>
     * Asynchronously resend message.
     * </p>
     * <p>
     * Note: Use the asynchronous Mailgun client for this method.
     * <pre>
     * <code>MailgunClient.config(PRIVATE_API_KEY)
     *             .createAsyncApi(MailgunStoreMessagesApi.class);</code>
     * </pre>
     *
     * @param recipientEmail Email address of the recipient
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST")
    CompletableFuture<Response> resendMessageFeignResponseAsync(@Param("to") String recipientEmail);

    /**
     * Retrieves the stored message from this client’s absolute storage URL; response JSON matches {@link StoreMessageResponse}
     * (same fields as {@link com.mailgun.api.v3.MailgunMessagesApi#getStoredMessage(String, String)} on the primary API).
     *
     * @return {@link StoreMessageResponse}
     */
    @RequestLine("GET")
    StoreMessageResponse retrieveMessage();

    /**
     * Same as {@link #retrieveMessage()} returning the raw Feign {@link Response}.
     */
    @RequestLine("GET")
    Response retrieveMessageFeignResponse();

}