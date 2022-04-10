package com.mailgun.model.mailing.lists;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;


/**
 * <p>
 * Verification mailing list response.
 * </p>
 */
@Value
@Jacksonized
@Builder
public class MailingListVerificationResponse {

    /**
     * <p>
     * An email address, the ID for the mailing list..
     * </p>
     */
    String id;

    /**
     * <p>
     * Result status message.
     * </p>
     */
    String message;

}
