package com.mailgun.model.ippools;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters when removing an IP from a domain pool or unlinking a DIPP
 * ({@code DELETE /v3/domains/{name}/ips/{ip}} or {@code .../pool/{ip}}).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/delete-v3-domains--name--ips--ip-">Remove domain pool IP / unlink DIPP</a>
 */
@Value
@Jacksonized
@Builder
public class DomainIpRemovalQuery {

    /**
     * Replacement dedicated IP, or {@link IpPoolConstants#REPLACEMENT_SHARED_IP} when unlinking a DIPP.
     * Either {@code ip} or {@code pool_id} must be specified when unlinking, not both.
     */
    String ip;

    /**
     * Replacement DIPP id when unlinking. Either {@code ip} or {@code pool_id} must be specified, not both.
     */
    String pool_id;

}
