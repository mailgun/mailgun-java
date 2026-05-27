package com.mailgun.model.ips;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code GET /v3/ips}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/get-v3-ips">List account IPs</a>
 */
@Value
@Jacksonized
@Builder
public class IpsListQuery {

    /**
     * When true, return only dedicated IPs.
     */
    Boolean dedicated;

    /**
     * When true, return only enabled IPs.
     */
    Boolean enabled;

}
