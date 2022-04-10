package com.mailgun.model.mailing.lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Jacksonized
@Getter
@ToString
@EqualsAndHashCode
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailingListMember {

    /**
     * <p>
     * An email address, e.g. {@code "Alice <alice@example.com>"} or just {@code "alice@example.com" }.
     * </p>
     */
    String address;

    /**
     * <p>
     * Optional member name.
     * </p>
     */
    String name;

    /**
     * <p>
     * A map with arbitrary keys/values that turns into a JSON dictionary, e.g. {@code {"sex":"female","age":27} }.
     * </p>
     */
    Map<String, Object> vars;

    /**
     * <p>
     * <code>false</code> to set unsubscribed.
     * <p>
     * </p>
     * <code>true</code> as subscribed.
     * </p>
     */
    Boolean subscribed;

}
