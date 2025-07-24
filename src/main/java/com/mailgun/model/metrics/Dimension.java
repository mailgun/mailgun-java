package com.mailgun.model.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Metrics dimension.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#metrics">Metrics</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dimension {

    /**
     * <p>
     * The dimension.
     * </p>
     */
    String dimension;

    /**
     * <p>
     * The dimension value.
     * </p>
     */
    String value;

    /**
     * <p>
     * The dimension value in displayable form.
     * </p>
     */
    @JsonProperty("display_value")
    String displayValue;
}
