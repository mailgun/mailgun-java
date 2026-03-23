package com.mailgun.api.v2;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.bounceclassification.BounceClassificationRequest;
import com.mailgun.model.bounceclassification.BounceClassificationResponse;
import feign.Headers;
import feign.RequestLine;
import feign.Response;

/**
 * Bounce Classification Metrics API (v2): query bounce and delay statistics across entities,
 * domains, IP pools, tags, and more. Items with no bounces and no delays
 * ({@code classified_failures_count == 0}) are not returned.
 *
 * <p>All v1 bounce-classification endpoints are deprecated; use this API instead.</p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/bounce-classification/post-v2-bounce-classification-metrics.md">List statistic v2</a>
 */
@Headers({"Accept: application/json", "Content-Type: application/json"})
public interface MailgunBounceClassificationApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_2;
    }

    /**
     * Query bounce classification metrics. Supports filtering, grouping by dimensions,
     * and pagination. Items with {@code classified_failures_count == 0} are excluded.
     *
     * @param request {@link BounceClassificationRequest} with filters, dimensions, metrics, and pagination
     * @return {@link BounceClassificationResponse} containing paginated result rows
     */
    @RequestLine("POST /bounce-classification/metrics")
    BounceClassificationResponse getMetrics(BounceClassificationRequest request);

    /**
     * Query bounce classification metrics (raw response).
     *
     * @param request {@link BounceClassificationRequest} with filters, dimensions, metrics, and pagination
     * @return {@link Response}
     */
    @RequestLine("POST /bounce-classification/metrics")
    Response getMetricsFeignResponse(BounceClassificationRequest request);

}
