package com.mailgun.model.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Account metrics.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#metrics">Metrics</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountMetrics {

    @JsonProperty("accepted_outgoing_count")
    Integer acceptedOutgoingCount;

    @JsonProperty("delivered_smtp_count")
    Integer deliveredSmtpCount;

    @JsonProperty("accepted_count")
    Integer acceptedCount;

    @JsonProperty("delivered_http_count")
    Integer deliveredHttpCount;

    @JsonProperty("accepted_incoming_count")
    Integer acceptedIncomingCount;

    @JsonProperty("delivered_optimized_count")
    Integer deliveredOptimizedCount;

    @JsonProperty("stored_count")
    Integer storedCount;

    @JsonProperty("delivered_count")
    Integer deliveredCount;

    @JsonProperty("processed_count")
    Integer processedCount;

    @JsonProperty("sent_count")
    Integer sentCount;

    @JsonProperty("opened_count")
    Integer openedCount;

    @JsonProperty("unique_opened_count")
    Integer uniqueOpenedCount;

    @JsonProperty("clicked_count")
    Integer clickedCount;

    @JsonProperty("unique_clicked_count")
    Integer uniqueClickedCount;

    @JsonProperty("complained_count")
    Integer complainedCount;

    @JsonProperty("permanent_failed_count")
    Integer permanentFailedCount;

    @JsonProperty("failed_count")
    Integer failedCount;

    @JsonProperty("rate_limit_count")
    Integer rateLimitCount;

    @JsonProperty("unsubscribed_count")
    Integer unsubscribedCount;

    @JsonProperty("temporary_failed_count")
    Integer temporaryFailedCount;

    @JsonProperty("permanent_failed_optimized_count")
    Integer permanentFailedOptimizedCount;

    @JsonProperty("bounced_count")
    Integer bouncedCount;

    @JsonProperty("esp_block_count")
    Integer espBlockCount;

    @JsonProperty("webhook_count")
    Integer webhookCount;

    @JsonProperty("delayed_bounce_count")
    Integer delayedBounceCount;

    @JsonProperty("soft_bounces_count")
    Integer softBouncesCount;

    @JsonProperty("permanent_failed_old_count")
    Integer permanentFailedOldCount;

    @JsonProperty("suppressed_bounces_count")
    Integer suppressedBouncesCount;

    @JsonProperty("delivered_subsequent_count")
    Integer deliveredSubsequentCount;

    @JsonProperty("delivered_rate")
    String deliveredRate;

    @JsonProperty("delayed_first_attempt_count")
    Integer delayedFirstAttemptCount;

    @JsonProperty("unsubscribed_rate")
    String unsubscribedRate;

    @JsonProperty("delivered_first_attempt_count")
    Integer deliveredFirstAttemptCount;

    @JsonProperty("opened_rate")
    String openedRate;

    @JsonProperty("suppressed_complaints_count")
    Integer suppressedComplaintsCount;

    @JsonProperty("delivered_two_plus_attempts_count")
    Integer deliveredTwoPlusAttemptsCount;

    @JsonProperty("hard_bounces_count")
    Integer hardBouncesCount;

    @JsonProperty("suppressed_unsubscribed_count")
    Integer suppressedUnsubscribedCount;

    @JsonProperty("unique_opened_rate")
    String uniqueOpenedRate;

    @JsonProperty("fail_rate")
    String failRate;

    @JsonProperty("complained_rate")
    String complainedRate;

    @JsonProperty("clicked_rate")
    String clickedRate;

    @JsonProperty("unique_clicked_rate")
    String uniqueClickedRate;

    @JsonProperty("bounce_rate")
    String bounceRate;

    @JsonProperty("delayed_rate")
    String delayedRate;

    @JsonProperty("permanent_fail_rate")
    String permanentFailRate;

    @JsonProperty("temporary_fail_rate")
    String temporaryFailRate;
}
