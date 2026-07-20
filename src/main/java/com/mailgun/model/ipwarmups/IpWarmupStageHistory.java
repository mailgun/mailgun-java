package com.mailgun.model.ipwarmups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;

/**
 * Completed stage of an IP warmup plan.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpWarmupStageHistory {

    @JsonProperty("first_updated_at")
    Instant firstUpdatedAt;

    @JsonProperty("completed_at")
    Instant completedAt;

    Integer limit;

}
