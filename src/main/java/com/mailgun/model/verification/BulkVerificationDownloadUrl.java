package com.mailgun.model.verification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * <code>csv</code> and <code>json</code> representation of the download link for the results of the bulk verifications.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html#bulk-verification">Bulk Verification</a>
 */
@Value
@Jacksonized
@Builder
public class BulkVerificationDownloadUrl {

    /**
     * <p>
     * <code>csv</code> representation of the download link for the results of the bulk verifications.
     * </p>
     */
    @JsonProperty("csv")
    String csv;

    /**
     * <p>
     * <code>json</code> representation of the download link for the results of the bulk verifications.
     * </p>
     */
    @JsonProperty("json")
    String json;

}
