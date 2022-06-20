package com.mailgun.model.seedlist;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mailgun.model.Paging;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Seed Lists Response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-inbox-placement.html">Seed Lists</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeedListsResponse {

    /**
     * <p>
     * A list of {@link SeedListItem}.
     * </p>
     */
    List<SeedListItem> items;

    /**
     * <p>
     * Provides pagination urls.
     * </p>
     * {@link Paging}
     */
    Paging paging;

    /**
     * <p>
     * The total number of seed lists.
     * </p>
     */
    Integer total;

}
