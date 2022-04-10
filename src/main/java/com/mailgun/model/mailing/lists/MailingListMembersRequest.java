package com.mailgun.model.mailing.lists;

import com.mailgun.enums.YesNo;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Mailing list members request.
 * </p
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-mailinglists.html#mailing-lists">Mailing Lists</a>
 */
@Value
@Jacksonized
@Builder
public class MailingListMembersRequest {

    /**
     * <p>
     * Maximum number of records to return (100 by default).
     * </p>
     */
    Integer limit;

    /**
     * <p>
     * <code>yes</code> to lists subscribed.
     * </p>
     * <p>
     * <code>no</code> for unsubscribed.
     * </p>
     * <p>
     * List all if not set.
     * </p>
     */
    String subscribed;

    public static class MailingListMembersRequestBuilder {

        /**
         * <p>
         * <code>true</code> to lists subscribed.
         * </p>
         * <p>
         * <code>false</code> for unsubscribed.
         * </p>
         *
         * @param subscribed <code>true</code> to lists subscribed, <code>false</code> for unsubscribed
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MailingListMembersRequest.MailingListMembersRequestBuilder subscribed(boolean subscribed) {
            this.subscribed = YesNo.getValue(subscribed);
            return this;
        }
    }

}
