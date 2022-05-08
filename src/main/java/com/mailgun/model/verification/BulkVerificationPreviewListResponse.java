package com.mailgun.model.verification;

import java.util.List;

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
public class BulkVerificationPreviewListResponse {

    /**
     * <p>
     * Verification previews {@link BulkVerificationPreviewStatusResponse}.
     * </p>
     */
    @JsonProperty("previews")
    List<BulkVerificationPreviewStatusResponse> previews;

}
