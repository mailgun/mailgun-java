package com.mailgun.model.alerts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.Map;

/**
 * Channel-specific settings for an alert (webhook URL, email recipients, or Slack channel IDs).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/post-v1-alerts-settings-events.md">Add Alert</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlertChannelSettings {

    /**
     * Webhook URL when channel is {@link com.mailgun.enums.AlertsChannel#WEBHOOK}.
     */
    String url;

    /**
     * Email addresses when channel is {@link com.mailgun.enums.AlertsChannel#EMAIL}.
     */
    List<String> emails;

    /**
     * Slack channel IDs when channel is {@link com.mailgun.enums.AlertsChannel#SLACK}.
     */
    @JsonProperty("channel_ids")
    List<String> channelIds;

    /**
     * Per-channel Slack disable map; shape is API-defined.
     */
    @JsonProperty("disabled_channel_ids")
    Map<String, Object> disabledChannelIds;

}
