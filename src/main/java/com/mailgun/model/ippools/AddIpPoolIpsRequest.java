package com.mailgun.model.ippools;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * JSON body for {@code POST /v3/ip_pools/{pool_id}/ips.json}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/post-v3-ip-pools--pool-id--ips-json">Add multiple IPs to the DIPP</a>
 */
@Jacksonized
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class AddIpPoolIpsRequest {

    /**
     * Dedicated IPs to add to the DIPP.
     */
    List<String> ips;

}
