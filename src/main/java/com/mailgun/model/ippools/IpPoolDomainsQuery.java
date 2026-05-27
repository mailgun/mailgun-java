package com.mailgun.model.ippools;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code GET /v3/ip_pools/{pool_id}/domains}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/get-v3-ip-pools--pool-id--domains">Get domains linked to DIPP</a>
 */
@Value
@Jacksonized
@Builder
public class IpPoolDomainsQuery {

    /**
     * Maximum number of records to return (10–500). Default: 10.
     */
    Integer limit;

    /**
     * Encoded page identifier from a previous response.
     */
    String page;

}
