package com.mailgun.model.domainkeys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mailgun.model.Paging;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Response for listing domain keys (GET /v1/dkim/keys). Paginated; use 'page' from paging URLs to navigate.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/get-v1-dkim-keys">List keys for all domains</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainKeysListResponse {

    List<DkimKeyItem> items;
    Paging paging;
}
