package com.mailgun.model.mailing.lists;

import com.mailgun.enums.YesNo;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code GET /v3/lists/{list_address}/members} (non-page cursor API).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/mailing-lists/get-lists-string:list_address-members.md">Get mailing list members</a>
 */
@Value
@Jacksonized
@Builder
public class MailingListMembersIndexQuery {

    /**
     * Filter by member address specification.
     */
    String address;

    /**
     * {@code yes} / {@code no} when filtering by subscription; omit to list all.
     */
    String subscribed;

    /**
     * Maximum members to return (default 100, max 100).
     */
    Integer limit;

    /**
     * Number of members to skip.
     */
    Integer skip;

    public static class MailingListMembersIndexQueryBuilder {

        /**
         * @param subscribed {@code true} for subscribed only, {@code false} for unsubscribed only
         */
        public MailingListMembersIndexQueryBuilder subscribed(boolean subscribed) {
            this.subscribed = YesNo.getValue(subscribed);
            return this;
        }
    }

}
