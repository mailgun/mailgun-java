package com.mailgun.api.v3.suppression;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.suppression.SuppressionResponse;
import com.mailgun.model.suppression.unsubscribe.UnsubscribeItem;
import com.mailgun.model.suppression.unsubscribe.UnsubscribeItemResponse;
import com.mailgun.model.suppression.unsubscribe.UnsubscribeSingleItemRequest;
import com.mailgun.model.suppression.unsubscribe.UnsubscribesListImportRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

import java.util.List;

/**
 * <p>
 * Mailgun Suppression Unsubscribe Api
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
 * Unsubscribe list stores email addresses of recipients who unsubscribed from your mailings by clicking a Mailgun generated unsubscribe link.
 * </p>
 *
 * <p>
 * Mailgun allows you to quickly add “Unsubscribe me” feature to your outgoing emails without any programming on your end.
 * You can enable this in your Control Panel under your domain settings
 * </p>
 *
 * <p>
 * Mailgun can notify your application every time a user unsubscribes via an
 * <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-unsubscribes">unsubscribed webhook</a>
 * </p>.
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#unsubscribes">Suppressions/Unsubscribe</a>
 */
@Headers("Accept: application/json")
public interface MailgunSuppressionUnsubscribeApi extends MailgunApi {

    /**
     * <p>
     * Returns a list of unsubscribes for a domain (limit to 100 entries).
     * </p>
     * <p>
     * Note: Via this API method unsubscribes are returned in the alphabetical order.
     * If you wish to poll for the recently occurred unsubscribes, please consider using the {@link com.mailgun.api.v3.MailgunEventsApi}.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link UnsubscribeItemResponse}
     */
    @RequestLine("GET /{domain}/unsubscribes")
    UnsubscribeItemResponse getAllUnsubscribe(@Param("domain") String domain);

    /**
     * <p>
     * Returns a list of unsubscribes for a domain (limit to 100 entries).
     * </p>
     * <p>
     * Note: Via this API method unsubscribes are returned in the alphabetical order.
     * If you wish to poll for the recently occurred unsubscribes, please consider using the {@link com.mailgun.api.v3.MailgunEventsApi}.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/unsubscribes")
    Response getAllUnsubscribeFeignResponse(@Param("domain") String domain);

    /**
     * <p>
     * Returns a list of unsubscribes for a domain.
     * </p>
     * <p>
     * Note: Via this API method unsubscribes are returned in the alphabetical order.
     * If you wish to poll for the recently occurred unsubscribes, please consider using the {@link com.mailgun.api.v3.MailgunEventsApi}.
     * </p>
     *
     * @param domain Name of the domain
     * @param limit  Number of entries to return, max: 10000.
     * @return {@link UnsubscribeItemResponse}
     */
    @RequestLine("GET /{domain}/unsubscribes?limit={limit}")
    UnsubscribeItemResponse getAllUnsubscribe(@Param("domain") String domain, @Param("limit") Integer limit);

    /**
     * <p>
     * Returns a list of unsubscribes for a domain.
     * </p>
     * <p>
     * Note: Via this API method unsubscribes are returned in the alphabetical order.
     * If you wish to poll for the recently occurred unsubscribes, please consider using the {@link com.mailgun.api.v3.MailgunEventsApi}.
     * </p>
     *
     * @param domain Name of the domain
     * @param limit  Number of entries to return, max: 10000.
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/unsubscribes?limit={limit}")
    Response getAllUnsubscribeFeignResponse(@Param("domain") String domain, @Param("limit") Integer limit);

    /**
     * <p>
     * Fetch a single unsubscribe record. Can be used to check if a given address is present in the list of unsubscribed users.
     * </p>
     *
     * @param domain  Name of the domain
     * @param address An email address
     * @return {@link UnsubscribeItem}
     */
    @RequestLine("GET /{domain}/unsubscribes/{address}")
    UnsubscribeItem getSingleUnsubscribe(@Param("domain") String domain, @Param("address") String address);

    /**
     * <p>
     * Fetch a single unsubscribe record. Can be used to check if a given address is present in the list of unsubscribed users.
     * </p>
     *
     * @param domain  Name of the domain
     * @param address An email address
     * @return {@link Response  }
     */
    @RequestLine("GET /{domain}/unsubscribes/{address}")
    Response getSingleUnsubscribeFeignResponse(@Param("domain") String domain, @Param("address") String address);

    /**
     * <p>
     * Add an address to the unsubscribe table.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link UnsubscribeSingleItemRequest}
     * @return {@link SuppressionResponse}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/unsubscribes")
    SuppressionResponse addAddressToUnsubscribeTable(@Param("domain") String domain, UnsubscribeSingleItemRequest request);

    /**
     * <p>
     * Add an address to the unsubscribe table.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link UnsubscribeSingleItemRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/unsubscribes")
    Response addAddressToUnsubscribeTableFeignResponse(@Param("domain") String domain, UnsubscribeSingleItemRequest request);

    /**
     * <p>
     * Add multiple unsubscribe records to the unsubscribe list in a single API call(up to 1000 unsubscribe records).
     * </p>
     *
     * @param domain  Name of the domain
     * @param request list of {@link UnsubscribeItem}
     * @return {@link ResponseWithMessage}
     */
    @Headers("Content-Type: application/json")
    @RequestLine("POST /{domain}/unsubscribes")
    ResponseWithMessage addAddressesToUnsubscribeTable(@Param("domain") String domain, List<UnsubscribeItem> request);

    /**
     * <p>
     * Add multiple unsubscribe records to the unsubscribe list in a single API call(up to 1000 unsubscribe records).
     * </p>
     *
     * @param domain  Name of the domain
     * @param request list of {@link UnsubscribeItem}
     * @return {@link Response}
     */
    @Headers("Content-Type: application/json")
    @RequestLine("POST /{domain}/unsubscribes")
    Response addAddressesToUnsubscribeTableFeignResponse(@Param("domain") String domain, List<UnsubscribeItem> request);

    /**
     * <p>
     * Import a CSV file containing a list of addresses to add to the unsubscribe list.
     * </p>
     * <p>
     * CSV file must be 25MB or under and must contain the following column headers:
     * </p>
     * <pre>
     * <code>address</code> Valid email address
     * <code>tags</code> Tag to unsubscribe from, use * to unsubscribe an address from all domain’s correspondence (optional, default: *)
     * <code>created_at</code> Timestamp of a bounce event in RFC2822 format (optional, default: current time)
     * </pre>
     *
     * @param domain  Name of the domain
     * @param request list of {@link UnsubscribesListImportRequest}
     * @return {@link ResponseWithMessage}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/unsubscribes/import")
    ResponseWithMessage importAddressesToUnsubscribeTable(@Param("domain") String domain, UnsubscribesListImportRequest request);

    /**
     * <p>
     * Import a CSV file containing a list of addresses to add to the unsubscribe list.
     * </p>
     * <p>
     * CSV file must be 25MB or under and must contain the following column headers:
     * </p>
     * <pre>
     * <code>address</code> Valid email address
     * <code>tags</code> Tag to unsubscribe from, use * to unsubscribe an address from all domain’s correspondence (optional, default: *)
     * <code>created_at</code> Timestamp of a bounce event in RFC2822 format (optional, default: current time)
     * </pre>
     *
     * @param domain  Name of the domain
     * @param request list of {@link UnsubscribesListImportRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/unsubscribes/import")
    Response importAddressesToUnsubscribeTableFeignResponse(@Param("domain") String domain, UnsubscribesListImportRequest request);


    /**
     * <p>
     * Remove an address from the unsubscribes list.
     * </p>
     *
     * @param domain  Name of the domain
     * @param address An email address
     * @param tag     Specific tag to remove (optional)
     * @return {@link SuppressionResponse}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("DELETE /{domain}/unsubscribes/{address}")
    SuppressionResponse removeAddressFromUnsubscribeTag(@Param("domain") String domain, @Param("address") String address, @Param("tag") String tag);

    /**
     * <p>
     * Remove an address from the unsubscribes list.
     * </p>
     *
     * @param domain  Name of the domain
     * @param address An email address
     * @param tag     Specific tag to remove
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("DELETE /{domain}/unsubscribes/{address}")
    Response removeAddressFromUnsubscribeTagFeignResponse(@Param("domain") String domain, @Param("address") String address, @Param("tag") String tag);

    /**
     * <p>
     * Completely remove an address from the unsubscribes list.
     * </p>
     *
     * @param domain  Name of the domain
     * @param address An email address
     * @return {@link SuppressionResponse}
     */
    @RequestLine("DELETE /{domain}/unsubscribes/{address}")
    SuppressionResponse removeAddressFromUnsubscribeList(@Param("domain") String domain, @Param("address") String address);

    /**
     * <p>
     * Completely remove an address from the unsubscribes list.
     * </p>
     *
     * @param domain  Name of the domain
     * @param address An email address
     * @return {@link Response}
     */
    @RequestLine("DELETE /{domain}/unsubscribes/{address}")
    Response removeAddressFromUnsubscribeListFeignResponse(@Param("domain") String domain, @Param("address") String address);

}
