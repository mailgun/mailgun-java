package com.mailgun.model.subaccounts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response containing the updated features of a subaccount.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubaccountFeaturesResponse {

    SubaccountFeatures features;

}
