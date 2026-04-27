package com.mailgun.model.suppression.whitelists;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code GET /v3/{domain}/whitelists} (allowlist pagination).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/allowlist/get-v3--domainid--whitelists.md">List allowlist records</a>
 */
@Value
@Jacksonized
@Builder
public class WhitelistsListQuery {

    /**
     * Maximum records to return (default 100, max 1000).
     */
    Integer limit;

    /**
     * Page direction relative to {@link #address}: {@code next}, {@code previous}, or {@code last}; empty returns the first page.
     */
    String page;

    /**
     * Address used as a pagination cursor between pages.
     */
    String address;

    /**
     * Return only values whose prefix matches this substring.
     */
    String term;

}
