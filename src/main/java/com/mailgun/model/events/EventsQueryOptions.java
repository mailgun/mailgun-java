package com.mailgun.model.events;

import com.mailgun.enums.EventType;
import com.mailgun.enums.Severity;
import com.mailgun.enums.YesNo;
import feign.Param;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;

/**
 * <p>
 * Events Query Options.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html#query-options">Query Options</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class EventsQueryOptions {

    /**
     * <p>
     * The beginning of the search time range in Linux epoch seconds.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html#time-range">Time Range</a>
     */
    Long begin;

    /**
     * <p>
     * The end of the search time range in Linux epoch seconds.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html#time-range">Time Range</a>
     */
    Long end;

    /**
     * <p>
     * Defines the direction of the search time range and must be provided if the range end time is not specified.
     * </p>
     * Can be either <code>yes</code> or <code>no</code>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html#time-range">Time Range</a>
     */
    String ascending;

    /**
     * <p>
     * Number of entries to return. (300 max)
     * </p>
     */
    Integer limit;

    /**
     * <p>
     * An event type.
     * </p>
     */
    String event;

    /**
     * <p>
     * A name of an attached file.
     * </p>
     */
    String attachment;

    /**
     * <p>
     * Filter Expression for the <code>from</code> field.
     * An email address mentioned in the <code>from</code> MIME header.
     * </p>
     * <p>
     * The value of the parameter should be a valid Filter Expression.
     * </p>
     * <p>
     * Examples:
     * </p>
     * <pre>
     * <code>foo bar</code>	Matches field values that contain both term foo and term bar.
     * <code>foo AND bar</code>	Same as above.
     * <code>foo OR bar</code>	Matches field values that contain either term foo or term bar.
     * <code>“foo bar”</code>	Matches field values that literally contain foo bar.
     * <code>NOT foo</code>	Matches field values that do not contain term foo.
     * </pre>
     * <p>
     * Note that more then one expression can be used as a filter value and parentheses can be used to specify grouping.
     * </p>
     * <pre>
     * E.g.: <code>(Hello AND NOT Rachel) OR (Farewell AND Monica)</code>.
     * </pre>
     */
    String from;

    /**
     * <p>
     * Filter Expression for the <code>messageId</code> field.
     * A Mailgun message id returned by the messages API.
     * </p>
     * <p>
     * The value of the parameter should be a valid Filter Expression.
     * </p>
     * <p>
     * Examples:
     * </p>
     * <pre>
     * <code>foo bar</code>	Matches field values that contain both term foo and term bar.
     * <code>foo AND bar</code>	Same as above.
     * <code>foo OR bar</code>	Matches field values that contain either term foo or term bar.
     * <code>“foo bar”</code>	Matches field values that literally contain foo bar.
     * <code>NOT foo</code>	Matches field values that do not contain term foo.
     * </pre>
     * <p>
     * Note that more then one expression can be used as a filter value and parentheses can be used to specify grouping.
     * </p>
     * <pre>
     * E.g.: <code>(Hello AND NOT Rachel) OR (Farewell AND Monica)</code>.
     * </pre>
     */
    @Param("message-id")
    String messageId;

    /**
     * <p>
     * Filter Expression for the <code>subject</code> field.
     * A subject line.
     * </p>
     * <p>
     * The value of the parameter should be a valid Filter Expression.
     * </p>
     * <p>
     * Examples:
     * </p>
     * <pre>
     * <code>foo bar</code>	Matches field values that contain both term foo and term bar.
     * <code>foo AND bar</code>	Same as above.
     * <code>foo OR bar</code>	Matches field values that contain either term foo or term bar.
     * <code>“foo bar”</code>	Matches field values that literally contain foo bar.
     * <code>NOT foo</code>	Matches field values that do not contain term foo.
     * </pre>
     * <p>
     * Note that more then one expression can be used as a filter value and parentheses can be used to specify grouping.
     * </p>
     * <pre>
     * E.g.: <code>(Hello AND NOT Rachel) OR (Farewell AND Monica)</code>.
     * </pre>
     */
    String subject;

    /**
     * <p>
     * Filter Expression for the <code>to</code> field.
     * An email address mentioned in the <code>to</code> MIME header.
     * </p>
     * <p>
     * The value of the parameter should be a valid Filter Expression.
     * </p>
     * <p>
     * Examples:
     * </p>
     * <pre>
     * <code>foo bar</code>	Matches field values that contain both term foo and term bar.
     * <code>foo AND bar</code>	Same as above.
     * <code>foo OR bar</code>	Matches field values that contain either term foo or term bar.
     * <code>“foo bar”</code>	Matches field values that literally contain foo bar.
     * <code>NOT foo</code>	Matches field values that do not contain term foo.
     * </pre>
     * <p>
     * Note that more then one expression can be used as a filter value and parentheses can be used to specify grouping.
     * </p>
     * <pre>
     * E.g.: <code>(Hello AND NOT Rachel) OR (Farewell AND Monica)</code>.
     * </pre>
     */
    String to;

    /**
     * <p>
     * Filter Expression for the <code>size</code> field.
     * Message size.
     * </p>
     * <p>
     * The value of the parameter should be a valid Filter Expression.
     * </p>
     * <p>
     * Examples:
     * </p>
     * <pre>
     * <code>10000</code> Matches values that greater then 10000.
     * <code>&gt;10000 &lt;20000</code> Matches values that are greater then 10000 and less then 20000.
     * </pre>
     */
    String size;

    /**
     * <p>
     * Filter Expression for the <code>recipient</code> field.
     * An email address of a particular recipient.
     * </p>
     * <p>
     * The value of the parameter should be a valid Filter Expression.
     * </p>
     * <p>
     * Examples:
     * </p>
     * <pre>
     * <code>foo bar</code>	Matches field values that contain both term foo and term bar.
     * <code>foo AND bar</code>	Same as above.
     * <code>foo OR bar</code>	Matches field values that contain either term foo or term bar.
     * <code>“foo bar”</code>	Matches field values that literally contain foo bar.
     * <code>NOT foo</code>	Matches field values that do not contain term foo.
     * </pre>
     * <p>
     * Note that more then one expression can be used as a filter value and parentheses can be used to specify grouping.
     * </p>
     * <pre>
     * E.g.: <code>(Hello AND NOT Rachel) OR (Farewell AND Monica)</code>.
     * </pre>
     */
    String recipient;

    /**
     * <p>
     * Filter Expression for the <code>recipients</code> field.
     * Specific to stored events, this field tracks all of the potential message recipients.
     * </p>
     * <p>
     * The value of the parameter should be a valid Filter Expression.
     * </p>
     * <p>
     * Examples:
     * </p>
     * <pre>
     * <code>foo bar</code>	Matches field values that contain both term foo and term bar.
     * <code>foo AND bar</code>	Same as above.
     * <code>foo OR bar</code>	Matches field values that contain either term foo or term bar.
     * <code>“foo bar”</code>	Matches field values that literally contain foo bar.
     * <code>NOT foo</code>	Matches field values that do not contain term foo.
     * </pre>
     * <p>
     * Note that more then one expression can be used as a filter value and parentheses can be used to specify grouping.
     * </p>
     * <pre>
     * E.g.: <code>(Hello AND NOT Rachel) OR (Farewell AND Monica)</code>.
     * </pre>
     */
    String recipients;

    /**
     * <p>
     * Filter Expression for the <code>tags</code> field.
     * User defined tags.
     * </p>
     * <p>
     * The value of the parameter should be a valid Filter Expression.
     * </p>
     * <p>
     * Examples:
     * </p>
     * <pre>
     * <code>foo bar</code>	Matches field values that contain both term foo and term bar.
     * <code>foo AND bar</code>	Same as above.
     * <code>foo OR bar</code>	Matches field values that contain either term foo or term bar.
     * <code>“foo bar”</code>	Matches field values that literally contain foo bar.
     * <code>NOT foo</code>	Matches field values that do not contain term foo.
     * </pre>
     * <p>
     * Note that more then one expression can be used as a filter value and parentheses can be used to specify grouping.
     * </p>
     * <pre>
     * E.g.: <code>(Hello AND NOT Rachel) OR (Farewell AND Monica)</code>.
     * </pre>
     */
    String tags;

    /**
     * <p>
     * Used to filter events based on severity, if exists. (Currently failed events only)
     * <code>Temporary</code> or <code>Permanent</code>.
     * </p>
     */
    String severity;

    public static class EventsQueryOptionsBuilder {

        /**
         * <p>
         * The beginning of the search time range.
         * </p>
         *
         * @param time The beginning of the search time range.
         * @return Returns a reference to this object so that method calls can be chained together.
         * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html#time-range">Time Range</a>
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder begin(ZonedDateTime time) {
            this.begin = time.toEpochSecond();
            return this;
        }

        /**
         * <p>
         * The beginning of the search time range in Linux epoch seconds.
         * </p>
         *
         * @param time The beginning of the search time range in Linux epoch seconds.
         * @return Returns a reference to this object so that method calls can be chained together.
         * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html#time-range">Time Range</a>
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder begin(Long time) {
            this.begin = time;
            return this;
        }

        /**
         * <p>
         * The end of the search time range.
         * </p>
         *
         * @param time The end of the search time range {@link ZonedDateTime}
         * @return Returns a reference to this object so that method calls can be chained together.
         * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html#time-range">Time Range</a>
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder end(ZonedDateTime time) {
            this.end = time.toEpochSecond();
            return this;
        }

        /**
         * <p>
         * The end of the search time range in Linux epoch seconds.
         * </p>
         *
         * @param time The end of the search time range in Linux epoch seconds.
         * @return Returns a reference to this object so that method calls can be chained together.
         * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html#time-range">Time Range</a>
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder end(Long time) {
            this.end = time;
            return this;
        }

        /**
         * <p>
         * Defines the direction of the search time range and must be provided if the range end time is not specified.
         * </p>
         * Can be either <code>true</code> or <code>false</code>
         *
         * @param ascending Defines the direction of the search time range and must be provided if the range end time is not specified.
         *                  Can be either <code>true</code> or <code>false</code>
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder ascending(boolean ascending) {
            this.ascending = YesNo.getValue(ascending);
            return this;
        }

        /**
         * <p>
         * An event type.
         * </p>
         *
         * @param event An event type.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder event(EventType event) {
            this.event = event.getValue();
            return this;
        }

        /**
         * <p>
         * Filter Expression for the <code>event</code> field.
         * </p>
         * <p>
         * The value of the parameter should be a valid Filter Expression.
         * </p>
         * <p>
         * Examples:
         * </p>
         * <pre>
         * <code>foo bar</code>	Matches field values that contain both term foo and term bar.
         * <code>foo AND bar</code>	Same as above.
         * <code>foo OR bar</code>	Matches field values that contain either term foo or term bar.
         * <code>“foo bar”</code>	Matches field values that literally contain foo bar.
         * <code>NOT foo</code>	Matches field values that do not contain term foo.
         * </pre>
         * <p>
         * Note that more then one expression can be used as a filter value and parentheses can be used to specify grouping.
         * </p>
         * <pre>
         * E.g.: <code>(Hello AND NOT Rachel) OR (Farewell AND Monica)</code>.
         * </pre>
         *
         * @param filterExpression Filter Expression
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder eventFieldFilterExpression(String filterExpression) {
            this.event = filterExpression;
            return this;
        }

        /**
         * <p>
         * Used to filter events based on severity, if exists. (Currently failed events only)
         * <code>Temporary</code> or <code>Permanent</code>.
         * </p>
         *
         * @param severity severity
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder severity(Severity severity) {
            this.severity = severity.getValue();
            return this;
        }

        /**
         * <p>
         * Filter Expression for the <code>attachment</code> field.
         * A name of an attached file.
         * </p>
         * <p>
         * The value of the parameter should be a valid Filter Expression.
         * </p>
         * <p>
         * Examples:
         * </p>
         * <pre>
         * <code>foo bar</code>	Matches field values that contain both term foo and term bar.
         * <code>foo AND bar</code>	Same as above.
         * <code>foo OR bar</code>	Matches field values that contain either term foo or term bar.
         * <code>“foo bar”</code>	Matches field values that literally contain foo bar.
         * <code>NOT foo</code>	Matches field values that do not contain term foo.
         * </pre>
         * <p>
         * Note that more then one expression can be used as a filter value and parentheses can be used to specify grouping.
         * </p>
         * <pre>
         * E.g.: <code>(Hello AND NOT Rachel) OR (Farewell AND Monica)</code>.
         * </pre>
         *
         * @param filterExpression Filter Expression
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder attachment(String filterExpression) {
            this.attachment = filterExpression;
            return this;
        }

        /**
         * <p>
         * Filter Expression for the <code>from</code> field.
         * An email address mentioned in the <code>from</code> MIME header.
         * </p>
         * <p>
         * The value of the parameter should be a valid Filter Expression.
         * </p>
         * <p>
         * Examples:
         * </p>
         * <pre>
         * <code>foo bar</code>	Matches field values that contain both term foo and term bar.
         * <code>foo AND bar</code>	Same as above.
         * <code>foo OR bar</code>	Matches field values that contain either term foo or term bar.
         * <code>“foo bar”</code>	Matches field values that literally contain foo bar.
         * <code>NOT foo</code>	Matches field values that do not contain term foo.
         * </pre>
         * <p>
         * Note that more then one expression can be used as a filter value and parentheses can be used to specify grouping.
         * </p>
         * <pre>
         * E.g.: <code>(Hello AND NOT Rachel) OR (Farewell AND Monica)</code>.
         * </pre>
         *
         * @param filterExpression Filter Expression
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder from(String filterExpression) {
            this.from = filterExpression;
            return this;
        }

        /**
         * <p>
         * Filter Expression for the <code>messageId</code> field.
         * A Mailgun message id returned by the messages API.
         * </p>
         * <p>
         * The value of the parameter should be a valid Filter Expression.
         * </p>
         * <p>
         * Examples:
         * </p>
         * <pre>
         * <code>foo bar</code>	Matches field values that contain both term foo and term bar.
         * <code>foo AND bar</code>	Same as above.
         * <code>foo OR bar</code>	Matches field values that contain either term foo or term bar.
         * <code>“foo bar”</code>	Matches field values that literally contain foo bar.
         * <code>NOT foo</code>	Matches field values that do not contain term foo.
         * </pre>
         * <p>
         * Note that more then one expression can be used as a filter value and parentheses can be used to specify grouping.
         * </p>
         * <pre>
         * E.g.: <code>(Hello AND NOT Rachel) OR (Farewell AND Monica)</code>.
         * </pre>
         *
         * @param filterExpression Filter Expression
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder messageId(String filterExpression) {
            this.messageId = filterExpression;
            return this;
        }

        /**
         * <p>
         * Filter Expression for the <code>subject</code> field.
         * A subject line.
         * </p>
         * <p>
         * The value of the parameter should be a valid Filter Expression.
         * </p>
         * <p>
         * Examples:
         * </p>
         * <pre>
         * <code>foo bar</code>	Matches field values that contain both term foo and term bar.
         * <code>foo AND bar</code>	Same as above.
         * <code>foo OR bar</code>	Matches field values that contain either term foo or term bar.
         * <code>“foo bar”</code>	Matches field values that literally contain foo bar.
         * <code>NOT foo</code>	Matches field values that do not contain term foo.
         * </pre>
         * <p>
         * Note that more then one expression can be used as a filter value and parentheses can be used to specify grouping.
         * </p>
         * <pre>
         * E.g.: <code>(Hello AND NOT Rachel) OR (Farewell AND Monica)</code>.
         * </pre>
         *
         * @param filterExpression Filter Expression
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder subject(String filterExpression) {
            this.subject = filterExpression;
            return this;
        }

        /**
         * <p>
         * Filter Expression for the <code>to</code> field.
         * An email address mentioned in the <code>to</code> MIME header.
         * </p>
         * <p>
         * The value of the parameter should be a valid Filter Expression.
         * </p>
         * <p>
         * Examples:
         * </p>
         * <pre>
         * <code>foo bar</code>	Matches field values that contain both term foo and term bar.
         * <code>foo AND bar</code>	Same as above.
         * <code>foo OR bar</code>	Matches field values that contain either term foo or term bar.
         * <code>“foo bar”</code>	Matches field values that literally contain foo bar.
         * <code>NOT foo</code>	Matches field values that do not contain term foo.
         * </pre>
         * <p>
         * Note that more then one expression can be used as a filter value and parentheses can be used to specify grouping.
         * </p>
         * <pre>
         * E.g.: <code>(Hello AND NOT Rachel) OR (Farewell AND Monica)</code>.
         * </pre>
         *
         * @param filterExpression Filter Expression
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder to(String filterExpression) {
            this.to = filterExpression;
            return this;
        }

        /**
         * <p>
         * Filter Expression for the <code>size</code> field.
         * Message size.
         * </p>
         * <p>
         * The value of the parameter should be a valid Filter Expression.
         * </p>
         * <p>
         * Examples:
         * </p>
         * <pre>
         * <code>10000</code> Matches values that greater then 10000.
         * <code>&gt;10000 &lt;20000</code> Matches values that are greater then 10000 and less then 20000.
         * </pre>
         *
         * @param filterExpression Filter Expression
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder size(String filterExpression) {
            this.size = filterExpression;
            return this;
        }

        /**
         * <p>
         * Filter Expression for the <code>recipient</code> field.
         * An email address of a particular recipient.
         * </p>
         * <p>
         * The value of the parameter should be a valid Filter Expression.
         * </p>
         * <p>
         * Examples:
         * </p>
         * <pre>
         * <code>foo bar</code>	Matches field values that contain both term foo and term bar.
         * <code>foo AND bar</code>	Same as above.
         * <code>foo OR bar</code>	Matches field values that contain either term foo or term bar.
         * <code>“foo bar”</code>	Matches field values that literally contain foo bar.
         * <code>NOT foo</code>	Matches field values that do not contain term foo.
         * </pre>
         * <p>
         * Note that more then one expression can be used as a filter value and parentheses can be used to specify grouping.
         * </p>
         * <pre>
         * E.g.: <code>(Hello AND NOT Rachel) OR (Farewell AND Monica)</code>.
         * </pre>
         *
         * @param filterExpression Filter Expression
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder recipient(String filterExpression) {
            this.recipient = filterExpression;
            return this;
        }

        /**
         * <p>
         * Filter Expression for the <code>recipients</code> field.
         * Specific to stored events, this field tracks all of the potential message recipients.
         * </p>
         * <p>
         * The value of the parameter should be a valid Filter Expression.
         * </p>
         * <p>
         * Examples:
         * </p>
         * <pre>
         * <code>foo bar</code>	Matches field values that contain both term foo and term bar.
         * <code>foo AND bar</code>	Same as above.
         * <code>foo OR bar</code>	Matches field values that contain either term foo or term bar.
         * <code>“foo bar”</code>	Matches field values that literally contain foo bar.
         * <code>NOT foo</code>	Matches field values that do not contain term foo.
         * </pre>
         * <p>
         * Note that more then one expression can be used as a filter value and parentheses can be used to specify grouping.
         * </p>
         * <pre>
         * E.g.: <code>(Hello AND NOT Rachel) OR (Farewell AND Monica)</code>.
         * </pre>
         *
         * @param filterExpression Filter Expression
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder recipients(String filterExpression) {
            this.recipients = filterExpression;
            return this;
        }

        /**
         * <p>
         * Filter Expression for the <code>tags</code> field.
         * User defined tags.
         * </p>
         * <p>
         * The value of the parameter should be a valid Filter Expression.
         * </p>
         * <p>
         * Examples:
         * </p>
         * <pre>
         * <code>foo bar</code>	Matches field values that contain both term foo and term bar.
         * <code>foo AND bar</code>	Same as above.
         * <code>foo OR bar</code>	Matches field values that contain either term foo or term bar.
         * <code>“foo bar”</code>	Matches field values that literally contain foo bar.
         * <code>NOT foo</code>	Matches field values that do not contain term foo.
         * </pre>
         * <p>
         * Note that more then one expression can be used as a filter value and parentheses can be used to specify grouping.
         * </p>
         * <pre>
         * E.g.: <code>(Hello AND NOT Rachel) OR (Farewell AND Monica)</code>.
         * </pre>
         *
         * @param filterExpression Filter Expression
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public EventsQueryOptions.EventsQueryOptionsBuilder tags(String filterExpression) {
            this.tags = filterExpression;
            return this;
        }

    }

}
