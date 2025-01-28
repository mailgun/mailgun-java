package com.mailgun.model.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Account usage metrics.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#metrics">Metrics</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountUsageMetrics {

    @JsonProperty("email_validation_single_count")
    Integer emailValidationSingleCount;

    @JsonProperty("email_validation_count")
    Integer emailValidationCount;

    @JsonProperty("email_validation_public_count")
    Integer emailValidationPublicCount;

    @JsonProperty("email_validation_valid_count")
    Integer emailValidationValidCount;

    @JsonProperty("email_validation_list_count")
    Integer emailValidationListCount;

    @JsonProperty("processed_count")
    Integer processedCount;

    @JsonProperty("email_validation_bulk_count")
    Integer emailValidationBulkCount;

    @JsonProperty("email_validation_mailjet_count")
    Integer emailValidationMailjetCount;

    @JsonProperty("email_preview_count")
    Integer emailPreviewCount;

    @JsonProperty("email_validation_mailgun_count")
    Integer emailValidationMailgunCount;

    @JsonProperty("link_validation_count")
    Integer linkValidationCount;

    @JsonProperty("email_preview_failed_count")
    Integer emailPreviewFailedCount;

    @JsonProperty("link_validation_failed_count")
    Integer linkValidationFailedCount;

    @JsonProperty("seed_test_count")
    Integer seedTestCount;
}
