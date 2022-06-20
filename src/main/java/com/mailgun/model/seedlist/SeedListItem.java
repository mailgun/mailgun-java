package com.mailgun.model.seedlist;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Seed Lists Item.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-inbox-placement.html">Seed Lists</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeedListItem {

    /**
     * <p>
     * Unique identifier for a seed list.
     * </p>
     */
    String kid;

    /**
     * <p>
     * Date and time that seed list was created.
     * </p>
     * {@link ZonedDateTime}
     */
    @JsonProperty("created_at")
    ZonedDateTime createdAt;

    /**
     * <p>
     * Date and time that seed list was updated.
     * Will update whenever it is changed.
     * </p>
     * {@link ZonedDateTime}
     */
    @JsonProperty("updated_at")
    ZonedDateTime updatedAt;

    /**
     * <p>
     * Date and time that seed list was updated. Will update whenever a new result comes in.
     * </p>
     * {@link ZonedDateTime}
     */
    @JsonProperty("last_result_at")
    ZonedDateTime lastResultAt;

    /**
     * <p>
     * The required email address that must be included in a mailing list for an inbox placement test to work.
     * </p>
     */
    @JsonProperty("target_email")
    String targetEmail;

    /**
     * <p>
     * The list of possible domains that the messages must come from.
     * </p>
     */
    @JsonProperty("sending_domains")
    List<String> sendingDomains;

    /**
     * <p>
     * A flag that is true when results exist for this seed list.
     * </p>
     */
    @JsonProperty("has_results")
    Boolean hasResults;

    /**
     * <p>
     * The name of the seed list.
     * </p>
     */
    String name;

    /**
     * <p>
     * A regular expression value that will be used to filter the list of seeds in the seed list.
     * </p>
     */
    @JsonProperty("seed_filter")
    String seedFilter;

    /**
     * <p>
     * A mailing list that contains the target email, and available seeds.
     * </p>
     */
    @JsonProperty("mailing_list")
    String mailingList;

    /**
     * <p>
     * Tags.
     * </p>
     */
    Object tags;

    /**
     * <p>
     * An object that contains sub-objects that describe delivery stats.
     * </p>
     */
    @JsonProperty("delivery_stats")
    Map<String, Map<String, Object>> deliveryStats;

    /**
     * <p>
     * A list of results from the seed list’s tests {@link SeedListItemResult}.
     * </p>
     */
    List<SeedListItemResult> results;

}
