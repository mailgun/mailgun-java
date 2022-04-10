package com.mailgun.model;

import com.mailgun.enums.Duration;
import com.mailgun.enums.ResolutionPeriod;
import com.mailgun.enums.StatsEventType;
import com.mailgun.util.CollectionUtil;
import com.mailgun.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.apache.commons.lang3.Validate;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mailgun.util.Constants.DURATION_MUST_BE_GREATER_THAN_ZERO;

/**
 * <p>
 * Mailgun tracks all of the events that occur throughout the system.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#stats">Stats</a>
 */
@Value
@Jacksonized
@Builder
@AllArgsConstructor
public class StatisticsOptions {

    /**
     * <p>
     * (Required) Event type(s).
     * </p>
     * {@link StatsEventType}
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#event-types">Event Types</a>
     */
    @NonNull
    Set<String> event;

    /**
     * <p>
     * The starting time.
     * </p>
     * Default: 7 days from the current time.
     */
    String start;

    /**
     * <p>
     * The ending date.
     * </p>
     * Default: current time.
     */
    String end;

    /**
     * <p>
     * Resolution
     * </p>
     * {@link ResolutionPeriod}
     * <p>
     * Can be either <code>HOUR</code>, <code>DAY</code> or <code>MONTH</code>
     * </p>
     * Default: <code>DAY</code>
     */
    String resolution;

    /**
     * <p>
     * Period of time with resolution encoded. If provided, overwrites the start date.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#duration">Duration</a>
     */
    String duration;

    public static class StatisticsOptionsBuilder {

        /**
         * <p>
         * Event type.
         * </p>
         *
         * @param event {@link StatsEventType}
         * @return Returns a reference to this object so that method calls can be chained together.
         * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#event-types">Event Types</a>
         */
        public StatisticsOptions.StatisticsOptionsBuilder event(StatsEventType event) {
            this.event = CollectionUtil.addToSet(this.event, event.getValue());
            return this;
        }

        /**
         * <p>
         * Event types.
         * </p>
         *
         * @param events list of {@link StatsEventType}
         * @return Returns a reference to this object so that method calls can be chained together.
         * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#event-types">Event Types</a>
         */
        public StatisticsOptions.StatisticsOptionsBuilder event(List<StatsEventType> events) {
            this.event = CollectionUtil.addToSet(this.event, events.stream()
                    .map(StatsEventType::getValue)
                    .collect(Collectors.toList()));
            return this;
        }

        /**
         * <p>
         * The starting time.
         * </p>
         * Default: 7 days from the current time.
         *
         * @param zonedDateTime {@link ZonedDateTime}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public StatisticsOptions.StatisticsOptionsBuilder start(ZonedDateTime zonedDateTime) {
            this.start = DateTimeUtil.toStringNumericTimeZone(zonedDateTime);
            return this;
        }

        /**
         * <p>
         * The ending date.
         * </p>
         * Default: current time.
         *
         * @param zonedDateTime {@link ZonedDateTime}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public StatisticsOptions.StatisticsOptionsBuilder end(ZonedDateTime zonedDateTime) {
            this.end = DateTimeUtil.toStringNumericTimeZone(zonedDateTime);
            return this;
        }

        /**
         * <p>
         * Resolution period.
         * </p>
         * <p>
         * Can be either <code>HOUR</code>, <code>DAY</code> or <code>MONTH</code>
         * </p>
         * Default: <code>DAY</code>
         *
         * @param resolutionPeriod {@link ResolutionPeriod}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public StatisticsOptions.StatisticsOptionsBuilder resolution(ResolutionPeriod resolutionPeriod) {
            this.resolution = resolutionPeriod.getValue();
            return this;
        }

        /**
         * <p>
         * Period of time with resolution encoded. If provided, overwrites the start date.
         * </p>
         *
         * @param timeValue time value
         * @param duration {@link Duration}
         * @return Returns a reference to this object so that method calls can be chained together.
         * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#duration">Duration</a>
         */
        public StatisticsOptions.StatisticsOptionsBuilder duration(int timeValue, Duration duration) {
            Validate.isTrue(timeValue > 0, DURATION_MUST_BE_GREATER_THAN_ZERO, timeValue);
            String durationValue = duration.getValue();
            this.duration = timeValue + durationValue;
            return this;
        }
    }

}
