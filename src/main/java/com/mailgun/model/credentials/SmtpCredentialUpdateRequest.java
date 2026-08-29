package com.mailgun.model.credentials;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/** Form fields for updating an SMTP credential. */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class SmtpCredentialUpdateRequest {

    @ToString.Exclude
    String password;
}
