package com.mailgun.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Filter.
 * </p>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class Filter {

    /**
     * <p>
     * Array of conditions.
     * </p>
     * {@link FilterItem}
     */
    @JsonProperty("AND")
    List<FilterItem> and;
}
