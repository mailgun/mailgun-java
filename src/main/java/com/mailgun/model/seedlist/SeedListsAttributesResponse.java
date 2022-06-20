package com.mailgun.model.seedlist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class SeedListsAttributesResponse {

    /**
     * <p>
     * A list of {@link SeedListsAttributes}.
     * </p>
     */
    SeedListsAttributes items;

}
