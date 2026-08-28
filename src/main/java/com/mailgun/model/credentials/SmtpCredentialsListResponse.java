package com.mailgun.model.credentials;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/** Response from listing SMTP credential metadata. */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmtpCredentialsListResponse {

    List<SmtpCredential> items;

    @JsonProperty("total_count")
    Integer totalCount;
}
