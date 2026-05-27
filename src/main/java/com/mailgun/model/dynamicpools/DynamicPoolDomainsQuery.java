package com.mailgun.model.dynamicpools;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Query parameters for {@code GET /v1/dynamic_pools/domains}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/get-v1-dynamic-pools-domains">List domains</a>
 */
@Value
@Jacksonized
@Builder
public class DynamicPoolDomainsQuery {

    Integer limit;

    /**
     * Filter by account id (may be specified multiple times).
     */
    List<String> account;

    /**
     * Filter by pool name (may be specified multiple times).
     */
    List<String> pool;

    /**
     * Sort field: {@code bounce_rate}, {@code complaint_rate}, or {@code name}.
     */
    String sort_by;

    /**
     * Sort order: {@code ascending} or {@code descending}.
     */
    String sort_order;

}
