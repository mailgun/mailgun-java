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
 * <p>
 * This API allows you to work with stored messages.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-sending.html">Stored Messages</a>
 */
@Headers("Accept: application/json")
public interface MailgunStoreMessagesApi extends MailgunApi {

    @Override
    default ApiVersion getApiVersion() {
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
     * <p>
     * Retrieve email(s).
     * </p>
     *
     * @return {@link StoreMessageResponse}
     */
    @RequestLine("GET")
    StoreMessageResponse retrieveMessage();

    /**
     * <p>
     * Retrieve email(s).
     *</p>
     *
     * @return {@link Response}
     */
    @RequestLine("GET")
    Response retrieveMessageFeignResponse();

}