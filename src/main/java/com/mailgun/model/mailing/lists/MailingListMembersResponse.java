package com.mailgun.model.mailing.lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
     * Total members in the list (present on {@code GET /v3/lists/.../members}).
     */
    @JsonProperty("total_count")
    Integer totalCount;

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
