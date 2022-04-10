package com.mailgun.model.routes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * <p>
 * Routes list response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-routes.html#routes">Routes</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoutesListResponse {

    /**
     * <p>
     * List of {@link Route}.
     * </p>
     */
    List<Route> items;

    /**
     * <p>
     * The total number of Routes.
     * </p>
     */
    @JsonProperty("total_count")
    Integer totalCount;

}
