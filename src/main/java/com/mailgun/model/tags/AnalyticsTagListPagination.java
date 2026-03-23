package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Pagination object used in both the analytics tags list request and response.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags-new/post-v1-analytics-tags.md">Post query to list account tags</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalyticsTagListPagination {

    /**
     * Colon-separated column name and sort direction e.g. 'lastseen:desc'.
     */
    String sort;

    /**
     * Number of items to skip. Set to zero for the first page, then increment by limit.
     */
    Integer skip;

    /**
     * Maximum number of items returned. Default 10, max 1000.
     */
    Integer limit;

    /**
     * Total number of tags matching the search criteria (populated in response).
     */
    Integer total;

    /**
     * Whether to include the total count in the response. Default false.
     */
    @JsonProperty("include_total")
    Boolean includeTotal;

}
