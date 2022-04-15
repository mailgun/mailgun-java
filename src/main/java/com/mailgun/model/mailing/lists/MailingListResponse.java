package com.mailgun.model.mailing.lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Creation mailing list response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-mailinglists.html#mailing-lists">Mailing Lists</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailingListResponse {

    /**
     * <p>
     * Mailing list data.
     * </p>
     * {@link MailingListData}.
     */
    MailingListData list;

    /**
     * <p>
     * Result status message.
     * </p>
     */
    String message;

    /**
     * Task id.
     */
    @JsonProperty("task-id")
    String taskId;

}
