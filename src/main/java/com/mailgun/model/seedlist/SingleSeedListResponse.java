package com.mailgun.model.seedlist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Single Seed List Response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-inbox-placement.html">Seed Lists</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleSeedListResponse {

    /**
     * <p>
     * A list of {@link SeedListItem}.
     * </p>
     */
    @JsonProperty("seedlist")
    SeedListItem seedList;

}
