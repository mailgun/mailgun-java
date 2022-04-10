package com.mailgun.model.suppression.bounces;

import com.mailgun.util.DateTimeUtil;
import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

/**
 * <p>
 * Bounces request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#bounces">Suppressions/Bounces</a>
 */
@Jacksonized
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class BouncesRequest {

    /**
     * <p>
     * Valid email address.
     * </p>
     */
    String address;

    /**
     * <p>
     * Error code (optional, default: 550).
     * </p>
     */
    String code;

    /**
     * <p>
     * Error description (optional, default: empty string).
     * </p>
     */
    String error;

    /**
     * <p>
     * Timestamp of a bounce event (optional, default: current time).
     * </p>
     */
    @FormProperty("created_at")
    String createdAt;

    public static class BouncesRequestBuilder {

        /**
         * <p>
         * Timestamp of a bounce event (optional, default: current time).
         * </p>
         * {@link ZonedDateTime}
         *
         * @param zonedDateTime {@link ZonedDateTime}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public BouncesRequest.BouncesRequestBuilder createdAt(ZonedDateTime zonedDateTime) {
            this.createdAt = DateTimeUtil.toString(zonedDateTime);
            return this;
        }
    }

}
