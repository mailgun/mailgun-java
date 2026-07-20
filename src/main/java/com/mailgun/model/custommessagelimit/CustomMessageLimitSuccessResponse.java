package com.mailgun.model.custommessagelimit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response indicating whether a custom message limit operation succeeded.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomMessageLimitSuccessResponse {

    Boolean success;

}
