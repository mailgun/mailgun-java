package com.mailgun.model.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Email details for a Mailgun user.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/users">Users API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEmailDetails {

    @JsonProperty("address")
    String address;

    @JsonProperty("is_valid")
    Boolean isValid;

    @JsonProperty("reason")
    String reason;

    @JsonProperty("parts")
    UserEmailParts parts;
}
