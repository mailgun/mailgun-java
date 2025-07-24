package com.mailgun.api.v1;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.metrics.AccountMetrics;
import com.mailgun.model.metrics.MetricsRequest;
import com.mailgun.model.metrics.MetricsResponse;
import com.mailgun.model.metrics.AccountUsageMetrics;

import feign.Headers;
import feign.RequestLine;

/**
 * <p>
 * This API endpoint is metrics service.
 * </p>
 * <p>
 * Mailgun collects many different events and generates event metrics which are available in your Control Panel. This data is also available via our analytics metrics API endpoint.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/openapi-final/tag/Metrics/">Work with Metrics</a>
 */
@Headers({"Accept: application/json", "Content-Type: application/json"})
public interface MailgunAccountMetricsApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_1;
    }

    /**
     * <p>
     * Gets filtered metrics for an account
     * </p>
     *
     * @param metricsRequest {@link MetricsRequest}
     * @return {@link MetricsResponse}
     */
    @RequestLine("POST /analytics/metrics")
    MetricsResponse<AccountMetrics> getMetrics(MetricsRequest metricsRequest);

    /**
     * <p>
     * Gets filtered metrics for an account
     * </p>
     *
     * @param metricsRequest {@link MetricsRequest}
     * @return {@link MetricsResponse}
     */
    @RequestLine("GET /analytics/usage/metrics")
    MetricsResponse<AccountUsageMetrics> getUsageMetrics(MetricsRequest metricsRequest);
}
