package com.mailgun.model.credentials;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/** Response from deleting one SMTP credential. */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmtpCredentialDeleteResponse {

    String message;

    String spec;
}
