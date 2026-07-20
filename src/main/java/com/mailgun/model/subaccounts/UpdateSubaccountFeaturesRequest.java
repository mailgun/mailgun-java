package com.mailgun.model.subaccounts;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Form fields for updating subaccount features. Each value is a JSON-encoded object,
 * for example {@code {"enabled":false}}.
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class UpdateSubaccountFeaturesRequest {

    @FormProperty("email_preview")
    String emailPreview;

    @FormProperty("inbox_placement")
    String inboxPlacement;

    @FormProperty("sending")
    String sending;

    @FormProperty("validations")
    String validations;

    @FormProperty("validations_bulk")
    String validationsBulk;

}
