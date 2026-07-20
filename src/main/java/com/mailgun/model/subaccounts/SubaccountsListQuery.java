package com.mailgun.model.subaccounts;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Optional filters and pagination for listing subaccounts.
 */
@Value
@Jacksonized
@Builder
public class SubaccountsListQuery {

    String sort;

    String filter;

    Integer limit;

    Integer skip;

    Boolean enabled;

    Boolean closed;

}
