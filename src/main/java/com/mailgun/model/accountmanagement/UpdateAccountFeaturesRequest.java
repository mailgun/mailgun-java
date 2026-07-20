package com.mailgun.model.accountmanagement;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Form fields for updating account features. Each value is a JSON-encoded object,
 * for example {@code {"enabled":false}}.
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class UpdateAccountFeaturesRequest {

    @FormProperty("webhooks_redact_pii")
    String webhooksRedactPii;

    @FormProperty("ai_insights")
    String aiInsights;

}
