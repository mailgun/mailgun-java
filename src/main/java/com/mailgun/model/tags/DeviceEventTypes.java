package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Device event types.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-tags.html#tags">Tags</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceEventTypes {

    /**
     * <p>
     * Clicked.
     * </p>
     */
    Integer clicked;

    /**
     * <p>
     * Complained.
     * </p>
     */
    Integer complained;

    /**
     * <p>
     * Opened.
     * </p>
     */
    Integer opened;

    /**
     * <p>
     * Unique clicked.
     * </p>
     */
    @JsonProperty("unique_clicked")
    Integer uniqueClicked;

    /**
     * <p>
     * Unique opened.
     * </p>
     */
    @JsonProperty("unique_opened")
    Integer uniqueOpened;

    /**
     * <p>
     * Unsubscribed.
     * </p>
     */
    Integer unsubscribed;

}
