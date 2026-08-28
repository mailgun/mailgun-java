package com.mailgun.model.ipallowlist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/** Response containing the account's current IP allowlist entries. */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpAllowlistResponse {

    List<IpAllowlistEntry> addresses;
}
