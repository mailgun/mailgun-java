package com.mailgun.model.credentials;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

/** Form fields for creating one or more SMTP credentials. */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class SmtpCredentialCreateRequest {

    @Singular("login")
    @FormProperty("login")
    List<String> logins;

    @Singular("mailbox")
    @FormProperty("mailbox")
    List<String> mailboxes;

    Boolean system;

    @Singular("password")
    @FormProperty("password")
    @ToString.Exclude
    List<String> passwords;
}
