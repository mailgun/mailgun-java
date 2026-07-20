package com.mailgun.model.accountmanagement;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Variable account settings accepted by {@code PUT /v5/accounts}.
 */
@Value
@Jacksonized
@Builder
public class UpdateAccountSettingsQuery {

    String name;

    Integer inactive_session_timeout;

    Integer absolute_session_timeout;

    String logout_redirect_url;

}
