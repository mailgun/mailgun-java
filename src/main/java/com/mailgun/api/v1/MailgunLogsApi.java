package com.mailgun.api.v1;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.logs.LogsRequest;
import com.mailgun.model.logs.LogsResponse;
import feign.Headers;
import feign.RequestLine;

/**
 * <p>
 * This API endpoint allows you to retrieve filtered, paginated logs for your Mailgun account.
 * </p>
 * <p>
 * See <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/openapi-final/logs/post-v1-analytics-logs">Mailgun Logs API</a>
 * </p>
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
     *
     * @param logsRequest {@link LogsRequest}
     * @return {@link LogsResponse}
     */
    @RequestLine("POST /analytics/logs")
    LogsResponse getLogs(LogsRequest logsRequest);
} 