package com.mailgun.model.ipwarmups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;

/**
 * Summary of an in-flight IP warmup.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpWarmupStatus {

    String ip;

    @JsonProperty("sent_within_stage")
    String sentWithinStage;

    Integer throttle;

    @JsonProperty("stage_number")
    Integer stageNumber;

    @JsonProperty("stage_start_volume")
    Integer stageStartVolume;

    @JsonProperty("stage_start_time")
    Instant stageStartTime;

    @JsonProperty("stage_volume_limit")
    Integer stageVolumeLimit;

}
