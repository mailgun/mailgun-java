package com.mailgun.api.v5;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.custommessagelimit.CustomMessageLimitResponse;
import com.mailgun.model.custommessagelimit.CustomMessageLimitSuccessResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

import java.math.BigDecimal;

/**
 * Custom Message Limit API. Manages the hard monthly sending limit of an account.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/custom-message-limit">Custom Message Limit</a>
 */
@Headers("Accept: application/json")
public interface MailgunCustomMessageLimitApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_5;
    }

    /**
     * Returns the current custom monthly sending limit and usage.
     *
     * @return {@link CustomMessageLimitResponse}
     */
    @RequestLine("GET /accounts/limit/custom/monthly")
    CustomMessageLimitResponse getCustomMessageLimit();

    @RequestLine("GET /accounts/limit/custom/monthly")
    Response getCustomMessageLimitFeignResponse();

    /**
     * Sets the custom monthly sending limit.
     *
     * @param limit limit to set
     * @return {@link CustomMessageLimitSuccessResponse}
     */
    @RequestLine("PUT /accounts/limit/custom/monthly?limit={limit}")
    CustomMessageLimitSuccessResponse setCustomMessageLimit(@Param("limit") BigDecimal limit);

    @RequestLine("PUT /accounts/limit/custom/monthly?limit={limit}")
    Response setCustomMessageLimitFeignResponse(@Param("limit") BigDecimal limit);

    /**
     * Deletes the custom monthly sending limit.
     *
     * @return {@link CustomMessageLimitSuccessResponse}
     */
    @RequestLine("DELETE /accounts/limit/custom/monthly")
    CustomMessageLimitSuccessResponse deleteCustomMessageLimit();

    @RequestLine("DELETE /accounts/limit/custom/monthly")
    Response deleteCustomMessageLimitFeignResponse();

    /**
     * Re-enables an account disabled after reaching its custom sending limit.
     *
     * @return {@link CustomMessageLimitSuccessResponse}
     */
    @RequestLine("PUT /accounts/limit/custom/enable")
    CustomMessageLimitSuccessResponse enableAccount();

    @RequestLine("PUT /accounts/limit/custom/enable")
    Response enableAccountFeignResponse();

}
