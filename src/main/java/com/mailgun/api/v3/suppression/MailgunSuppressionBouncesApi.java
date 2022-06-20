package com.mailgun.api.v3.suppression;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.suppression.SuppressionResponse;
import com.mailgun.model.suppression.bounces.BouncesItem;
import com.mailgun.model.suppression.bounces.BouncesListImportRequest;
import com.mailgun.model.suppression.bounces.BouncesRequest;
import com.mailgun.model.suppression.bounces.BouncesResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

import java.util.List;


/**
 * <p>
 * Mailgun Suppression Bounces Api
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
 * Bounce list stores events of delivery failures due to permanent recipient mailbox errors such as non-existent mailbox.
 * Soft bounces (for example, mailbox is full) and other failures (for example, ESP rejects an email because it thinks it is spam) are not added to the list.
 * </p>
 *
 * <p>
 * Subsequent delivery attempts to an address found in a bounce list are prevented to protect your sending reputation.
 * </p>
 *
 * <p>
 * Mailgun can notify your application every time a message bounces via a
 * <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-failures">ermanent_fail webhook</a>.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#bounces">Suppressions/Bounces</a>
 */
@Headers("Accept: application/json")
public interface MailgunSuppressionBouncesApi extends MailgunApi {

    /**
     * <p>
     * Returns a list of bounces for a domain (limit to 100 entries).
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link BouncesResponse}
     */
    @RequestLine("GET /{domain}/bounces")
    BouncesResponse getBounces(@Param("domain") String domain);

    /**
     * <p>
     * Returns a list of bounces for a domain (limit to 100 entries).
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/bounces")
    Response getBouncesFeignResponse(@Param("domain") String domain);

    /**
     * <p>
     * Returns a list of bounces for a domain.
     * </p>
     *
     * @param domain Name of the domain
     * @param limit  Number of entries to return, max: 10000.
     * @return {@link BouncesResponse}
     */
    @RequestLine("GET /{domain}/bounces?limit={limit}")
    BouncesResponse getBounces(@Param("domain") String domain, @Param("limit") Integer limit);

    /**
     * <p>
     * Returns a list of bounces for a domain.
     * </p>
     *
     * @param domain Name of the domain
     * @param limit  Number of entries to return, max: 10000.
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/bounces?limit={limit}")
    Response getBouncesFeignResponse(@Param("domain") String domain, @Param("limit") Integer limit);

    /**
     * <p>
     * Fetch a single bounce event by a given email address.
     * </p>
     * <p>
     * Useful to check if a given email address has bounced before.
     * </p>
     *
     * @param domain  Name of the domain
     * @param address An email address
     * @return {@link BouncesItem}
     */
    @RequestLine("GET /{domain}/bounces/{address}")
    BouncesItem getBounce(@Param("domain") String domain, @Param("address") String address);

    /**
     * <p>
     * Fetch a single bounce event by a given email address.
     * </p>
     * <p>
     * Useful to check if a given email address has bounced before.
     * </p>
     *
     * @param domain  Name of the domain
     * @param address An email address
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/bounces/{address}")
    Response getBounceFeignResponse(@Param("domain") String domain, @Param("address") String address);

    /**
     * <p>
     * Add a single bounce record to the bounce list.
     * </p>
     * <p>
     * Updates the existing record if the address is already there.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link BouncesRequest}
     * @return {@link SuppressionResponse}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/bounces")
    SuppressionResponse addBounce(@Param("domain") String domain, BouncesRequest request);

    /**
     * <p>
     * Add a single bounce record to the bounce list.
     * </p>
     * <p>
     * Updates the existing record if the address is already there.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link BouncesRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/bounces")
    Response addSBounceFeignResponse(@Param("domain") String domain, BouncesRequest request);

    /**
     * <p>
     * Add multiple bounce records to the bounce list in a single API call.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request list of {@link BouncesRequest}
     * @return {@link ResponseWithMessage}
     */
    @Headers("Content-Type: application/json")
    @RequestLine("POST /{domain}/bounces")
    ResponseWithMessage addBounces(@Param("domain") String domain, List<BouncesRequest> request);

    /**
     * <p>
     * Add multiple bounce records to the bounce list in a single API call.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request list of {@link BouncesRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: application/json")
    @RequestLine("POST /{domain}/bounces")
    Response addSBounceFeignResponses(@Param("domain") String domain, List<BouncesRequest> request);

    /**
     * <p>
     * Import a list of bounces.
     * </p>
     * <p>
     * CSV file must be 25MB or under and must contain the following column headers:
     * </p>
     * <pre>
     * <code>address</code> Valid email address
     * <code>code</code> Error code (optional, default: 550)
     * <code>error</code> Error description (optional, default: empty string)
     * <code>created_at</code> Timestamp of a bounce event in RFC2822 format (optional, default: current time)
     * </pre>
     *
     * @param domain  Name of the domain
     * @param request {@link BouncesListImportRequest}
     * @return {@link ResponseWithMessage}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/bounces/import")
    ResponseWithMessage importBounceList(@Param("domain") String domain, BouncesListImportRequest request);

    /**
     * <p>
     * Import a list of bounces.
     * </p>
     * <p>
     * CSV file must be 25MB or under and must contain the following column headers:
     * </p>
     * <pre>
     * <code>address</code> Valid email address
     * <code>code</code> Error code (optional, default: 550)
     * <code>error</code> Error description (optional, default: empty string)
     * <code>created_at</code> Timestamp of a bounce event in RFC2822 format (optional, default: current time)
     * </pre>
     *
     * @param domain  Name of the domain
     * @param request {@link BouncesListImportRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/bounces/import")
    Response importBounceListFeignResponses(@Param("domain") String domain, BouncesListImportRequest request);

    /**
     * <p>
     * Delete a single bounce.
     * Clears a given bounce event.
     * </p>
     * <p>
     * The delivery to the deleted email address resumes until it bounces again.
     * </p>
     *
     * @param domain  Name of the domain
     * @param address An email address
     * @return {@link ResponseWithMessage}
     */
    @RequestLine("DELETE /{domain}/bounces/{address}")
    ResponseWithMessage deleteBounce(@Param("domain") String domain, @Param("address") String address);

    /**
     * <p>
     * Delete a single bounce.
     * Clears a given bounce event.
     * </p>
     * <p>
     * The delivery to the deleted email address resumes until it bounces again.
     * </p>
     *
     * @param domain  Name of the domain
     * @param address An email address
     * @return {@link Response}
     */
    @RequestLine("DELETE /{domain}/bounces/{address}")
    Response deleteBounceFeignResponse(@Param("domain") String domain, @Param("address") String address);

    /**
     * <p>
     * Delete all bounced email addresses for a domain.
     * Delivery to the deleted email addresses will no longer be suppressed.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link ResponseWithMessage}
     */
    @RequestLine("DELETE /{domain}/bounces")
    ResponseWithMessage deleteAllBounces(@Param("domain") String domain);

    /**
     * <p>
     * Delete all bounced email addresses for a domain.
     * Delivery to the deleted email addresses will no longer be suppressed.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link Response}
     */
    @RequestLine("DELETE /{domain}/bounces")
    Response deleteAllBouncesFeignResponse(@Param("domain") String domain);

}
