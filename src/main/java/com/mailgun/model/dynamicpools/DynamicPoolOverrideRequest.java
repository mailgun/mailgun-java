package com.mailgun.model.dynamicpools;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Form request for {@code PUT /v1/dynamic_pools/domains/{name}/override}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/put-v1-dynamic-pools-domains--name--override">Override domain assignment</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class DynamicPoolOverrideRequest {

    /**
     * Dynamic IP pool name (e.g. {@code dynamic_good}).
     */
    String pool;

}
