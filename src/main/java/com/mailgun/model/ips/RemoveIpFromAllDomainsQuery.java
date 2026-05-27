package com.mailgun.model.ips;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code DELETE /v3/ips/{ip}/domains}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/delete-v3-ips--ip--domains">Remove IP from all domains</a>
 */
@Value
@Jacksonized
@Builder
public class RemoveIpFromAllDomainsQuery {

    /**
     * Replacement IP assigned to all domains after removal.
     */
    String alternative;

}
