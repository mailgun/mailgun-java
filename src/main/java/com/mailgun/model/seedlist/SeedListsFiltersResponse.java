package com.mailgun.model.seedlist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Seed Lists Attributes Response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-inbox-placement.html">Seed Lists</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeedListsFiltersResponse {

    /**
     * <p>
     * {@link SeedListsFilters}.
     * </p>
     */
    @JsonProperty("supported_filters")
    SeedListsFilters supportedFilters;

}
