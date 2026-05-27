package com.mailgun.model.ippools;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mailgun.model.Paging;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Paginated list of domains linked to a DIPP.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/get-v3-ip-pools--pool-id--domains">Get domains linked to DIPP</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpPoolDomainsResponse {

    List<IpPoolLinkedDomain> domains;

    Paging paging;

}
