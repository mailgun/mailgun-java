package com.mailgun.model.ips;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Single IP.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-ips.html#ips">IPs</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IPResult {

    /**
     * <p>
     * IP address.
     * </p>
     */
    String ip;

    /**
     * <p>
     * Is this IP dedicated.
     * </p>
     */
    Boolean dedicated;

    /**
     * <p>
     * Reverse DNS.
     * </p>
     */
    String rdns;

}
