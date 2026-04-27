package com.mailgun.api.v1;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.limits.AccountLimitThreshold;
import com.mailgun.model.limits.AccountLimitThresholdRequest;
import com.mailgun.model.limits.AccountLimitsListResult;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * Account limit thresholds API (v1): pre-send usage caps (e.g. email previews, seed tests) on subaccounts.
 * <p>
 * Distinct from {@link MailgunSendAlertsApi} (send <em>delivery</em> metric alerts at {@code /v1/thresholds/alerts/send}).
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/limits/get-v1-thresholds-limits.md">List limit thresholds</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/limits/post-v1-thresholds-limits.md">Create a limit threshold</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/limits/get-v1-thresholds-limits--name-.md">Get a limit threshold</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/limits/put-v1-thresholds-limits--name-.md">Update a limit threshold</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/limits/delete-v1-thresholds-limits--name-.md">Delete a limit threshold</a>
 */
@Headers({"Accept: application/json", "Content-Type: application/json"})
public interface MailgunLimitsApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_1;
    }

    @RequestLine("GET /thresholds/limits")
    AccountLimitsListResult listLimitThresholds();

    @RequestLine("POST /thresholds/limits")
    AccountLimitThreshold createLimitThreshold(AccountLimitThresholdRequest request);

    @RequestLine("GET /thresholds/limits/{name}")
    AccountLimitThreshold getLimitThreshold(@Param("name") String name);

    @RequestLine("PUT /thresholds/limits/{name}")
    ResponseWithMessage updateLimitThreshold(@Param("name") String name, AccountLimitThresholdRequest request);

    @RequestLine("DELETE /thresholds/limits/{name}")
    ResponseWithMessage deleteLimitThreshold(@Param("name") String name);

}
