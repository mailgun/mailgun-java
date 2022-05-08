package com.mailgun.model.verification;

import java.io.File;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * Bulk verification job status request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html#bulk-verification">Bulk Verification</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class BulkVerificationJobStatusRequest {

    /**
     * <p>
     * An uploaded file of email addresses.
     * </p>
     */
    File file;

}

