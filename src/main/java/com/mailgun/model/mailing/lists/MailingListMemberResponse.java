package com.mailgun.model.mailing.lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailingListMemberResponse {

    /**
     * <p>
     * Mailing list member.
     *  {@link MailingListMember}
     * </p>
     */
    MailingListMember member;

    /**
     * <p>
     * Result status message.
     * </p>
     */
    String message;

}
