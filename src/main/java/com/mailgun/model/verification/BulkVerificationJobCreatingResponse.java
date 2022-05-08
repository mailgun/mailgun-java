package com.mailgun.model.verification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Bulk verification job creating response.
 * </p>
 * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html#bulk-verification">Bulk Verification</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BulkVerificationJobCreatingResponse {

    /**
     * <p>
     * Result status message.
     * </p>
     */
    @JsonProperty("id")
    String id;

    /**
     * <p>
     * Result status message.
     * </p>
     */
    @JsonProperty("message")
    String message;

}
