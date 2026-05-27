package com.mailgun.model.dynamicpools;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code GET /v1/dynamic_pools/history}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/get-v1-dynamic-pools-history">List account history</a>
 */
@Value
@Jacksonized
@Builder
public class DynamicPoolHistoryQuery {

    /**
     * Maximum number of events to return. Query param name is {@code Limit} (capital L).
     */
    Integer Limit;

    Boolean include_subaccounts;

    String domain;

    String before;

    String after;

    String moved_to;

    String moved_from;

}
