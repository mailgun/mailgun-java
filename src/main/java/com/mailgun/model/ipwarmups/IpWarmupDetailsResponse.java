package com.mailgun.model.ipwarmups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response for {@code GET /v3/ip_warmups/{addr}}.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpWarmupDetailsResponse {

    IpWarmupDetails details;

}
