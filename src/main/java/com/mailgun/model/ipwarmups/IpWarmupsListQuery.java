package com.mailgun.model.ipwarmups;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code GET /v3/ip_warmups}.
 */
@Value
@Jacksonized
@Builder
public class IpWarmupsListQuery {

    String page;

    String limit;

}
