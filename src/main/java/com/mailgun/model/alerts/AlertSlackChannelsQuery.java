package com.mailgun.model.alerts;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for {@code GET /v1/alerts/slack/channels}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/get-v1-alerts-slack-channels.md">List Slack channels</a>
 */
@Value
@Jacksonized
@Builder
public class AlertSlackChannelsQuery {

    /**
     * Encoded paging token from {@code paging.next} or similar.
     */
    String page;

    /**
     * Maximum number of channels to return.
     */
    Integer limit;

}
