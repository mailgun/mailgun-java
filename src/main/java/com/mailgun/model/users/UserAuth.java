package com.mailgun.model.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

/**
 * User authentication details.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/users">Users API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAuth {

    @JsonProperty("method")
    String method;

    @JsonProperty("prior_method")
    String priorMethod;

    @JsonProperty("prior_details")
    Map<String, Object> priorDetails;
}
