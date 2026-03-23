package com.mailgun.model.bounceclassification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mailgun.model.metrics.Dimension;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.Map;

/**
 * A single result row in the bounce classification metrics response.
 * The {@code metrics} map keys correspond to the metric names requested
 * (e.g. "classified_failures_count").
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/bounce-classification/post-v2-bounce-classification-metrics.md">List statistic v2</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class BounceClassificationItem {

    /**
     * Dimension values for this row (e.g. entity name, domain, group ID).
     */
    List<Dimension> dimensions;

    /**
     * Metric name → value pairs for this row.
     */
    Map<String, Object> metrics;

}
