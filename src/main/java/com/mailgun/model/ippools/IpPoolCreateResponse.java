package com.mailgun.model.ippools;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response for {@code POST /v3/ip_pools}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/post-v3-ip-pools">Add a new DIPP</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpPoolCreateResponse {

    String message;

    @JsonProperty("pool_id")
    String poolId;

}
