package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Per-tag email metrics returned when {@code include_metrics=true} in the analytics tags list request.
 * Combines delivery, engagement, failure, and usage metrics.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags-new/post-v1-analytics-tags.md">Post query to list account tags</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalyticsTagMetrics {

    @JsonProperty("accepted_incoming_count")
    Integer acceptedIncomingCount;

    @JsonProperty("accepted_outgoing_count")
    Integer acceptedOutgoingCount;

    @JsonProperty("accepted_count")
    Integer acceptedCount;

    @JsonProperty("delivered_smtp_count")
    Integer deliveredSmtpCount;

    @JsonProperty("delivered_http_count")
    Integer deliveredHttpCount;

    @JsonProperty("delivered_optimized_count")
    Integer deliveredOptimizedCount;

    @JsonProperty("delivered_count")
    Integer deliveredCount;

    @JsonProperty("stored_count")
    Integer storedCount;

    @JsonProperty("sent_count")
    Integer sentCount;

    @JsonProperty("opened_count")
    Integer openedCount;

    @JsonProperty("clicked_count")
    Integer clickedCount;

    @JsonProperty("unique_opened_count")
    Integer uniqueOpenedCount;

    @JsonProperty("unique_clicked_count")
    Integer uniqueClickedCount;

    @JsonProperty("unsubscribed_count")
    Integer unsubscribedCount;

    @JsonProperty("complained_count")
    Integer complainedCount;

    @JsonProperty("failed_count")
    Integer failedCount;

    @JsonProperty("temporary_failed_count")
    Integer temporaryFailedCount;

    @JsonProperty("permanent_failed_count")
    Integer permanentFailedCount;

    /**
     * Deprecated — use {@code temporaryFailedEspBlockCount} instead.
     */
    @JsonProperty("esp_block_count")
    Integer espBlockCount;

    @JsonProperty("temporary_failed_esp_block_count")
    Integer temporaryFailedEspBlockCount;

    @JsonProperty("permanent_failed_esp_block_count")
    Integer permanentFailedEspBlockCount;

    @JsonProperty("rate_limit_count")
    Integer rateLimitCount;

    @JsonProperty("webhook_count")
    Integer webhookCount;

    @JsonProperty("permanent_failed_optimized_count")
    Integer permanentFailedOptimizedCount;

    @JsonProperty("permanent_failed_old_count")
    Integer permanentFailedOldCount;

    @JsonProperty("bounced_count")
    Integer bouncedCount;

    @JsonProperty("hard_bounces_count")
    Integer hardBouncesCount;

    @JsonProperty("soft_bounces_count")
    Integer softBouncesCount;

    @JsonProperty("delayed_bounce_count")
    Integer delayedBounceCount;

    @JsonProperty("suppressed_bounces_count")
    Integer suppressedBouncesCount;

    @JsonProperty("suppressed_unsubscribed_count")
    Integer suppressedUnsubscribedCount;

    @JsonProperty("suppressed_complaints_count")
    Integer suppressedComplaintsCount;

    @JsonProperty("delivered_first_attempt_count")
    Integer deliveredFirstAttemptCount;

    @JsonProperty("delayed_first_attempt_count")
    Integer delayedFirstAttemptCount;

    @JsonProperty("delivered_subsequent_count")
    Integer deliveredSubsequentCount;

    @JsonProperty("delivered_two_plus_attempts_count")
    Integer deliveredTwoPlusAttemptsCount;

    @JsonProperty("processed_count")
    Integer processedCount;

    @JsonProperty("delivered_rate")
    String deliveredRate;

    @JsonProperty("opened_rate")
    String openedRate;

    @JsonProperty("clicked_rate")
    String clickedRate;

    @JsonProperty("unique_opened_rate")
    String uniqueOpenedRate;

    @JsonProperty("unique_clicked_rate")
    String uniqueClickedRate;

    @JsonProperty("unsubscribed_rate")
    String unsubscribedRate;

    @JsonProperty("complained_rate")
    String complainedRate;

    @JsonProperty("bounce_rate")
    String bounceRate;

    @JsonProperty("hard_bounce_rate")
    String hardBounceRate;

    @JsonProperty("soft_bounce_rate")
    String softBounceRate;

    @JsonProperty("fail_rate")
    String failRate;

    @JsonProperty("permanent_fail_rate")
    String permanentFailRate;

    @JsonProperty("temporary_fail_rate")
    String temporaryFailRate;

    @JsonProperty("delayed_rate")
    String delayedRate;

    @JsonProperty("email_validation_count")
    Integer emailValidationCount;

    @JsonProperty("email_validation_public_count")
    Integer emailValidationPublicCount;

    @JsonProperty("email_validation_valid_count")
    Integer emailValidationValidCount;

    @JsonProperty("email_validation_single_count")
    Integer emailValidationSingleCount;

    @JsonProperty("email_validation_bulk_count")
    Integer emailValidationBulkCount;

    @JsonProperty("email_validation_list_count")
    Integer emailValidationListCount;

    @JsonProperty("email_validation_mailgun_count")
    Integer emailValidationMailgunCount;

    @JsonProperty("email_validation_mailjet_count")
    Integer emailValidationMailjetCount;

    @JsonProperty("email_preview_count")
    Integer emailPreviewCount;

    @JsonProperty("email_preview_failed_count")
    Integer emailPreviewFailedCount;

    @JsonProperty("link_validation_count")
    Integer linkValidationCount;

    @JsonProperty("link_validation_failed_count")
    Integer linkValidationFailedCount;

    @JsonProperty("seed_test_count")
    Integer seedTestCount;

    @JsonProperty("accessibility_count")
    Integer accessibilityCount;

    @JsonProperty("accessibility_failed_count")
    Integer accessibilityFailedCount;

    @JsonProperty("image_validation_count")
    Integer imageValidationCount;

    @JsonProperty("image_validation_failed_count")
    Integer imageValidationFailedCount;

}
