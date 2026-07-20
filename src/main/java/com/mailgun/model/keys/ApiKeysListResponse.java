package com.mailgun.model.keys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Response from {@code GET /v1/keys}.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiKeysListResponse {

    @JsonProperty("total_count")
    Integer totalCount;

    List<ApiKey> items;
}
