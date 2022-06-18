package com.mailgun.api.v3.suppression;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.suppression.whitelists.WhitelistsItem;
import com.mailgun.model.suppression.whitelists.WhitelistsItemResponse;
import com.mailgun.model.suppression.whitelists.WhitelistsListImportRequest;
import com.mailgun.model.suppression.whitelists.WhitelistsRemoveRecordResponse;
import com.mailgun.model.suppression.whitelists.WhitelistsRequest;
import feign.Headers;
import feign.Param;
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
 * The whitelist API provides the ability to whitelist specific addresses from being added to bounce list.
 * You can whitelist by domain name (i.e example.com) or by specific address (i.e. alice@example.com).
 * Mailgun doesn't add an address to the bounce list if the address is whitelisted.
 * This API is very useful if you test against your private services and don’t want to constantly clean up bounce lists.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#whitelists">Suppressions/Whitelists</a>
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
     * <code>address</code> Valid email address if you would like to whitelist email address
     * <code>domain</code> Valid domain name if you would like whitelist entire domain name
     * </pre>
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
     * Import a CSV file containing a list of addresses and/or domains to add to the whitelist.
     * </p>
     * <p>
     * CSV file must be 25MB or under and must contain the following column headers:
     * </p>
     * <pre>
     * <code>address</code> Valid email address if you would like to whitelist email address
     * <code>domain</code> Valid domain name if you would like whitelist entire domain name
     * </pre>
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
     * @return {@link WhitelistsRemoveRecordResponse}
     */
    @RequestLine("DELETE /{domain}/whitelists/{addressOrDomain}")
    Response removeRecordFromWhitelistsFeignResponse(@Param("domain") String domain, @Param("addressOrDomain") String addressOrDomain);

}
