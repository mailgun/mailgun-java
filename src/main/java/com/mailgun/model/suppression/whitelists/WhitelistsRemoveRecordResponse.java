package com.mailgun.model.suppression.whitelists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Response to removing a record from the whitelists.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#whitelists">Suppressions/Whitelists</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WhitelistsRemoveRecordResponse {

    /**
     * <p>
     * Result status message.
     * </p>
     */
    String message;

    /**
     * <p>
     * Address or domain.
     * </p>
     */
    String value;
}
