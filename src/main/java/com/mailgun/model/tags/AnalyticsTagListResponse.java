package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Response body for the analytics tags list endpoint (POST /v1/analytics/tags).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags-new/post-v1-analytics-tags.md">Post query to list account tags</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalyticsTagListResponse {

    List<AnalyticsTagItem> items;

    AnalyticsTagListPagination pagination;

}
