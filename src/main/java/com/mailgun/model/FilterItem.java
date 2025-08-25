package com.mailgun.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Metric filter condition.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#metrics">Metrics</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterItem {

    /**
     * <p>
     * Condition attribute.
     * </p>
     */
    String attribute;

    /**
     * <p>
     * Condition comparator.
     * </p>
     * {@link FilterItem}
     */
    String comparator;

    /**
     * <p>
     * Condition values.
     * </p>
     * {@link FilterItemValue}
     */
    List<FilterItemValue> values;

    /**
     * <p>
     * Filter value.
     * </p>
     */
    @Value
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FilterItemValue {

        /**
         * <p>
         * Filter item value label.
         * </p>
         */
        String label;

        /**
         * <p>
         * Filter item value value.
         * </p>
         */
        String value;
    }
}
