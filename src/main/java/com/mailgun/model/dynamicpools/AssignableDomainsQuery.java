package com.mailgun.model.dynamicpools;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code GET /v3/domains/dynamic_pools/assignable}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/get-v3-domains-dynamic-pools-assignable">List assignable domains</a>
 */
@Value
@Jacksonized
@Builder
public class AssignableDomainsQuery {

    /**
     * Subaccount id whose domains are queried.
     */
    String subaccount_id;

    /**
     * Regex search term to filter by domain name.
     */
    String domain;

}
