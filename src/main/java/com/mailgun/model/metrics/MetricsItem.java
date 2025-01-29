package com.mailgun.model.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Metric items.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#metrics">Metrics</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetricsItem<T> {

    /**
     * <p>
     * Metrics dimensions.
     * </p>
     * {@link Dimension}
     */
    List<Dimension> dimensions;

    /**
     * <p>
     * Metrics data.
     * </p>
     * {@link AccountMetrics}
     * {@link AccountUsageMetrics}
     */
    T metrics;
}
