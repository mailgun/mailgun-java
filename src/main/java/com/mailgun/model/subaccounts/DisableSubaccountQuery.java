package com.mailgun.model.subaccounts;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Optional reason and note for disabling a subaccount.
 */
@Value
@Jacksonized
@Builder
public class DisableSubaccountQuery {

    String reason;

    String note;

}
