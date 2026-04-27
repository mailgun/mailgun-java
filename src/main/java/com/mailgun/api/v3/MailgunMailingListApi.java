package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.mailing.lists.AddMailingListMembersCsvRequest;
import com.mailgun.model.mailing.lists.AddMailingListMembersRequest;
import com.mailgun.model.mailing.lists.DeleteMailingListResponse;
import com.mailgun.model.mailing.lists.MailingListDataResponse;
import com.mailgun.model.mailing.lists.MailingListMemberResponse;
import com.mailgun.model.mailing.lists.MailingListMemberUpdateRequest;
import com.mailgun.model.mailing.lists.MailingListMembersIndexQuery;
import com.mailgun.model.mailing.lists.MailingListMembersRequest;
import com.mailgun.model.mailing.lists.MailingListMembersResponse;
import com.mailgun.model.mailing.lists.MailingListNewMemberRequest;
import com.mailgun.model.mailing.lists.MailingListRequest;
import com.mailgun.model.mailing.lists.MailingListResponse;
import com.mailgun.model.mailing.lists.MailingListsQuery;
import com.mailgun.model.mailing.lists.MailingListVerificationResponse;
import com.mailgun.model.mailing.lists.MailingListVerificationStatusResponse;
import com.mailgun.model.mailing.lists.SingleMailingListResponse;
import com.mailgun.model.mailing.lists.UpdateMailingListRequest;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * <p>
 * Mailgun Mailing Lists Api.
 * </p>
 * <p>
 * You can programmatically create mailing lists using Mailgun Mailing List API.
 * </p>
 * <p>
 * A mailing list is a group of members (recipients) with its own email address (e.g. {@code developers@mailgun.net});
 * that address is the list ID.
 * </p>
 * <p>
 * When you send a message to <code>developers@mailgun.net</code>, all members of the list will receive a copy of it.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-mailinglists.html">Mailing Lists</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/mailing-lists">Mailing Lists API</a>
 */
@Headers("Accept: application/json")
public interface MailgunMailingListApi extends MailgunApi {

    /**
     * <p>
     * Lists mailing lists for the account ({@code GET /v3/lists}).
     * </p>
     *
     * @return {@link MailingListDataResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/mailing-lists/get-v3-lists.md">Get mailing lists</a>
     */
    @RequestLine("GET /lists")
    MailingListDataResponse getMailingLists();

    /**
     * <p>
     * Lists mailing lists for the account (raw response).
     * </p>
     *
     * @return {@link Response}
     */
    @RequestLine("GET /lists")
    Response getMailingListsFeignResponse();

    /**
     * <p>
     * Lists mailing lists with optional {@code limit}, {@code skip}, and {@code address} filter.
     * </p>
     *
     * @param query optional query parameters
     * @return {@link MailingListDataResponse}
     */
    @RequestLine("GET /lists")
    MailingListDataResponse getMailingLists(@QueryMap MailingListsQuery query);

    /**
     * <p>
     * Lists mailing lists with optional query parameters (raw response).
     * </p>
     *
     * @param query optional query parameters
     * @return {@link Response}
     */
    @RequestLine("GET /lists")
    Response getMailingListsFeignResponse(@QueryMap MailingListsQuery query);

    /**
     * <p>
     * Paginates mailing lists ({@code GET /v3/lists/pages}).
     * </p>
     *
     * @return {@link MailingListDataResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/mailing-lists/get-v3-lists-pages.md">Get mailing lists by page</a>
     */
    @RequestLine("GET /lists/pages")
    MailingListDataResponse getMailingList();

    /**
     * <p>
     * Paginates mailing lists (raw response).
     * </p>
     *
     * @return {@link Response}
     */
    @RequestLine("GET /lists/pages")
    Response getMailingListFeignResponse();

    /**
     * <p>
     * Paginates mailing lists with a page size limit.
     * </p>
     *
     * @param limit Maximum number of records to return
     * @return {@link MailingListDataResponse}
     */
    @RequestLine("GET /lists/pages?limit={limit}")
    MailingListDataResponse getMailingList(@Param("limit") Integer limit);

    /**
     * <p>
     * Paginates mailing lists with a page size limit (raw response).
     * </p>
     *
     * @param limit Maximum number of records to return
     * @return {@link Response}
     */
    @RequestLine("GET /lists/pages?limit={limit}")
    Response getMailingListFeignResponse(@Param("limit") Integer limit);

    /**
     * <p>
     * Returns a single mailing list by a given address.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link SingleMailingListResponse}
     */
    @RequestLine("GET /lists/{mailingListAddress}")
    SingleMailingListResponse getMailingListByAddress(@Param("mailingListAddress") String mailingListAddress);

    /**
     * <p>
     * Returns a single mailing list by a given address.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link Response}
     */
    @RequestLine("GET /lists/{mailingListAddress}")
    Response getMailingListByAddressFeignResponse(@Param("mailingListAddress") String mailingListAddress);

    /**
     * <p>
     * Creates a new mailing list.
     * </p>
     *
     * @param request {@link MailingListRequest}
     * @return {@link MailingListResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/mailing-lists/post-v3-lists.md">Create a mailing list</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /lists")
    MailingListResponse createMailingList(MailingListRequest request);

    /**
     * <p>
     * Creates a new mailing list.
     * </p>
     *
     * @param request {@link MailingListRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /lists")
    Response createMailingListFeignResponse(MailingListRequest request);

    /**
     * <p>
     * Update mailing list properties, such as <code>address</code>, <code>description</code> or <code>name</code>.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param request            {@link MailingListRequest}
     * @return {@link MailingListResponse}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /lists/{mailingListAddress}")
    MailingListResponse updateMailingList(@Param("mailingListAddress") String mailingListAddress, UpdateMailingListRequest request);

    /**
     * <p>
     * Update mailing list properties, such as <code>address</code>, <code>description</code> or <code>name</code>.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param request            {@link MailingListRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /lists/{mailingListAddress}")
    Response updateMailingListFeignResponse(@Param("mailingListAddress") String mailingListAddress, UpdateMailingListRequest request);

    /**
     * <p>
     * Deletes a mailing list.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link DeleteMailingListResponse}
     */
    @RequestLine("DELETE /lists/{mailingListAddress}")
    DeleteMailingListResponse deleteMailingList(@Param("mailingListAddress") String mailingListAddress);

    /**
     * <p>
     * Deletes a mailing list.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link Response}
     */
    @RequestLine("DELETE /lists/{mailingListAddress}")
    Response deleteMailingListFeignResponse(@Param("mailingListAddress") String mailingListAddress);

    /**
     * Verify all the members of the mailing list.
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link MailingListVerificationResponse}
     */
    @RequestLine("POST /lists/{mailingListAddress}/validate")
    MailingListVerificationResponse verifyMailingListMembers(@Param("mailingListAddress") String mailingListAddress);

    /**
     * Verify all the members of the mailing list.
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link Response}
     */
    @RequestLine("POST /lists/{mailingListAddress}/validate")
    Response verifyMailingListMembersFeignResponse(@Param("mailingListAddress") String mailingListAddress);

    /**
     * Retrieve current status of the mailing list verification job.
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link MailingListVerificationStatusResponse}
     */
    @RequestLine("GET /lists/{mailingListAddress}/validate")
    MailingListVerificationStatusResponse getMailingListVerificationJobStatus(@Param("mailingListAddress") String mailingListAddress);

    /**
     * Retrieve current status of the mailing list verification job.
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link Response}
     */
    @RequestLine("GET /lists/{mailingListAddress}/validate")
    Response getMailingListVerificationJobStatusFeignResponse(@Param("mailingListAddress") String mailingListAddress);

    /**
     * Cancel an active mailing list verification job.
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return Result status message
     */
    @RequestLine("DELETE /lists/{mailingListAddress}/validate")
    String cancelActiveMailingListVerificationJob(@Param("mailingListAddress") String mailingListAddress);

    /**
     * Cancel an active mailing list verification job.
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link Response}
     */
    @RequestLine("DELETE /lists/{mailingListAddress}/validate")
    Response cancelActiveMailingListVerificationJobFeignResponse(@Param("mailingListAddress") String mailingListAddress);

    /**
     * <p>
     * Lists members in a mailing list ({@code GET /v3/lists/.../members}).
     * </p>
     *
     * @param mailingListAddress mailing list address
     * @return {@link MailingListMembersResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/mailing-lists/get-lists-string:list_address-members.md">Get mailing list members</a>
     */
    @RequestLine("GET /lists/{mailingListAddress}/members")
    MailingListMembersResponse listMailingListMembers(@Param("mailingListAddress") String mailingListAddress);

    /**
     * <p>
     * Lists members (raw response).
     * </p>
     */
    @RequestLine("GET /lists/{mailingListAddress}/members")
    Response listMailingListMembersFeignResponse(@Param("mailingListAddress") String mailingListAddress);

    /**
     * <p>
     * Lists members with optional filters ({@code limit}, {@code skip}, {@code subscribed}, {@code address}).
     * </p>
     *
     * @param mailingListAddress mailing list address
     * @param query              optional query parameters
     * @return {@link MailingListMembersResponse}
     */
    @RequestLine("GET /lists/{mailingListAddress}/members")
    MailingListMembersResponse listMailingListMembers(@Param("mailingListAddress") String mailingListAddress, @QueryMap MailingListMembersIndexQuery query);

    /**
     * <p>
     * Lists members with optional query parameters (raw response).
     * </p>
     */
    @RequestLine("GET /lists/{mailingListAddress}/members")
    Response listMailingListMembersFeignResponse(@Param("mailingListAddress") String mailingListAddress, @QueryMap MailingListMembersIndexQuery query);

    /**
     * <p>
     * Paginates members in ascending order ({@code GET /v3/lists/.../members/pages}).
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link MailingListMembersResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/mailing-lists/get-lists-list_address-members-pages.md">Get members by page</a>
     */
    @RequestLine("GET /lists/{mailingListAddress}/members/pages")
    MailingListMembersResponse getMailingListMembers(@Param("mailingListAddress") String mailingListAddress);

    /**
     * <p>
     * Paginates members (raw response).
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link Response}
     */
    @RequestLine("GET /lists/{mailingListAddress}/members/pages")
    Response getMailingListMembersFeignResponse(@Param("mailingListAddress") String mailingListAddress);

    /**
     * <p>
     * Paginates members with cursor-style {@link MailingListMembersRequest} parameters.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param parameters         {@link MailingListMembersRequest}
     * @return {@link MailingListMembersResponse}
     */
    @RequestLine("GET /lists/{mailingListAddress}/members/pages")
    MailingListMembersResponse getMailingListMembers(@Param("mailingListAddress") String mailingListAddress, @QueryMap MailingListMembersRequest parameters);

    /**
     * <p>
     * Returns the list of members in the given mailing list.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param parameters         {@link MailingListMembersRequest}
     * @return {@link Response}
     */
    @RequestLine("GET /lists/{mailingListAddress}/members/pages")
    Response getMailingListMembersFeignResponse(@Param("mailingListAddress") String mailingListAddress, @QueryMap MailingListMembersRequest parameters);

    /**
     * <p>
     * Retrieves a mailing list member.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list
     * @param memberAddress      mailing list member email address
     * @return {@link MailingListMemberResponse}
     */
    @RequestLine("GET /lists/{mailingListAddress}/members/{memberAddress}")
    MailingListMemberResponse getMailingListMember(@Param("mailingListAddress") String mailingListAddress, @Param("memberAddress") String memberAddress);

    /**
     * <p>
     * Retrieves a mailing list member.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list
     * @param memberAddress      mailing list member email address
     * @return {@link Response}
     */
    @RequestLine("GET /lists/{mailingListAddress}/members/{memberAddress}")
    Response getMailingListMemberFeignResponse(@Param("mailingListAddress") String mailingListAddress, @Param("memberAddress") String memberAddress);

    /**
     * <p>
     * Adds a member to the mailing list.
     * </p>
     * <p>
     * For many members at once, use {@link #addMembersToMailingList(String, AddMailingListMembersRequest)} (JSON) or
     * {@link #addMembersToMailingListFromCsv(String, AddMailingListMembersCsvRequest)} (CSV).
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param request            {@link MailingListNewMemberRequest}
     * @return {@link MailingListMemberResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/mailing-lists/post-lists-string:list_address-members.md">Create a mailing list member</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /lists/{mailingListAddress}/members")
    MailingListMemberResponse addMemberToMailingList(@Param("mailingListAddress") String mailingListAddress, MailingListNewMemberRequest request);

    /**
     * <p>
     * Adds a member to the mailing list.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param request            {@link MailingListNewMemberRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /lists/{mailingListAddress}/members")
    Response addMemberToMailingListFeignResponse(@Param("mailingListAddress") String mailingListAddress, MailingListNewMemberRequest request);

    /**
     * <p>
     * Updates a mailing list member with given properties. Won’t touch the property if it’s not passed in.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param memberAddress      mailing list member email address
     * @param request            {@link MailingListMemberUpdateRequest}
     * @return {@link MailingListMemberResponse}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /lists/{mailingListAddress}/members/{memberAddress}")
    MailingListMemberResponse updateMailingListMember(@Param("mailingListAddress") String mailingListAddress, @Param("memberAddress") String memberAddress, MailingListMemberUpdateRequest request);

    /**
     * <p>
     * Updates a mailing list member with given properties. Won’t touch the property if it’s not passed in.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param memberAddress      mailing list member email address
     * @param request            {@link MailingListMemberUpdateRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /lists/{mailingListAddress}/members/{memberAddress}")
    Response updateMailingListMemberFeignResponse(@Param("mailingListAddress") String mailingListAddress, @Param("memberAddress") String memberAddress, MailingListMemberUpdateRequest request);

    /**
     * <p>
     * Adds multiple members, up to 1,000 per call, to a Mailing List.
     * </p>
     * <p>
     * If the request contains more than 100 entries, Mailgun may process the update asynchronously (see {@code task-id} on {@link MailingListResponse}).
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param request            {@link AddMailingListMembersRequest}
     * @return {@link MailingListResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/mailing-lists/post-lists-list_address-members.json.md">Bulk upload (JSON)</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /lists/{mailingListAddress}/members.json")
    MailingListResponse addMembersToMailingList(@Param("mailingListAddress") String mailingListAddress, AddMailingListMembersRequest request);

    /**
     * <p>
     * Adds multiple members, up to 1,000 per call, to a Mailing List.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param request            {@link AddMailingListMembersRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /lists/{mailingListAddress}/members.json")
    Response addMembersToMailingListFeignResponse(@Param("mailingListAddress") String mailingListAddress, AddMailingListMembersRequest request);

    /**
     * <p>
     * Bulk-add members from a CSV file (up to 1000 rows per call).
     * </p>
     *
     * @param mailingListAddress mailing list address
     * @param request            multipart CSV payload
     * @return {@link MailingListResponse} may include {@code task-id} for background processing
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/mailing-lists/post-lists-list_address-members.csv.md">Bulk upload (CSV)</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /lists/{mailingListAddress}/members.csv")
    MailingListResponse addMembersToMailingListFromCsv(@Param("mailingListAddress") String mailingListAddress, AddMailingListMembersCsvRequest request);

    /**
     * <p>
     * Bulk-add members from CSV (raw response).
     * </p>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /lists/{mailingListAddress}/members.csv")
    Response addMembersToMailingListFromCsvFeignResponse(@Param("mailingListAddress") String mailingListAddress, AddMailingListMembersCsvRequest request);

    /**
     * <p>
     * Delete a mailing list member.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param memberAddress      mailing list member email address
     * @return {@link MailingListMemberResponse}
     */
    @RequestLine("DELETE /lists/{mailingListAddress}/members/{memberAddress}")
    MailingListMemberResponse deleteMemberFromMailingList(@Param("mailingListAddress") String mailingListAddress, @Param("memberAddress") String memberAddress);

    /**
     * <p>
     * Delete a mailing list member.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param memberAddress      mailing list member email address
     * @return {@link Response}
     */
    @RequestLine("DELETE /lists/{mailingListAddress}/members/{memberAddress}")
    Response deleteMemberFromMailingListFeignResponse(@Param("mailingListAddress") String mailingListAddress, @Param("memberAddress") String memberAddress);

}
