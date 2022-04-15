package com.mailgun.model.mailing.lists;

import com.mailgun.enums.YesNo;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * <p>
 * Request to add members to the mailing list.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-mailinglists.html#mailing-lists">Mailing Lists</a>
 */
@Jacksonized
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class AddMailingListMembersRequest {

    /**
     * <p>
     * JSON-encoded array. Elements can be either addresses, e.g. {@code ["bob@example.com", "alice@example.com"] }.
     * </p>
     * <p>
     * Or JSON objects, e.g. {@code [{"address": "bob@example.com", "name": "Bob", "subscribed": false}, {"address": "alice@example.com", "name": "Alice"}] }.
     * </p>
     * <p>
     * Custom variables can be provided.
     * </p>
     */
    List<MailingListMember> members;

    /**
     * <p>
     * <code>yes</code> to update existing members.
     * </p>
     * <p>
     * <code>no</code> (default) to ignore duplicates.
     * </p>
     */
    String upsert;

    public static class AddMailingListMembersRequestBuilder {

        /**
         * <p>
         * <code>true</code> to update existing members.
         * </p>
         * <p>
         * <code>false</code> (default) to ignore duplicates.
         * </p>
         *
         * @param updateExistingMembers update existing members or ignore duplicates
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public AddMailingListMembersRequest.AddMailingListMembersRequestBuilder upsert(boolean updateExistingMembers) {
            this.upsert = YesNo.getValue(updateExistingMembers);
            return this;
        }
    }

}
