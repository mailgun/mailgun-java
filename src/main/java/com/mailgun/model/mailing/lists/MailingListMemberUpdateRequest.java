package com.mailgun.model.mailing.lists;

import com.mailgun.enums.YesNo;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * <p>
 * Mailing list member update request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-mailinglists.html#mailing-lists">Mailing Lists</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class MailingListMemberUpdateRequest {

    /**
     * <p>
     * Valid email address specification, e.g. {@code "Alice <alice@example.com>"} or just {@code "alice@example.com" }.
     * </p>
     */
    String address;

    /**
     * <p>
     * Recipient name, e.g. <code>Alice</code>.
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
     * <code>no</code> to set unsubscribed.
     * <p>
     * </p>
     * <code>yes</code> as subscribed.
     * </p>
     */
    String subscribed;

    public static class MailingListMemberUpdateRequestBuilder {

        /**
         * <p>
         * <code>false</code> to set unsubscribed.
         * </p>
         * <p>
         * <code>true</code> as subscribed.
         * </p>
         *
         * @param subscribed <code>true</code> to lists subscribed, <code>false</code> for unsubscribed
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MailingListMemberUpdateRequest.MailingListMemberUpdateRequestBuilder subscribed(boolean subscribed) {
            this.subscribed = YesNo.getValue(subscribed);
            return this;
        }
    }

}
