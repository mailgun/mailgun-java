package com.mailgun.model.bounceclassification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mailgun.model.Pagination;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Response from the bounce classification metrics endpoint (POST /v2/bounce-classification/metrics).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/bounce-classification/post-v2-bounce-classification-metrics.md">List statistic v2</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class BounceClassificationResponse {

    /**
     * Start timestamp of the result set in RFC 2822 format.
     */
    String start;

    /**
     * End timestamp of the result set in RFC 2822 format.
     */
    String end;

    /**
     * Resolution echoed from the request.
     */
    String resolution;

    /**
     * Duration echoed from the request.
     */
    String duration;

    /**
     * Dimensions echoed from the request.
     */
    List<String> dimensions;

    /**
     * Pagination metadata (sort, skip, limit, total).
     */
    Pagination pagination;

    /**
     * Result rows. Items with no bounces and no delays are not included.
     */
    List<BounceClassificationItem> items;

}
