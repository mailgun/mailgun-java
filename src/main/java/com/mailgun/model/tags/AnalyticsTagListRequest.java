package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * JSON request body for listing or searching account-level tags (POST /v1/analytics/tags).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags-new/post-v1-analytics-tags.md">Post query to list account tags</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalyticsTagListRequest {

    /**
     * Pagination options. When {@code includeTotal=true} the response includes total item count.
     */
    AnalyticsTagListPagination pagination;

    /**
     * Include data from all subaccounts. Default false.
     */
    @JsonProperty("include_subaccounts")
    Boolean includeSubaccounts;

    /**
     * Include per-tag metrics in each item. Default false. When true, max limit is 20.
     */
    @JsonProperty("include_metrics")
    Boolean includeMetrics;

    /**
     * Exact tag name or tag prefix to filter by.
     */
    String tag;

}
