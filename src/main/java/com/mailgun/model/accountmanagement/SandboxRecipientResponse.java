package com.mailgun.model.accountmanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response containing an authorized sandbox recipient.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SandboxRecipientResponse {

    SandboxRecipient recipient;

}
