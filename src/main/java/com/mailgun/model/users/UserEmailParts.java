package com.mailgun.model.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Email address parts for a Mailgun user.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/users">Users API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEmailParts {

    @JsonProperty("domain")
    String domain;

    @JsonProperty("local_part")
    String localPart;

    @JsonProperty("display_name")
    String displayName;
}
