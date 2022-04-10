package com.mailgun.model.mailing.lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mailgun.model.Paging;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailingListMembersResponse {

    /**
     * <p>
     * List of {@link MailingListMember}.
     * </p>
     */
    List<MailingListMember> items;

    /**
     * <p>
     * Provides pagination urls.
     * </p>
     * {@link Paging}
     */
    Paging paging;

}
