package com.mailgun.api.v1;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.logs.LogsRequest;
import com.mailgun.model.logs.LogsResponse;

import feign.Headers;
import feign.RequestLine;

/**
 * <p>
 * This API endpoint is logs service.
 * </p>
 * <p>
 * Mailgun keeps track of every inbound and outbound message event and stores this log data.
 * This data can be queried and filtered to provide insights into the health of your email infrastructure.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/logs">Work with Logs</a>
 */
@Headers({"Accept: application/json", "Content-Type: application/json"})
public interface MailgunLogsApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_1;
    }

    /**
     * <p>
     * Gets filtered logs for an account.
     * </p>
     * <p>
     * Returns a collection of logs and aggregated statistics about your email activities.
     * The response contains a paginated collection of individual records and a metrics object
     * with aggregated statistics for events specified in the request.
     * </p>
     *
     * @param logsRequest {@link LogsRequest} Request parameters to filter the logs
     * @return {@link LogsResponse} Response containing logs and aggregated statistics
     */
    @RequestLine("POST /analytics/logs")
    LogsResponse getLogs(LogsRequest logsRequest);

}
