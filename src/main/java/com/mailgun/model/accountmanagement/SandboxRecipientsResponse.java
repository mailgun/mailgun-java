package com.mailgun.model.accountmanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Response containing authorized sandbox recipients.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SandboxRecipientsResponse {

    List<SandboxRecipient> recipients;

}
