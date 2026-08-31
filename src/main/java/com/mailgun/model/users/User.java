package com.mailgun.model.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mailgun.enums.EnumWithValue;
import com.mailgun.enums.UserRole;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;
import java.util.Optional;

/**
 * Mailgun user entity.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/users">Users API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("id")
    String id;

    @JsonProperty("activated")
    Boolean activated;

    @JsonProperty("name")
    String name;

    @JsonProperty("is_disabled")
    Boolean isDisabled;

    @JsonProperty("email")
    String email;

    @JsonProperty("email_details")
    UserEmailDetails emailDetails;

    @JsonProperty("role")
    String role;

    @JsonProperty("account_id")
    String accountId;

    @JsonProperty("opened_ip")
    String openedIp;

    @JsonProperty("is_master")
    Boolean isMaster;

    @JsonProperty("metadata")
    Map<String, Object> metadata;

    @JsonProperty("tfa_enabled")
    Boolean tfaEnabled;

    @JsonProperty("tfa_active")
    Boolean tfaActive;

    @JsonProperty("tfa_created_at")
    String tfaCreatedAt;

    @JsonProperty("password_updated_at")
    String passwordUpdatedAt;

    @JsonProperty("preferences")
    UserPreferences preferences;

    @JsonProperty("auth")
    UserAuth auth;

    @JsonProperty("github_user_id")
    String githubUserId;

    @JsonProperty("salesforce_user_id")
    String salesforceUserId;

    @JsonProperty("migration_status")
    String migrationStatus;

    /** Return the documented user role, while {@link #getRole()} preserves any unknown raw value. */
    public Optional<UserRole> getRoleEnum() {
        return EnumWithValue.fromValue(UserRole.class, role);
    }
}
