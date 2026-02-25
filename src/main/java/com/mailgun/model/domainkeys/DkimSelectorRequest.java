package com.mailgun.model.domainkeys;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Request to update DKIM selector (PUT /v3/domains/{name}/dkim_selector). Selector must be unique.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/put-v3-domains--name--dkim-selector">Update a DKIM selector</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class DkimSelectorRequest {

    /**
     * New DKIM selector for the domain. If omitted, no change is committed.
     */
    @FormProperty("dkim_selector")
    String dkimSelector;
}
