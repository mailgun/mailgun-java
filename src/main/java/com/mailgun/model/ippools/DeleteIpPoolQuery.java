package com.mailgun.model.ippools;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code DELETE /v3/ip_pools/{pool_id}} (replacement DIPP or IP).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/delete-v3-ip-pools--pool-id-">Delete the DIPP</a>
 */
@Value
@Jacksonized
@Builder
public class DeleteIpPoolQuery {

    /**
     * Replacement dedicated IP, or {@link IpPoolConstants#REPLACEMENT_SHARED_IP} for shared IPs.
     */
    String ip;

    /**
     * Id of the replacement DIPP. Either {@code ip} or {@code pool_id} may be set, not both.
     */
    String pool_id;

}
