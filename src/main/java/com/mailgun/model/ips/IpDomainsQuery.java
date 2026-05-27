package com.mailgun.model.ips;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code GET /v3/ips/{ip}/domains}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/get-v3-ips--ip--domains">Get IP domains</a>
 */
@Value
@Jacksonized
@Builder
public class IpDomainsQuery {

    Integer limit;

    Integer skip;

    /**
     * Search domains by name (words OR-matched).
     */
    String search;

}
