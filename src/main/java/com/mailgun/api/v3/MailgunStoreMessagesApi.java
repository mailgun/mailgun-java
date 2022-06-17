package com.mailgun.api.v3;

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
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * <p>
     * Resend email.
     * </p>
     *
     * @param to Email address of the recipient
     * @return {@link MessageResponse}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST ")
    MessageResponse resendMessage(@Param("to") String to);

    /**
     * <p>
     * Resend message.
     * </p>
     *
     * @param to Email address of the recipient
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST ")
    Response resendMessageFeignResponse(@Param("to") String to);

    /**
     * <p>
     * Retrieve email(s).
     * </p>
     *
     * @return {@link StoreMessageResponse}
     */
    @RequestLine("GET ")
    StoreMessageResponse retrieveMessage();

    /**
     * <p>
     * Retrieve email(s).
     *</p>
     *
     * @return {@link Response}
     */
    @RequestLine("GET ")
    Response retrieveMessageFeignResponse();

}