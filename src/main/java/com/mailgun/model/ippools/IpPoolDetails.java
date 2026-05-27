package com.mailgun.model.ippools;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Detailed properties of a dedicated IP pool (DIPP).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/get-v3-ip-pools--pool-id-">Get DIPP details</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpPoolDetails {

    String description;

    List<String> ips;

    @JsonProperty("is_linked")
    Boolean isLinked;

    @JsonProperty("linked_domains")
    List<String> linkedDomains;

    String name;

    @JsonProperty("pool_id")
    String poolId;

}
