package com.mailgun.model.mailing.lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Update mailgun lists request.
 * </p
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-mailinglists.html#mailing-lists">Mailing Lists</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleMailingListResponse {

    /**
     * <p>
     * {@link MailingListData}.
     * </p>
     */
    MailingListData list;

}
