package com.mailgun.model.dynamicpools;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Paginated history for {@code GET /v1/dynamic_pools/history}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/get-v1-dynamic-pools-history">List account history</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamicPoolHistoryListResponse {

    List<DynamicPoolHistoryRecord> items;

    @JsonProperty("total_items")
    Integer totalItems;

    DynamicPoolPaging paging;

}
