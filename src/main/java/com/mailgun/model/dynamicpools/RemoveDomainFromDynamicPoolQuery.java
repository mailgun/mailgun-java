package com.mailgun.model.dynamicpools;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Query parameters for {@code DELETE /v3/domains/{name}/dynamic_pools}.
 * Either {@code replacement_ip} or {@code replacement_pool_id} must be set, not both.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/delete-v3-domains--name--dynamic-pools">Remove domain from dynamic IP pools</a>
 */
@Value
@Jacksonized
@Builder
public class RemoveDomainFromDynamicPoolQuery {

    /**
     * Dedicated IP(s) or {@link DynamicPoolConstants#REPLACEMENT_SHARED_IP} (may be repeated).
     */
    List<String> replacement_ip;

    /**
     * Dedicated IP pool id to assign when leaving dynamic pools.
     */
    String replacement_pool_id;

}
