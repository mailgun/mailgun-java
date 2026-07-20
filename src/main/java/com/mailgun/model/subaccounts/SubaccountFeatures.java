package com.mailgun.model.subaccounts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Features available to a subaccount.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubaccountFeatures {

    @JsonProperty("email_preview")
    SubaccountFeature emailPreview;

    @JsonProperty("inbox_placement")
    SubaccountFeature inboxPlacement;

    SubaccountFeature sending;

    SubaccountFeature validations;

    @JsonProperty("validations_bulk")
    SubaccountFeature validationsBulk;

}
