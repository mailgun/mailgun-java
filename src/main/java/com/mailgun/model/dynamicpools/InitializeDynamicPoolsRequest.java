package com.mailgun.model.dynamicpools;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Form request for {@code POST /v3/dynamic_pools/all}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/post-v3-dynamic-pools-all">Initialize/set IPs for all pools</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class InitializeDynamicPoolsRequest {

    @FormProperty("good_reputation")
    List<String> goodReputation;

    @FormProperty("poor_reputation")
    List<String> poorReputation;

    @FormProperty("new_senders")
    List<String> newSenders;

}
