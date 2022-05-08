package com.mailgun.model.verification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Bulk verification preview list Response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html#bulk-verification">Bulk Verification</a>
 */
@Value
@Jacksonized
@Builder
public class BulkVerificationPreviewResponse {

    /**
     * <p>
     * Verification preview {@link BulkVerificationPreviewStatusResponse}.
     * </p>
     */
    @JsonProperty("preview")
    BulkVerificationPreviewStatusResponse preview;

}
