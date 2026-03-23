package com.mailgun.model.stats;

import com.mailgun.enums.ResolutionPeriod;
import com.mailgun.enums.StatsEventType;
import com.mailgun.util.CollectionUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Query parameters for {@code GET /v3/stats/total/domains}.
 * Gets stat totals for domains in an account for a single time resolution.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/stats/get-v3-stats-total-domains.md">Totals for account domains for a single time resolution</a>
 */
@Value
@Jacksonized
@Builder
@AllArgsConstructor
public class StatsTotalDomainsOptions {

    /**
     * (Required) Event type(s) to receive stats for.
     * Supported: accepted, delivered, failed, opened, clicked, unsubscribed, complained, stored.
     */
    @NonNull
    Set<String> event;

    /**
     * Number of domains to skip; used for paging through large numbers of domains.
     */
    Integer limit;

    /**
     * Gregorian resolution: 'day', 'month', or 'hour'. Default: day.
     */
    String resolution;

    /**
     * (Required) The date/time of the resolution being retrieved (RFC 2822 or unix epoch).
     */
    @NonNull
    String timestamp;

    public static class StatsTotalDomainsOptionsBuilder {

        /**
         * Adds a single event type to the filter.
         *
         * @param event {@link StatsEventType}
         * @return this builder
         */
        public StatsTotalDomainsOptionsBuilder event(StatsEventType event) {
            this.event = CollectionUtil.addToSet(this.event, event.getValue());
            return this;
        }

        /**
         * Adds multiple event types to the filter.
         *
         * @param events list of {@link StatsEventType}
         * @return this builder
         */
        public StatsTotalDomainsOptionsBuilder event(List<StatsEventType> events) {
            this.event = CollectionUtil.addToSet(this.event, events.stream()
                    .map(StatsEventType::getValue)
                    .collect(Collectors.toList()));
            return this;
        }

        /**
         * Sets the resolution using the {@link ResolutionPeriod} enum.
         *
         * @param resolutionPeriod {@link ResolutionPeriod}
         * @return this builder
         */
        public StatsTotalDomainsOptionsBuilder resolution(ResolutionPeriod resolutionPeriod) {
            this.resolution = resolutionPeriod.getValue();
            return this;
        }
    }
}
