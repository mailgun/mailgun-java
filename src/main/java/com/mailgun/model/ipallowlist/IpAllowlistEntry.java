package com.mailgun.model.ipallowlist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/** An allowlisted IP address and its description. */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpAllowlistEntry {

    @JsonProperty("ip_address")
    String ipAddress;

    String description;
}
