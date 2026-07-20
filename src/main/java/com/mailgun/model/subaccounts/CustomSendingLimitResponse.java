package com.mailgun.model.subaccounts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

/**
 * Current custom monthly sending limit and usage for a subaccount.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomSendingLimitResponse {

    BigDecimal limit;

    BigDecimal current;

    String period;

}
