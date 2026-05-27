package com.mailgun.model.ips;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * <p>
 * List of IPs.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-ips.html#ips">IPs</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IPsResult {

    /**
     * IPs that can be assigned to dedicated IP pools (present when DIPPs feature is enabled).
     */
    @JsonProperty("assignable_to_pools")
    List<String> assignableToPools;

    /**
     * Detailed information per IP.
     */
    List<IpDetail> details;

    /**
     * <p>
     * List of IPs.
     * </p>
     */
    List<String> items;

    /**
     * <p>
     * The total number of IPs returned for the query.
     * </p>
     */
    @JsonProperty("total_count")
    Integer totalCount;

}
