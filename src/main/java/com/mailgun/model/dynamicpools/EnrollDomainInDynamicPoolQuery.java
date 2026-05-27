package com.mailgun.model.dynamicpools;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code POST /v3/domains/{name}/dynamic_pools}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/post-v3-domains--name--dynamic-pools">Enroll domain</a>
 */
@Value
@Jacksonized
@Builder
public class EnrollDomainInDynamicPoolQuery {

    /**
     * A valid dedicated IP address or {@link DynamicPoolConstants#REPLACEMENT_SHARED_IP}.
     */
    String replacement_ip;

}
