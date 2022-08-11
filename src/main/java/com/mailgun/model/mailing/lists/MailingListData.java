package com.mailgun.model.mailing.lists;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mailgun.enums.AccessLevel;
import com.mailgun.enums.ReplyPreference;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NUMERIC;

/**
 * <p>
 * Mailgun list data.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-mailinglists.html#mailing-lists">Mailing Lists</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailingListData {

    /**
     * <p>
     * List access level, one of: <code>READONLY</code> (default), <code>MEMBERS</code>, <code>EVERYONE</code>.
     * </p>
     * {@link AccessLevel}
     */
    @JsonProperty("access_level")
    AccessLevel accessLevel;

    /**
     * <p>
     * An email address, the ID for the mailing list.
     * </p>
     */
    String address;

    /**
     * <p>
     * Mailing list creation time.
     * </p>
     * {@link ZonedDateTime}
     */
    @JsonFormat(pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NUMERIC, locale = "en")
    @JsonProperty("created_at")
    ZonedDateTime createdAt;

    /**
     * <p>
     * Mailing list description.
     * </p>
     */
    String description;

    /**
     * <p>
     * Mailing list members count.
     * The total number of mailing list members.
     * </p>
     */
    @JsonProperty("members_count")
    Integer membersCount;

    /**
     * <p>
     * Mailing list name, e.g. <code>Developers</code> (optional).
     * </p>
     */
    String name;

    /**
     * <p>
     * Set where replies should go: <code>list</code> (default) | <code>sender</code> (optional).
     * </p>
     */
    @JsonProperty("reply_preference")
    ReplyPreference replyPreference;

}
