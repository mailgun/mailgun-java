package com.mailgun.model.custommessagelimit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

/**
 * Current custom monthly sending limit and usage.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomMessageLimitResponse {

    BigDecimal limit;

    BigDecimal current;

    String period;

}
