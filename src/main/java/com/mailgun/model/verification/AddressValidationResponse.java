package com.mailgun.model.verification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * <p>
 * Address Validation Response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html#field-explanation">field-explanation</a>
 */
@Value
@Jacksonized
@Builder
public class AddressValidationResponse {

    /**
     * <p>
     * Email address being validated
     * </p>
     */
    @JsonProperty("address")
    String address;

    /**
     * <p>
     * If the domain is in a list of disposable email addresses, this will be appropriately categorized.
     * </p>
     */
    @JsonProperty("is_disposable_address")
    Boolean isDisposableAddress;

    /**
     * <p>
     * Checks the mailbox portion of the email if it matches a specific role type (<code>admin</code>, <code>sales</code> or <code>webmaster</code>).
     * </p>
     */
    @JsonProperty("is_role_address")
    Boolean isRoleAddress;

    /**
     * <p>
     * List of potential reasons why a specific validation may be unsuccessful.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html#reason-explanation">reason-explanation</a>
     */
    @JsonProperty("reason")
    List<String> reason;

    /**
     * <p>
     * Either <code>deliverable</code>, <code>undeliverable</code>, <code>do_not_send</code>, <code>catch_all</code> or <code>unknown</code>.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html#result-types">result-types</a>
     */
    @JsonProperty("result")
    String result;

    /**
     * <p>
     * <code>high</code>, <code>medium</code>, <code>low</code> or <code>unknown</code>.
     * Depending on the evaluation of all aspects of the given email.
     * </p>
     */
    @JsonProperty("risk")
    String risk;

}
