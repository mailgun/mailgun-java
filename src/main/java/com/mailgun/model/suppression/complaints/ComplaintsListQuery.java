package com.mailgun.model.suppression.complaints;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code GET /v3/{domain}/complaints}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/complaints/get-v3--domainid--complaints.md">List all complaints</a>
 */
@Value
@Jacksonized
@Builder
public class ComplaintsListQuery {

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
