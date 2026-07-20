package com.mailgun.model.accountmanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Authorized recipient of a sandbox domain.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SandboxRecipient {

    String email;

    Boolean activated;

}
