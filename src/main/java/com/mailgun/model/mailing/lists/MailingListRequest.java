package com.mailgun.model.mailing.lists;

import com.mailgun.enums.AccessLevel;
import com.mailgun.enums.ReplyPreference;
import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * Mailgun lists request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-mailinglists.html#mailing-lists">Mailing Lists</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class MailingListRequest {

    /**
     * <p>
     * A valid email address for the mailing list, e.g. <code>developers@mailgun.net</code>, or Developers <code>devs@mg.net</code>.
     * </p>
     */
    String address;

    /**
     * <p>
     * Mailing list name, e.g. <code>Developers</code> (optional).
     * </p>
     */
    String name;

    /**
     * <p>
     * A description (optional).
     * </p>
     */
    String description;

    /**
     * <p>
     * List access level, one of: <code>READONLY</code> (default), <code>MEMBERS</code>, <code>EVERYONE</code>.
     * </p>
     */
    @FormProperty("access_level")
    String accessLevel;

    /**
     * <p>
     * Set where replies should go: <code>list</code> (default) | <code>sender</code> (optional).
     * </p>
     */
    @FormProperty("reply_preference")
    String replyPreference;

    public static class MailingListRequestBuilder {

        /**
         * <p>
         * List access level, one of: <code>READONLY</code> (default), <code>MEMBERS</code>, <code>EVERYONE</code>.
         * </p>
         *
         * @param accessLevel {@link AccessLevel}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MailingListRequest.MailingListRequestBuilder accessLevel(AccessLevel accessLevel) {
            this.accessLevel = accessLevel.getValue();
            return this;
        }

        /**
         * <p>
         * Set where replies should go: <code>list</code> (default) | <code>sender</code> (optional).
         * </p>
         *
         * @param replyPreference {@link ReplyPreference}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MailingListRequest.MailingListRequestBuilder replyPreference(ReplyPreference replyPreference) {
            this.replyPreference = replyPreference.getValue();
            return this;
        }
    }

}
