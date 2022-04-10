package com.mailgun.model.suppression.unsubscribe;

import com.mailgun.util.DateTimeUtil;
import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;

/**
 * <p>
 * Unsubscribe single item request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#add-a-single-unsubscribe">Suppressions/Add a single unsubscribe</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class UnsubscribeSingleItemRequest {

    /**
     * <p>
     * Valid email address.
     * </p>
     */
    String address;

    /**
     * <p>
     * Name of the tag.
     * </p>
     * <p>
     * Tag to unsubscribe from, use * to unsubscribe an address from all domain’s correspondence (optional, default: *).
     * </p>
     */
    String tag;

    /**
     * <p>
     * Timestamp of an unsubscribe event (optional, default: current time).
     * </p>
     */
    @FormProperty("created_at")
    String createdAt;

    public static class UnsubscribeSingleItemRequestBuilder {

        /**
         * <p>
         * Timestamp of an unsubscribe event (optional, default: current time).
         * </p>
         *
         * @param zonedDateTime {@link ZonedDateTime}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public UnsubscribeSingleItemRequest.UnsubscribeSingleItemRequestBuilder createdAt(ZonedDateTime zonedDateTime) {
            this.createdAt = DateTimeUtil.toStringNameTimeZone(zonedDateTime);
            return this;
        }
    }

}
