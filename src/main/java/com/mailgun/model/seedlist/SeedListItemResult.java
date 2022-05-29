package com.mailgun.model.seedlist;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * A result is an object summarizing Inbox Placement tests sent to the target_email.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-inbox-placement.html">Seed Lists</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeedListItemResult {

    /**
     * <p>
     * Unique identifier for a received test.
     * </p>
     */
    @JsonProperty("result_id")
    String resultId;

    /**
     * <p>
     * The subject of the email sent to the target_email.
     * </p>
     */
    String subject;

    /**
     * <p>
     * Sender address of the email sent to the target_email.
     * </p>
     */
    String sender;

    /**
     * <p>
     * An object that contains sub-objects that describe delivery stats.
     * </p>
     */
    @JsonProperty("delivery_stats")
    Map<String, Map<String, Object>> deliveryStats;

}
