package com.mailgun.model.domainkeys;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Request to update DKIM authority (PUT /v3/domains/{name}/dkim_authority).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/put-v3-domains--name--dkim-authority">Update DKIM authority</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class DkimAuthorityRequest {

    /**
     * If true, domain is DKIM authority for itself. If false, domain uses root domain's DKIM authority.
     */
    @FormProperty("self")
    Boolean self;
}
