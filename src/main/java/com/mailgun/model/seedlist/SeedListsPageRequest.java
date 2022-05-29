package com.mailgun.model.seedlist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Seed Lists Page Request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-inbox-placement.html">Seed Lists</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeedListsPageRequest {

    /**
     * <p>
     * A limit on the number of items that can be returned (defaults to 25).
     * </p>
     */
    Integer limit;

    /**
     * <p>
     * A the amount of items to “move” by.
     * </p>
     */
    Integer offset;

    /**
     * <p>
     * A flag that will set the order of the pagination
     * </p>
     */
    Boolean ascending;

    /**
     * <p>
     * A unique id that can be used as a “pivot” of where you are in the set.
     * </p>
     */
    String cursor;

    /**
     * <p>
     * The parameter to sort by (EXPERIMENTAL).
     * </p>
     */
    String sort;

}
