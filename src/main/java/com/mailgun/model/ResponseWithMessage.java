package com.mailgun.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Response with one single message.
 * </p>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseWithMessage {

    /**
     * <p>
     * Result status message.
     * </p>
     */
    String message;

}
