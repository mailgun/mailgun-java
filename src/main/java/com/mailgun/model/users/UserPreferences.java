package com.mailgun.model.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * User preferences on a Mailgun account.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/users">Users API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPreferences {

    @JsonProperty("time_zone")
    String timeZone;

    @JsonProperty("time_format")
    String timeFormat;

    @JsonProperty("programming_language")
    String programmingLanguage;
}
