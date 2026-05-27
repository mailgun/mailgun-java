package com.mailgun.model.dynamicpools;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code POST /v3/domains/all/dynamic_pools/enroll}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/post-v3-domains-all-dynamic-pools-enroll">Enroll all account domains</a>
 */
@Value
@Jacksonized
@Builder
public class EnrollAllDomainsInDynamicPoolsQuery {

    /**
     * When true, subaccount domains are included in the enrollment job.
     */
    Boolean include_subaccounts;

}
