package com.mailgun.api.v3.suppression;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.suppression.whitelists.WhitelistsItem;
import com.mailgun.model.suppression.whitelists.WhitelistsItemResponse;
import com.mailgun.model.suppression.whitelists.WhitelistsListImportRequest;
import com.mailgun.model.suppression.whitelists.WhitelistsListQuery;
import com.mailgun.model.suppression.whitelists.WhitelistsRemoveRecordResponse;
import com.mailgun.model.suppression.whitelists.WhitelistsRequest;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * <p>
 * Mailgun Suppression Whitelists Api
 * </p>
 *
 * <p>
 * Mailgun keeps three lists of addresses it blocks the delivery to: bounces, unsubscribes and complaints.
 * These lists are populated automatically as Mailgun detects undeliverable addresses you try to send to
 * and as recipients unsubscribe from your mailings or mark your emails as a spam (for ESPs that provide FBL).
 * You can also add/remove addresses from any of these lists using the API.
 * </p>
 * <p>
 * It’s important to note that these suppression lists are unique to a sending domain and are not an account level (global) suppression list.
 * If you want to add/remove the same address(es) from multiple domains, you’ll need to do so for each domain.
 * </p>
 *
 * <p>
 * The allowlist API ({@code /v3/.../whitelists}) prevents matching addresses or whole domains from being added to the
 * bounce list. You can allowlist by domain (e.g. {@code example.com}) or by address (e.g. {@code alice@example.com}).
 * Useful when testing against private services so bounce lists do not fill with expected failures.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#whitelists">Suppressions/Whitelists</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/allowlist/get-v3--domainid--whitelists.md">List allowlist records</a>
 */
@Headers("Accept: application/json")
public interface MailgunSuppressionWhitelistsApi extends MailgunApi {

    /**
     * <p>
     * Returns a list of whitelists for a domain (limit to 100 entries).
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link WhitelistsItemResponse}
     */
    @RequestLine("GET /{domain}/whitelists")
    WhitelistsItemResponse getAllWhitelists(@Param("domain") String domain);

    /**
     * <p>
     * Returns a list of whitelists for a domain (limit to 100 entries).
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/whitelists")
    Response getAllWhitelistsFeignResponse(@Param("domain") String domain);

    /**
     * <p>
     * Returns a list of whitelists for a domain.
     * </p>
     *
     * @param domain Name of the domain
     * @param limit  Number of entries to return, max: 10000.
     * @return {@link WhitelistsItemResponse}
     */
    @RequestLine("GET /{domain}/whitelists?limit={limit}")
    WhitelistsItemResponse getAllWhitelists(@Param("domain") String domain, @Param("limit") Integer limit);

    /**
     * <p>
     * Returns a list of whitelists for a domain.
     * </p>
     *
     * @param domain Name of the domain
     * @param limit  Number of entries to return, max: 10000.
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/whitelists?limit={limit}")
    Response getAllWhitelistsFeignResponse(@Param("domain") String domain, @Param("limit") Integer limit);

    /**
     * <p>
     * Paginated allowlist for a domain ({@code limit}, {@code page}, {@code address}, {@code term}).
     * </p>
     *
     * @param domain Name of the domain
     * @param query  optional pagination and filter parameters
     * @return {@link WhitelistsItemResponse}
     */
    @RequestLine("GET /{domain}/whitelists")
    WhitelistsItemResponse getAllWhitelists(@Param("domain") String domain, @QueryMap WhitelistsListQuery query);

    /**
     * <p>
     * Paginated allowlist for a domain (raw response).
     * </p>
     *
     * @param domain Name of the domain
     * @param query  optional pagination and filter parameters
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/whitelists")
    Response getAllWhitelistsFeignResponse(@Param("domain") String domain, @QueryMap WhitelistsListQuery query);

    /**
     * <p>
     * Fetch a single whitelist record.
     * Can be used to check if a given address or domain is present in the whitelist table.
     * </p>
     *
     * @param domain          Name of the domain
     * @param addressOrDomain address or domain
     * @return {@link WhitelistsItem}
     */
    @RequestLine("GET /{domain}/whitelists/{addressOrDomain}")
    WhitelistsItem getSingleWhitelistRecord(@Param("domain") String domain, @Param("addressOrDomain") String addressOrDomain);

    /**
     * <p>
     * Fetch a single whitelist record.
     * Can be used to check if a given address or domain is present in the whitelist table.
     * </p>
     *
     * @param domain          Name of the domain
     * @param addressOrDomain address or domain
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/whitelists/{addressOrDomain}")
    Response getSingleWhitelistRecordFeignResponse(@Param("domain") String domain, @Param("addressOrDomain") String addressOrDomain);

    /**
     * <p>
     * Add an address or domain to the whitelist table.
     * </p>
     * <p>
     * Note: The single request accepts either one address or domain parameter.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link WhitelistsRequest}
     * @return {@link ResponseWithMessage}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/whitelists")
    ResponseWithMessage addSingleWhitelistRecord(@Param("domain") String domain, WhitelistsRequest request);

    /**
     * <p>
     * Add an address or domain to the whitelist table.
     * </p>
     * <p>
     * Note: The single request accepts either one address or domain parameter.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link WhitelistsRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/whitelists")
    Response addSingleWhitelistRecordFeignResponse(@Param("domain") String domain, WhitelistsRequest request);

    /**
     * <p>
     * Import a CSV file containing a list of addresses and/or domains to add to the whitelist.
     * </p>
     * <p>
     * CSV file must be 25MB or under and must contain the following column headers:
     * </p>
     * <pre>
     * <code>address</code> Email address to allowlist (per row: provide either {@code address} or {@code domain}, not both)
     * <code>domain</code> Domain to allowlist (leave the other column blank on each row)
     * </pre>
     * <p>
     * CSV must be 25MB or smaller.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link WhitelistsListImportRequest}
     * @return {@link ResponseWithMessage}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/whitelists/import")
    ResponseWithMessage importWhitelistRecords(@Param("domain") String domain, WhitelistsListImportRequest request);

    /**
     * <p>
     * Import a CSV file containing addresses and/or domains to add to the allowlist.
     * </p>
     * <p>
     * CSV file must be 25MB or under and must contain the following column headers:
     * </p>
     * <pre>
     * <code>address</code> Email address to allowlist (per row: either {@code address} or {@code domain}, not both)
     * <code>domain</code> Domain to allowlist (leave the other column blank on each row)
     * </pre>
     * <p>
     * CSV must be 25MB or smaller.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link WhitelistsListImportRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/whitelists/import")
    Response importWhitelistRecordsFeignResponse(@Param("domain") String domain, WhitelistsListImportRequest request);

    /**
     * <p>
     * Delete a single record from whitelist table.
     * </p>
     *
     * @param domain          Name of the domain
     * @param addressOrDomain address or domain
     * @return {@link WhitelistsRemoveRecordResponse}
     */
    @RequestLine("DELETE /{domain}/whitelists/{addressOrDomain}")
    WhitelistsRemoveRecordResponse removeRecordFromWhitelists(@Param("domain") String domain, @Param("addressOrDomain") String addressOrDomain);

    /**
     * <p>
     * Delete a single record from whitelist table.
     * </p>
     *
     * @param domain          Name of the domain
     * @param addressOrDomain address or domain
     * @return {@link Response}
     */
    @RequestLine("DELETE /{domain}/whitelists/{addressOrDomain}")
    Response removeRecordFromWhitelistsFeignResponse(@Param("domain") String domain, @Param("addressOrDomain") String addressOrDomain);

    /**
     * <p>
     * Clear the entire allowlist for the domain ({@code DELETE /v3/{domain}/whitelists}).
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/allowlist/delete-v3--domainid--whitelists.md">Clear allowlist</a>
     */
    @RequestLine("DELETE /{domain}/whitelists")
    ResponseWithMessage clearAllowlist(@Param("domain") String domain);

    /**
     * <p>
     * Clear the entire allowlist for the domain (raw response).
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link Response}
     */
    @RequestLine("DELETE /{domain}/whitelists")
    Response clearAllowlistFeignResponse(@Param("domain") String domain);

}
