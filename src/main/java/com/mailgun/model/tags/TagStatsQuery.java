package com.mailgun.model.tags;

import com.mailgun.enums.ResolutionPeriod;
import com.mailgun.enums.StatsEventType;
import com.mailgun.util.CollectionUtil;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Query parameters for {@code GET /v3/{domain}/tag/stats} (retrieve stats by tag).
 * {@code tag} and {@code event} are required.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags/get-v3--domain--tag-stats.md">Get stats by tag</a>
 * @deprecated Use the new Tags API instead.
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class TagStatsQuery {

    /**
     * (Required) The name of the tag.
     */
    @NonNull
    String tag;

    /**
     * (Required) Event type(s) to include.
     * Supported: accepted, delivered, failed, opened, clicked, unsubscribed, complained, stored.
     */
    @NonNull
    Set<String> event;

    /**
     * Start date in RFC 2822 format or unix epoch. Default: 7 days ago.
     */
    String start;

    /**
     * End date in RFC 2822 format or unix epoch. Default: current time.
     */
    String end;

    /**
     * Resolution: 'day', 'month', or 'hour'. Default: day.
     */
    String resolution;

    /**
     * Duration from end date, overwrites start. e.g. '3d', '2h'.
     */
    String duration;

    /**
     * Filter results by email service provider.
     */
    String provider;

    /**
     * Filter results by device type.
     */
    String device;

    /**
     * Filter results by country.
     */
    String country;

    public static class TagStatsQueryBuilder {

        /**
         * Adds a single event type.
         *
         * @param statsEventType {@link StatsEventType}
         * @return this builder
         */
        public TagStatsQueryBuilder event(StatsEventType statsEventType) {
            this.event = CollectionUtil.addToSet(this.event, statsEventType.getValue());
            return this;
        }

        /**
         * Adds multiple event types.
         *
         * @param statsEventTypes list of {@link StatsEventType}
         * @return this builder
         */
        public TagStatsQueryBuilder event(List<StatsEventType> statsEventTypes) {
            this.event = CollectionUtil.addToSet(this.event, statsEventTypes.stream()
                    .map(StatsEventType::getValue)
                    .collect(Collectors.toList()));
            return this;
        }

        /**
         * Sets resolution using the {@link ResolutionPeriod} enum.
         *
         * @param resolutionPeriod {@link ResolutionPeriod}
         * @return this builder
         */
        public TagStatsQueryBuilder resolution(ResolutionPeriod resolutionPeriod) {
            this.resolution = resolutionPeriod.getValue();
            return this;
        }
    }
}
