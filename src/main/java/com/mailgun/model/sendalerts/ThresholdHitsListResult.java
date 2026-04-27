package com.mailgun.model.sendalerts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Response for {@code GET /v1/thresholds/hits}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/get-v1-thresholds-hits.md">List account hits</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThresholdHitsListResult {

    List<ThresholdHit> items;

    Integer total;

}
