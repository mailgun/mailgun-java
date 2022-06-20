package com.mailgun.model.seedlist;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mailgun.model.Paging;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Seed Lists Attributes.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-inbox-placement.html#">Seed Lists</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeedListsAttributes {

    /**
     * <p>
     * A string describing what was selected.
     * </p>
     * {@link Paging}
     */
    String attribute;

    /**
     * <p>
     * A list of attributes that can be retrieved from the API.
     * </p>
     */
    List<String> values;

}
