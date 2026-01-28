package com.mailgun.api.v5;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.ApiVersion;
import com.mailgun.enums.UserRole;
import com.mailgun.model.users.User;
import com.mailgun.model.users.UserAuth;
import com.mailgun.model.users.UserEmailDetails;
import com.mailgun.model.users.UsersListResponse;
import com.mailgun.model.users.UsersPageRequest;
import com.mailgun.util.StringUtil;
import com.mailgun.utils.TestHeadersUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunUsersApiTest extends WireMockBaseTest {

    private static final String USERS_PATH = "/" + ApiVersion.V_5.getValue() + "/users";
    private static final String USER_ID = "user-123";

    private MailgunUsersApi mailgunUsersApi;

    @BeforeEach
    void setUp() {
        mailgunUsersApi = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunUsersApi.class);
    }

    @Test
    void getUsersSuccess() {
        User user = User.builder()
                .id(USER_ID)
                .name("John Doe")
                .email("johndoe@example.com")
                .role("basic")
                .build();
        UsersListResponse expected = UsersListResponse.builder()
                .users(Collections.singletonList(user))
                .total(1)
                .build();

        stubFor(get(urlPathEqualTo(USERS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(StringUtil.asJsonString(expected))));

        UsersListResponse result = mailgunUsersApi.getUsers();

        assertNotNull(result);
        assertEquals(expected, result);
        assertEquals(1, result.getTotal());
        assertEquals(USER_ID, result.getUsers().get(0).getId());
    }

    @Test
    void getUsersWithPageRequestSuccess() {
        UsersListResponse expected = UsersListResponse.builder()
                .users(Collections.emptyList())
                .total(0)
                .build();

        stubFor(get(urlPathEqualTo(USERS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("role", WireMock.equalTo("basic"))
                .withQueryParam("limit", WireMock.equalTo("10"))
                .withQueryParam("skip", WireMock.equalTo("0"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(StringUtil.asJsonString(expected))));

        UsersPageRequest pageRequest = UsersPageRequest.withRole(UserRole.BASIC, 10, 0);
        UsersListResponse result = mailgunUsersApi.getUsers(pageRequest);

        assertNotNull(result);
        assertEquals(0, result.getTotal());
    }

    @Test
    void getUserSuccess() {
        User expected = User.builder()
                .id(USER_ID)
                .name("John Doe")
                .activated(true)
                .isDisabled(false)
                .email("johndoe@example.com")
                .emailDetails(UserEmailDetails.builder()
                        .address("johndoe@example.com")
                        .isValid(true)
                        .build())
                .role("admin")
                .accountId("acc-456")
                .isMaster(true)
                .tfaEnabled(false)
                .tfaActive(false)
                .auth(UserAuth.builder().method("sinch").build())
                .build();

        stubFor(get(urlPathEqualTo(USERS_PATH + "/" + USER_ID))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(StringUtil.asJsonString(expected))));

        User result = mailgunUsersApi.getUser(USER_ID);

        assertNotNull(result);
        assertEquals(USER_ID, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("admin", result.getRole());
    }

    @Test
    void getCurrentUserSuccess() {
        User expected = User.builder()
                .id(USER_ID)
                .name("Jane Doe")
                .email("janedoe@example.com")
                .role("developer")
                .build();

        stubFor(get(urlPathEqualTo(USERS_PATH + "/me"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(StringUtil.asJsonString(expected))));

        User result = mailgunUsersApi.getCurrentUser();

        assertNotNull(result);
        assertEquals(USER_ID, result.getId());
        assertEquals("Jane Doe", result.getName());
        assertEquals("developer", result.getRole());
    }
}
