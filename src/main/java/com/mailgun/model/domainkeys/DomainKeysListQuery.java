package com.mailgun.model.domainkeys;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for listing domain keys (GET /v1/dkim/keys). Results are paginated; limit default 10, max 100.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/get-v1-dkim-keys">List keys for all domains</a>
 */
@Value
@Jacksonized
@Builder
public class DomainKeysListQuery {

    /**
     * Encoded paging information from 'next' or 'previous' links in the paging response.
     */
    String page;

    /**
     * Number of items per page. Default: 10, max: 100.
     */
    Integer limit;

    /**
     * Filter by signing domain.
     */
    String signing_domain;

    /**
     * Filter by selector.
     */
    String selector;
}
