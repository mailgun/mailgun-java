package com.mailgun.model.mailing.lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Deletion mailgun list response.
 * </p
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-mailinglists.html#mailing-lists">Mailing Lists</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteMailingListResponse {

    /**
     * <p>
     * An email address, the ID for the mailing list.
     * </p>
     */
    String address;

    /**
     * <p>
     * Result status message.
     * </p>
     */
    String message;

}
