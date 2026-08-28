package com.mailgun.model.credentials;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/** Response from deleting every SMTP credential for a domain. */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmtpCredentialsDeleteResponse {

    String message;

    Integer count;
}
