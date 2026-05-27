package com.mailgun.model.ippools;

import lombok.experimental.UtilityClass;

/**
 * Special path and query values for IP pool and domain pool operations.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools">IP Pools</a>
 */
@UtilityClass
public class IpPoolConstants {

    /**
     * Path {@code ip} value: remove the entire domain pool.
     */
    public static final String DOMAIN_POOL_REMOVE_ALL = "all";

    /**
     * Path {@code ip} value: unlink the DIPP currently linked to the domain.
     */
    public static final String DOMAIN_POOL_UNLINK_IP_POOL = "ip_pool";

    /**
     * Replacement IP query value: assign a shared IP (account must be eligible).
     */
    public static final String REPLACEMENT_SHARED_IP = "shared";

}
