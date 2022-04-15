package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.mailing.lists.AddMailingListMembersRequest;
import com.mailgun.model.mailing.lists.DeleteMailingListResponse;
import com.mailgun.model.mailing.lists.MailingListDataResponse;
import com.mailgun.model.mailing.lists.MailingListMemberResponse;
import com.mailgun.model.mailing.lists.MailingListMemberUpdateRequest;
import com.mailgun.model.mailing.lists.MailingListMembersRequest;
import com.mailgun.model.mailing.lists.MailingListMembersResponse;
import com.mailgun.model.mailing.lists.MailingListNewMemberRequest;
import com.mailgun.model.mailing.lists.MailingListRequest;
import com.mailgun.model.mailing.lists.MailingListResponse;
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
 * See
 * A mailing list is a group of members (recipients) which itself has an email address, like <code>developers@mailgun.net</code>.
 * This address becomes an ID for this mailing list.
 * </p>
 * <p>
 * When you send a message to <code>developers@mailgun.net</code>, all members of the list will receive a copy of it.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-mailinglists.html">Mailing Lists</a>
 */
public interface MailgunMailingListApi extends MailgunApi {

    /**
     * <p>
     * Returns mailing lists under your account (limit to 100 entries).
     * </p>
     *
     * @return {@link MailingListDataResponse}
     */
    @Headers("Accept: application/json")
    @RequestLine("GET /lists/pages")
    MailingListDataResponse getMailingList();

    /**
     * <p>
     * Returns mailing lists under your account (limit to 100 entries).
     * </p>
     *
     * @return {@link Response}
     */
    @Headers("Accept: application/json")
    @RequestLine("GET /lists/pages")
    Response getMailingListFeignResponse();

    /**
     * <p>
     * Returns mailing lists under your account.
     * </p>
     *
     * @param limit Maximum number of records to return
     * @return {@link MailingListDataResponse}
     */
    @Headers("Accept: application/json")
    @RequestLine("GET /lists/pages?limit={limit}")
    MailingListDataResponse getMailingList(@Param("limit") Integer limit);

    /**
     * <p>
     * Returns mailing lists under your account.
     * </p>
     *
     * @param limit Maximum number of records to return
     * @return {@link Response}
     */
    @Headers("Accept: application/json")
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
    @Headers("Accept: application/json")
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
    @Headers("Accept: application/json")
    @RequestLine("GET /lists/{mailingListAddress}")
    Response getMailingListByAddressFeignResponse(@Param("mailingListAddress") String mailingListAddress);

    /**
     * <p>
     * Creates a new mailing list.
     * </p>
     *
     * @param request {@link MailingListRequest}
     * @return {@link MailingListResponse}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
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
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
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
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
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
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
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
    @Headers({"Accept: application/json"})
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
    @Headers({"Accept: application/json"})
    @RequestLine("DELETE /lists/{mailingListAddress}")
    Response deleteMailingListFeignResponse(@Param("mailingListAddress") String mailingListAddress);

    /**
     * Verify all the members of the mailing list.
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link MailingListVerificationResponse}
     */
    @Headers({"Accept: application/json"})
    @RequestLine("POST /lists/{mailingListAddress}/validate")
    MailingListVerificationResponse verifyMailingListMembers(@Param("mailingListAddress") String mailingListAddress);

    /**
     * Verify all the members of the mailing list.
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link Response}
     */
    @Headers({"Accept: application/json"})
    @RequestLine("POST /lists/{mailingListAddress}/validate")
    Response verifyMailingListMembersFeignResponse(@Param("mailingListAddress") String mailingListAddress);

    /**
     * Retrieve current status of the mailing list verification job.
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link MailingListVerificationStatusResponse}
     */
    @Headers({"Accept: application/json"})
    @RequestLine("GET /lists/{mailingListAddress}/validate")
    MailingListVerificationStatusResponse getMailingListVerificationJobStatus(@Param("mailingListAddress") String mailingListAddress);

    /**
     * Retrieve current status of the mailing list verification job.
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link Response}
     */
    @Headers({"Accept: application/json"})
    @RequestLine("GET /lists/{mailingListAddress}/validate")
    Response getMailingListVerificationJobStatusFeignResponse(@Param("mailingListAddress") String mailingListAddress);

    /**
     * Cancel an active mailing list verification job.
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return Result status message
     */
    @Headers({"Accept: application/json"})
    @RequestLine("DELETE /lists/{mailingListAddress}/validate")
    String cancelActiveMailingListVerificationJob(@Param("mailingListAddress") String mailingListAddress);

    /**
     * Cancel an active mailing list verification job.
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link Response}
     */
    @Headers({"Accept: application/json"})
    @RequestLine("DELETE /lists/{mailingListAddress}/validate")
    Response cancelActiveMailingListVerificationJobFeignResponse(@Param("mailingListAddress") String mailingListAddress);

    /**
     * <p>
     * Returns the list of members in the given mailing list (limit to 100 entries).
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link MailingListMembersResponse}
     */
    @Headers("Accept: application/json")
    @RequestLine("GET /lists/{mailingListAddress}/members/pages")
    MailingListMembersResponse getMailingListMembers(@Param("mailingListAddress") String mailingListAddress);

    /**
     * <p>
     * Returns the list of members in the given mailing list (limit to 100 entries).
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @return {@link Response}
     */
    @Headers("Accept: application/json")
    @RequestLine("GET /lists/{mailingListAddress}/members/pages")
    Response getMailingListMembersFeignResponse(@Param("mailingListAddress") String mailingListAddress);

    /**
     * <p>
     * Returns the list of members in the given mailing list.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param parameters         {@link MailingListMembersRequest}
     * @return {@link MailingListMembersResponse}
     */
    @Headers("Accept: application/json")
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
    @Headers("Accept: application/json")
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
    @Headers("Accept: application/json")
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
    @Headers("Accept: application/json")
    @RequestLine("GET /lists/{mailingListAddress}/members/{memberAddress}")
    Response getMailingListMemberFeignResponse(@Param("mailingListAddress") String mailingListAddress, @Param("memberAddress") String memberAddress);

    /**
     * <p>
     * Adds a member to the mailing list.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param request            {@link MailingListNewMemberRequest}
     * @return {@link MailingListMemberResponse}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
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
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
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
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
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
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("PUT /lists/{mailingListAddress}/members/{memberAddress}")
    Response updateMailingListMemberFeignResponse(@Param("mailingListAddress") String mailingListAddress, @Param("memberAddress") String memberAddress, MailingListMemberUpdateRequest request);

    /**
     * <p>
     * Adds multiple members, up to 1,000 per call, to a Mailing List.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param request            {@link AddMailingListMembersRequest}
     * @return {@link MailingListResponse}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
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
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("POST /lists/{mailingListAddress}/members.json")
    Response addMembersToMailingListFeignResponse(@Param("mailingListAddress") String mailingListAddress, AddMailingListMembersRequest request);

    /**
     * <p>
     * Delete a mailing list member.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param memberAddress      mailing list member email address
     * @return {@link MailingListMemberResponse }
     */
    @Headers("Accept: application/json")
    @RequestLine("DELETE /lists/{mailingListAddress}/members/{memberAddress}")
    MailingListMemberResponse deleteMemberFromMailingList(@Param("mailingListAddress") String mailingListAddress, @Param("memberAddress") String memberAddress);

    /**
     * <p>
     * Delete a mailing list member.
     * </p>
     *
     * @param mailingListAddress an email address, the ID for the mailing list.
     * @param memberAddress      mailing list member email address
     * @return {@link Response }
     */
    @Headers("Accept: application/json")
    @RequestLine("DELETE /lists/{mailingListAddress}/members/{memberAddress}")
    Response deleteMemberFromMailingListFeignResponse(@Param("mailingListAddress") String mailingListAddress, @Param("memberAddress") String memberAddress);

}
