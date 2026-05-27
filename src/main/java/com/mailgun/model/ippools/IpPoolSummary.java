package com.mailgun.model.ippools;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Summary of a dedicated IP pool (DIPP) in list responses.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/get-v3-ip-pools">List dedicated IP pools</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpPoolSummary {

    String description;

    List<String> ips;

    @JsonProperty("is_inherited")
    Boolean isInherited;

    @JsonProperty("is_linked")
    Boolean isLinked;

    String name;

    @JsonProperty("pool_id")
    String poolId;

}
