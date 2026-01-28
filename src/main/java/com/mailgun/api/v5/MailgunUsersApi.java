package com.mailgun.api.v5;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.users.User;
import com.mailgun.model.users.UsersListResponse;
import com.mailgun.model.users.UsersPageRequest;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * Mailgun Users API. Supports viewing user entities on an account.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/users">Users API</a>
 */
@Headers("Accept: application/json")
public interface MailgunUsersApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_5;
    }

    /**
     * Get users on an account.
     *
     * @return {@link UsersListResponse}
     */
    @RequestLine("GET /users")
    UsersListResponse getUsers();

    /**
     * Get users on an account.
     *
     * @return {@link Response}
     */
    @RequestLine("GET /users")
    Response getUsersFeignResponse();

    /**
     * Get users on an account with optional filters and pagination.
     *
     * @param pageRequest optional role, limit, skip
     * @return {@link UsersListResponse}
     */
    @RequestLine("GET /users")
    UsersListResponse getUsers(@QueryMap UsersPageRequest pageRequest);

    /**
     * Get users on an account with optional filters and pagination.
     *
     * @param pageRequest optional role, limit, skip
     * @return {@link Response}
     */
    @RequestLine("GET /users")
    Response getUsersFeignResponse(@QueryMap UsersPageRequest pageRequest);

    /**
     * Get details for a user on the account.
     *
     * @param userId the user ID
     * @return {@link User}
     */
    @RequestLine("GET /users/{userId}")
    User getUser(@Param("userId") String userId);

    /**
     * Get details for a user on the account.
     *
     * @param userId the user ID
     * @return {@link Response}
     */
    @RequestLine("GET /users/{userId}")
    Response getUserFeignResponse(@Param("userId") String userId);

    /**
     * Get one's own user details. Requires an API key with a user_id (e.g. web kind).
     *
     * @return {@link User}
     */
    @RequestLine("GET /users/me")
    User getCurrentUser();

    /**
     * Get one's own user details. Requires an API key with a user_id (e.g. web kind).
     *
     * @return {@link Response}
     */
    @RequestLine("GET /users/me")
    Response getCurrentUserFeignResponse();
}
