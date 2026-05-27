package com.mailgun.model.ippools;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Form request to delegate or revoke a DIPP for a subaccount.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/put-v3-ip-pools--pool-id--delegate">Delegate DIPP to Subaccount</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/delete-v3-ip-pools--pool-id--delegate">Revoke DIPP from Subaccount</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class IpPoolDelegationRequest {

    /**
     * Subaccount id to delegate to or revoke from.
     */
    @FormProperty("subaccount_id")
    String subaccountId;

}
