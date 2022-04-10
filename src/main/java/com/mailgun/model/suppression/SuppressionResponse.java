package com.mailgun.model.suppression;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Suppression response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#suppressions">Suppressions</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuppressionResponse {

    /**
     * <p>
     * Result status message.
     * </p>
     */
    String message;

    /**
     * <p>
     * An email address.
     * </p>
     */
    String address;

}
