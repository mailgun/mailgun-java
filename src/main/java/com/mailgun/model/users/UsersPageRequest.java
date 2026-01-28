package com.mailgun.model.users;

import com.mailgun.enums.UserRole;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Query parameters for listing users on an account.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/users">Users API</a>
 */
@Value
@Jacksonized
@Builder
public class UsersPageRequest {

    /**
     * Filter results by user role (basic, billing, support, developer, admin).
     * Use {@link UserRole#getValue()} when passing an enum, e.g. {@code UserRole.BASIC.getValue()}.
     */
    String role;

    /**
     * Maximum number of users to return.
     */
    Integer limit;

    /**
     * Number of users to skip.
     */
    Integer skip;

    /**
     * Create a request that filters by {@link UserRole}.
     */
    public static UsersPageRequest withRole(UserRole userRole, Integer limit, Integer skip) {
        return UsersPageRequest.builder()
                .role(userRole != null ? userRole.getValue() : null)
                .limit(limit)
                .skip(skip)
                .build();
    }
}
