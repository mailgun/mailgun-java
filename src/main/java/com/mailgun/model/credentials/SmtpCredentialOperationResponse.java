package com.mailgun.model.credentials;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

/** Response from creating or updating SMTP credentials. */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmtpCredentialOperationResponse {

    String message;

    String note;

    Map<String, String> credentials;
}
