package com.mailgun.model.suppression.complaints;

import com.mailgun.util.DateTimeUtil;
import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;

/**
 * <p>
 * Complaints single item request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#complaints">Suppressions/Complaints</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ComplaintsSingleItemRequest {

    /**
     * <p>
     * Valid email address.
     * </p>
     */
    String address;

    /**
     * <p>
     * Timestamp of an complaints event (optional, default: current time).
     * </p>
     */
    @FormProperty("created_at")
    String createdAt;

    public static class ComplaintsSingleItemRequestBuilder {

        /**
         * <p>
         * Timestamp of an complaints event (optional, default: current time).
         * </p>
         *
         * @param zonedDateTime {@link ZonedDateTime}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public ComplaintsSingleItemRequest.ComplaintsSingleItemRequestBuilder createdAt(ZonedDateTime zonedDateTime) {
            this.createdAt = DateTimeUtil.toStringNameTimeZone(zonedDateTime);
            return this;
        }
    }

}
