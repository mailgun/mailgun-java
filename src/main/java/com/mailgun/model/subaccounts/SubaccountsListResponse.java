package com.mailgun.model.subaccounts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Response containing subaccounts and their total count.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubaccountsListResponse {

    List<Subaccount> subaccounts;

    Integer total;

}
