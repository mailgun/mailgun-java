package com.mailgun.api.v1;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.sendalerts.SendAlert;
import com.mailgun.model.sendalerts.SendAlertRequest;
import com.mailgun.model.sendalerts.SendAlertsListResult;
import com.mailgun.model.sendalerts.ThresholdHitsListResult;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * Send Alerts API (v1): list, create, read, replace, and delete account send alerts; list threshold hits.
 * <p>
 * The update endpoint performs a full resource replacement; fetch the current alert with GET before PUT
 * if you need to change only part of the configuration.
 * </p>
 * <p>
 * For Mailgun Alerts <em>delivery</em> settings (email, webhook, Slack for events), use {@link MailgunAlertsApi}
 * ({@code /v1/alerts/...}), not this interface.
 * </p>
 * <p>
 * For pre-send <em>usage limits</em> on subaccounts ({@code /v1/thresholds/limits}), use {@link MailgunLimitsApi}.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/get-v1-thresholds-alerts-send.md">List send alerts</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/post-v1-thresholds-alerts-send.md">Create a send alert</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/get-v1-thresholds-alerts-send--name-.md">Get a send alert</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/put-v1-thresholds-alerts-send--name-.md">Update a send alert</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/delete-v1-thresholds-alerts-send--name-.md">Delete a send alert</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/get-v1-thresholds-hits.md">List account hits</a>
 */
@Headers({"Accept: application/json", "Content-Type: application/json"})
public interface MailgunSendAlertsApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_1;
    }

    /**
     * List send alerts for the account.
     *
     * @return {@link SendAlertsListResult}
     */
    @RequestLine("GET /thresholds/alerts/send")
    SendAlertsListResult listSendAlerts();

    /**
     * Create a send alert.
     *
     * @param request alert configuration
     * @return created alert
     */
    @RequestLine("POST /thresholds/alerts/send")
    SendAlert createSendAlert(SendAlertRequest request);

    /**
     * Get a send alert by name.
     *
     * @param name threshold name
     * @return {@link SendAlert}
     */
    @RequestLine("GET /thresholds/alerts/send/{name}")
    SendAlert getSendAlert(@Param("name") String name);

    /**
     * Replace a send alert (full PUT). Prefer GET-then-PUT when updating a subset of fields.
     *
     * @param name    threshold name
     * @param request full replacement body
     * @return status message
     */
    @RequestLine("PUT /thresholds/alerts/send/{name}")
    ResponseWithMessage updateSendAlert(@Param("name") String name, SendAlertRequest request);

    /**
     * Delete a send alert by name.
     *
     * @param name threshold name
     * @return status message
     */
    @RequestLine("DELETE /thresholds/alerts/send/{name}")
    ResponseWithMessage deleteSendAlert(@Param("name") String name);

    /**
     * List account threshold hits.
     *
     * @return {@link ThresholdHitsListResult}
     */
    @RequestLine("GET /thresholds/hits")
    ThresholdHitsListResult listThresholdHits();

}
