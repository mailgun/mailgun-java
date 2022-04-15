package com.mailgun.model.mailing.lists;

import com.mailgun.enums.YesNo;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * <p>
 * Mailing list new member request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-mailinglists.html#mailing-lists">Mailing Lists</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class MailingListNewMemberRequest {

    /**
     * <p>
     * Valid email address specification, e.g. {@code "Alice <alice@example.com>"} or just {@code "alice@example.com" }.
     * </p>
     */
    String address;

    /**
     * <p>
     * Optional member name.
     * </p>
     */
    String name;

    /**
     * <p>
     * A map with arbitrary keys/values that turns into a JSON dictionary, e.g. {@code {"sex":"female","age":27} }.
     * </p>
     */
    Map<String, Object> vars;

    /**
     * <p>
     * <code>yes</code> to add as subscribed (default).
     * <p>
     * </p>
     * <code>no</code> as unsubscribed.
     * </p>
     */
    String subscribed;

    /**
     * <p>
     * <code>yes</code> to update member if present.
     * </p>
     * <p>
     * <code>no</code> to raise error in case of a duplicate member (default).
     * </p>
     */
    String upsert;

    public static class MailingListNewMemberRequestBuilder {

        /**
         * <p>
         * <code>true</code> to add as subscribed (default).
         * </p>
         * <p>
         * <code>false</code> as unsubscribed.
         * </p>
         *
         * @param subscribed <code>true</code> to lists subscribed, <code>false</code> for unsubscribed
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MailingListNewMemberRequest.MailingListNewMemberRequestBuilder subscribed(boolean subscribed) {
            this.subscribed = YesNo.getValue(subscribed);
            return this;
        }

        /**
         * <p>
         * <code>true</code> to update member if present.
         * </p>
         * <p>
         * <code>false</code> to raise error in case of a duplicate member (default).
         * </p>
         *
         * @param upsert <code>true</code> to update member if present, <code>false</code> to raise error in case of a duplicate member.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MailingListNewMemberRequest.MailingListNewMemberRequestBuilder upsert(boolean upsert) {
            this.upsert = YesNo.getValue(upsert);
            return this;
        }
    }

}
