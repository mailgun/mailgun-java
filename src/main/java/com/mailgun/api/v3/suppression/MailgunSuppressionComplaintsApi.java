package com.mailgun.api.v3.suppression;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.suppression.SuppressionResponse;
import com.mailgun.model.suppression.complaints.ComplaintsItem;
import com.mailgun.model.suppression.complaints.ComplaintsItemResponse;
import com.mailgun.model.suppression.complaints.ComplaintsSingleItemRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

import java.util.List;

/**
 * <p>
 * Mailgun Suppression Complaints Api
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
 * Complaint list stores email addresses of recipients who marked your messages as a spam (for ESPs that support FBL).
 * </p>
 *
 * <p>
 * Mailgun can notify your application every time a recipient flags your message as spam via a
 * <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-spam-complaints">complained webhook</a>.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#complaints">Suppressions/Complaints</a>
 */
public interface MailgunSuppressionComplaintsApi extends MailgunApi {

    /**
     * <p>
     * Returns a list of complaints for a domain (limit to 100 entries).
     * </p>
     * <p>
     * Note Via this API method complaints are returned in the alphabetical order.
     * If you wish to poll for the recently occurred complaints, please consider using the {@link com.mailgun.api.v3.MailgunEventsApi}.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link ComplaintsItemResponse}
     */
    @Headers("Accept: application/json")
    @RequestLine("GET /{domain}/complaints")
    ComplaintsItemResponse getAllComplaints(@Param("domain") String domain);

    /**
     * <p>
     * Returns a list of complaints for a domain (limit to 100 entries).
     * </p>
     * <p>
     * Note Via this API method complaints are returned in the alphabetical order.
     * If you wish to poll for the recently occurred complaints, please consider using the {@link com.mailgun.api.v3.MailgunEventsApi}.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link Response}
     */
    @Headers("Accept: application/json")
    @RequestLine("GET /{domain}/complaints")
    Response getAllComplaintsFeignResponse(@Param("domain") String domain);

    /**
     * <p>
     * Returns a list of complaints for a domain.
     * </p>
     * <p>
     * Note Via this API method complaints are returned in the alphabetical order.
     * If you wish to poll for the recently occurred complaints, please consider using the {@link com.mailgun.api.v3.MailgunEventsApi}.
     * </p>
     *
     * @param domain Name of the domain
     * @param limit  Number of entries to return, max: 10000.
     * @return {@link ComplaintsItemResponse}
     */
    @Headers("Accept: application/json")
    @RequestLine("GET /{domain}/complaints?limit={limit}")
    ComplaintsItemResponse getAllComplaints(@Param("domain") String domain, @Param("limit") Integer limit);

    /**
     * <p>
     * Returns a list of complaints for a domain.
     * </p>
     * <p>
     * Note Via this API method complaints are returned in the alphabetical order.
     * If you wish to poll for the recently occurred complaints, please consider using the {@link com.mailgun.api.v3.MailgunEventsApi}.
     * </p>
     *
     * @param domain Name of the domain
     * @param limit  Number of entries to return, max: 10000.
     * @return {@link Response}
     */
    @Headers("Accept: application/json")
    @RequestLine("GET /{domain}/complaints?limit={limit}")
    Response getAllComplaintsFeignResponse(@Param("domain") String domain, @Param("limit") Integer limit);

    /**
     * <p>
     * Fetch a single spam complaint by a given email address. This is useful to check if a particular user has complained.
     * </p>
     *
     * @param domain  Name of the domain
     * @param address An email address
     * @return {@link ComplaintsItem}
     */
    @Headers("Accept: application/json")
    @RequestLine("GET /{domain}/complaints/{address}")
    ComplaintsItem getSingleComplaint(@Param("domain") String domain, @Param("address") String address);

    /**
     * <p>
     * Fetch a single spam complaint by a given email address. This is useful to check if a particular user has complained.
     * </p>
     *
     * @param domain  Name of the domain
     * @param address An email address
     * @return {@link Response}
     */
    @Headers("Accept: application/json")
    @RequestLine("GET /{domain}/complaints/{address}")
    Response getSingleComplaintFeignResponse(@Param("domain") String domain, @Param("address") String address);

    /**
     * <p>
     * Add an address to the complaints list.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link ComplaintsSingleItemRequest}
     * @return {@link SuppressionResponse}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("POST /{domain}/complaints")
    SuppressionResponse addAddressToComplaintsList(@Param("domain") String domain, ComplaintsSingleItemRequest request);

    /**
     * <p>
     * Add an address to the complaints list.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link ComplaintsSingleItemRequest}
     * @return {@link Response}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("POST /{domain}/complaints")
    Response addAddressToComplaintsListFeignResponse(@Param("domain") String domain, ComplaintsSingleItemRequest request);

    /**
     * <p>
     * Add multiple complaint records to the complaint list in a single API call(up to 1000 complaint records).
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link ComplaintsItem}
     * @return list of {@link ResponseWithMessage}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @RequestLine("POST /{domain}/complaints")
    ResponseWithMessage addAddressesToComplaintsList(@Param("domain") String domain, List<ComplaintsItem> request);

    /**
     * <p>
     * Add multiple complaint records to the complaint list in a single API call(up to 1000 complaint records).
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link ComplaintsItem}
     * @return list of {@link Response}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @RequestLine("POST /{domain}/complaints")
    Response addAddressesToComplaintsListFeignResponse(@Param("domain") String domain, List<ComplaintsItem> request);

    /**
     * <p>
     * Remove Address From Complaints.
     * </p>
     *
     * @param domain  Name of the domain
     * @param address An email address
     * @return {@link SuppressionResponse}
     */
    @Headers({"Accept: application/json"})
    @RequestLine("DELETE /{domain}/complaints/{address}")
    SuppressionResponse removeAddressFromComplaints(@Param("domain") String domain, @Param("address") String address);

    /**
     * <p>
     * Remove Address From Complaints.
     * </p>
     *
     * @param domain  Name of the domain
     * @param address An email address
     * @return {@link Response}
     */
    @Headers({"Accept: application/json"})
    @RequestLine("DELETE /{domain}/complaints/{address}")
    Response removeAddressFromComplaintsFeignResponse(@Param("domain") String domain, @Param("address") String address);

}
