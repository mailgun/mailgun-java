package com.mailgun.model.suppression.bounces;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code GET /v3/{domain}/bounces}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/bounces/get-v3--domainid--bounces.md">List all bounces</a>
 */
@Value
@Jacksonized
@Builder
public class BouncesListQuery {

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
     * Return only addresses whose prefix matches this substring.
     */
    String term;

}
