package com.mailgun.model.dynamicpools;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Response for {@code GET /v3/dynamic_pools}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools/get-v3-dynamic-pools">List all Dynamic IP pools</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamicPoolsListResponse {

    @JsonProperty("dynamic_good")
    List<String> dynamicGood;

    @JsonProperty("dynamic_poor")
    List<String> dynamicPoor;

    @JsonProperty("dynamic_new")
    List<String> dynamicNew;

    @JsonProperty("good_reputation")
    List<String> goodReputation;

    @JsonProperty("poor_reputation")
    List<String> poorReputation;

    @JsonProperty("new_senders")
    List<String> newSenders;

    String message;

}
