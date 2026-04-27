package com.mailgun.model.mailing.lists;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code GET /v3/lists}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/mailing-lists/get-v3-lists.md">Get mailing lists</a>
 */
@Value
@Jacksonized
@Builder
public class MailingListsQuery {

    /**
     * Maximum lists to return (default 100).
     */
    Integer limit;

    /**
     * Number of lists to skip from the start (default 0).
     */
    Integer skip;

    /**
     * Return only lists whose address matches this filter.
     */
    String address;

}
