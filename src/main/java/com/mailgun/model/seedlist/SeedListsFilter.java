package com.mailgun.model.seedlist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Seed Lists Filter.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-inbox-placement.html">Seed Lists</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeedListsFilter {

    /**
     * <p>
     * The key of the parameter you can pass in your query string.
     * </p>
     */
    String parameter;

    /**
     * <p>
     * A description of the filter.
     * </p>
     */
    String description;

}
