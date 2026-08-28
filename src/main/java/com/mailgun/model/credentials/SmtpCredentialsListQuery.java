package com.mailgun.model.credentials;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/** Optional pagination for listing SMTP credentials. */
@Value
@Jacksonized
@Builder
public class SmtpCredentialsListQuery {

    Integer skip;

    Integer limit;
}
