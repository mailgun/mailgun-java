package com.mailgun.model.ipwarmups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mailgun.model.Paging;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Response for {@code GET /v3/ip_warmups}.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpWarmupsListResponse {

    List<IpWarmupStatus> items;

    Paging paging;

}
