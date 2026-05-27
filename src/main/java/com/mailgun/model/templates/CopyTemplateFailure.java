package com.mailgun.model.templates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * A failed entry in {@link CopyTemplateResponse#getFailedCopies()}.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CopyTemplateFailure {

    @JsonProperty("account_id")
    String accountId;

    String name;

    String domain;

    String error;

}
