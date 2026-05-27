package com.mailgun.model.ippools;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Response for {@code GET /v3/ip_pools}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/get-v3-ip-pools">List dedicated IP pools</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpPoolsListResponse {

    @JsonProperty("ip_pools")
    List<IpPoolSummary> ipPools;

    String message;

}
