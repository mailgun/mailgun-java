package com.mailgun.model.alerts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * A Slack channel in the workspace connected for Mailgun Alerts.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/get-v1-alerts-slack-channels.md">List Slack channels</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/get-v1-alerts-slack-channels--id-.md">Get Slack channel</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlertSlackChannel {

    String id;

    String name;

    @JsonProperty("is_archived")
    boolean archived;

}
