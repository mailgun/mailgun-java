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
 * Update mailgun lists request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-mailinglists.html#mailing-lists">Mailing Lists</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class UpdateMailingListRequest {

    /**
     * <p>
     * New mailing list address, e.g. <code>devs@mg.net</code> (optional).
     * </p>
     */
    String address;

    /**
     * <p>
     * New name, e.g. <code>My newsletter</code> (optional).
     * </p>
     */
    String name;

    /**
     * <p>
     * A description (optional)
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

    public static class UpdateMailingListRequestBuilder {

        /**
         * <p>
         * List access level, one of: <code>READONLY</code> (default), <code>MEMBERS</code>, <code>EVERYONE</code>.
         * </p>
         *
         * @param accessLevel {@link AccessLevel}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public UpdateMailingListRequest.UpdateMailingListRequestBuilder accessLevel(AccessLevel accessLevel) {
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
        public UpdateMailingListRequest.UpdateMailingListRequestBuilder replyPreference(ReplyPreference replyPreference) {
            this.replyPreference = replyPreference.getValue();
            return this;
        }
    }

}
