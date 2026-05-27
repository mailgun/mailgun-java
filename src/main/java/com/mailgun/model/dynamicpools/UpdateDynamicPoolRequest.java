package com.mailgun.model.dynamicpools;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Form request for {@code PATCH /v3/dynamic_pools/{pool_name}}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/patch-v3-dynamic-pools--pool-name-">Update pool IPs</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class UpdateDynamicPoolRequest {

    @FormProperty("add_ip")
    List<String> addIp;

    @FormProperty("remove_ip")
    List<String> removeIp;

}
