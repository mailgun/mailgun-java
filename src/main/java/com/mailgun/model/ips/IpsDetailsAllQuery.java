package com.mailgun.model.ips;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code GET /v3/ips/details/all}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/get-v3-ips-details-all">List account IPs (detailed view)</a>
 */
@Value
@Jacksonized
@Builder
public class IpsDetailsAllQuery {

    Integer limit;

    Integer skip;

    /**
     * Pool id filter, or {@code any} / {@code none}.
     */
    String pool_id;

    /**
     * Domain id filter, or {@code any} / {@code none}.
     */
    String domain_id;

    /**
     * Subaccount id filter, or {@code any} / {@code none}.
     */
    String subaccount_id;

    /**
     * Partial IP address search.
     */
    String ip;

    String sort_by;

    /**
     * {@code descending} or {@code ascending}.
     */
    String sort_order;

}
