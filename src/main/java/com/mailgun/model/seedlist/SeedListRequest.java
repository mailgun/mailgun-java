package com.mailgun.model.seedlist;

import java.util.List;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * Seed List Request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-inbox-placement.html#generate-a-seed-list">Generate Seed Lists</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class SeedListRequest {

    /**
     * <p>
     * The sending domains that messages will come from.
     * You may specify this multiple times.
     * At least one is required.
     * </p>
     */
    @FormProperty("sending_domains")
    List<String> sendingDomains;

    /**
     * <p>
     * The name that you would like to use for this seed list.
     * </p>
     */
    String name;

    /**
     * <p>
     * A regular expression that will be applied to addresses in the mailing list.
     * </p>
     */
    @FormProperty("seed_filter")
    String seedFilter;

}
