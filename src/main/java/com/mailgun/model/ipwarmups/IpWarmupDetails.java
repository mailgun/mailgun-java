package com.mailgun.model.ipwarmups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;
import java.util.List;

/**
 * Detailed status of an in-flight IP warmup.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpWarmupDetails {

    @JsonProperty("sent_within_stage")
    String sentWithinStage;

    Integer throttle;

    @JsonAlias("stage_start_volume")
    @JsonProperty("start_volume")
    Integer startVolume;

    @JsonAlias("stage_number")
    Integer stage;

    @JsonAlias("stage_volume_limit")
    @JsonProperty("stage_limit")
    Integer stageLimit;

    /**
     * Present in the API documentation example, but omitted by some API responses.
     */
    @JsonProperty("hourly_limit")
    Integer hourlyLimit;

    @JsonProperty("stage_started_at")
    Instant stageStartedAt;

    @JsonProperty("hour_started_at")
    Instant hourStartedAt;

    @JsonProperty("plan_started_at")
    Instant planStartedAt;

    @JsonProperty("plan_last_updated_at")
    Instant planLastUpdatedAt;

    @JsonProperty("total_stages")
    Integer totalStages;

    @JsonProperty("stage_history")
    List<IpWarmupStageHistory> stageHistory;

}
