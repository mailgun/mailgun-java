package com.mailgun.api.v5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.ApiVersion;
import com.mailgun.enums.UserRole;
import com.mailgun.model.users.User;
import com.mailgun.model.users.UsersListResponse;
import com.mailgun.model.users.UsersPageRequest;
import com.mailgun.utils.TestHeadersUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class MailgunUsersApiTest extends WireMockBaseTest {

    private static final String USERS_PATH = "/" + ApiVersion.V_5.getValue() + "/users";
    private static final String USER_ID = "user-123";
    private static final String USER_JSON = "{\"id\":\"" + USER_ID + "\","
            + "\"activated\":true,\"name\":\"John Doe\",\"is_disabled\":false,"
            + "\"email\":\"johndoe@example.com\",\"email_details\":{"
            + "\"address\":\"johndoe@example.com\",\"is_valid\":true,\"reason\":\"\","
            + "\"parts\":{\"domain\":\"example.com\",\"local_part\":\"johndoe\","
            + "\"display_name\":\"John Doe\"}},\"role\":\"basic\",\"account_id\":\"account-456\","
            + "\"opened_ip\":\"192.0.2.20\",\"is_master\":true,\"metadata\":{\"team\":\"delivery\"},"
            + "\"tfa_enabled\":true,\"tfa_active\":true,"
            + "\"tfa_created_at\":\"2022-12-20T16:52:01.892000\","
            + "\"password_updated_at\":\"2022-12-21T16:52:01.892000\","
            + "\"preferences\":{\"programming_language\":\"java\","
            + "\"time_format\":\"%m/%d/%y %I:%M %p\",\"time_zone\":\"Europe/Kyiv\"},"
            + "\"auth\":{\"method\":\"sinch\",\"prior_details\":{\"provider\":\"mailgun\"},"
            + "\"prior_method\":\"password\"},\"github_user_id\":null,"
            + "\"salesforce_user_id\":null,\"migration_status\":\"done\"}";

    private MailgunUsersApi mailgunUsersApi;

    @BeforeEach
    void setUp() {
        mailgunUsersApi = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunUsersApi.class);
    }

    @Test
    void getUsersSuccess() {
        stubFor(get(urlPathEqualTo(USERS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"users\":[" + USER_JSON + "],\"total\":1}")));

        UsersListResponse result = mailgunUsersApi.getUsers();

        assertNotNull(result);
        assertEquals(1, result.getTotal());
        assertCompleteUser(result.getUsers().get(0));
    }

    @Test
    void getUsersWithPageRequestSuccess() {
        stubFor(get(urlPathEqualTo(USERS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("role", WireMock.equalTo("basic"))
                .withQueryParam("limit", WireMock.equalTo("10"))
                .withQueryParam("skip", WireMock.equalTo("0"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"users\":[],\"total\":0}")));

        UsersPageRequest pageRequest = UsersPageRequest.builder()
                .roleEnum(UserRole.BASIC)
                .limit(10)
                .skip(0)
                .build();
        UsersListResponse result = mailgunUsersApi.getUsers(pageRequest);

        assertNotNull(result);
        assertEquals(0, result.getTotal());
    }

    @Test
    void getUserSuccess() {
        stubFor(get(urlPathEqualTo(USERS_PATH + "/" + USER_ID))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(USER_JSON)));

        User result = mailgunUsersApi.getUser(USER_ID);

        assertNotNull(result);
        assertCompleteUser(result);
    }

    @Test
    void getCurrentUserSuccess() {
        stubFor(get(urlPathEqualTo(USERS_PATH + "/me"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(USER_JSON)));

        User result = mailgunUsersApi.getCurrentUser();

        assertNotNull(result);
        assertCompleteUser(result);
    }

    private static void assertCompleteUser(User result) {
        assertEquals(USER_ID, result.getId());
        assertEquals(true, result.getActivated());
        assertEquals("John Doe", result.getName());
        assertEquals(false, result.getIsDisabled());
        assertEquals("johndoe@example.com", result.getEmail());
        assertEquals("johndoe@example.com", result.getEmailDetails().getAddress());
        assertEquals(true, result.getEmailDetails().getIsValid());
        assertEquals("", result.getEmailDetails().getReason());
        assertEquals("example.com", result.getEmailDetails().getParts().getDomain());
        assertEquals("johndoe", result.getEmailDetails().getParts().getLocalPart());
        assertEquals("John Doe", result.getEmailDetails().getParts().getDisplayName());
        assertEquals("basic", result.getRole());
        assertEquals(UserRole.BASIC, result.getRoleEnum().orElseThrow());
        assertEquals("account-456", result.getAccountId());
        assertEquals("192.0.2.20", result.getOpenedIp());
        assertEquals(true, result.getIsMaster());
        assertEquals("delivery", result.getMetadata().get("team"));
        assertEquals(true, result.getTfaEnabled());
        assertEquals(true, result.getTfaActive());
        assertEquals("2022-12-20T16:52:01.892000", result.getTfaCreatedAt());
        assertEquals("2022-12-21T16:52:01.892000", result.getPasswordUpdatedAt());
        assertEquals("Europe/Kyiv", result.getPreferences().getTimeZone());
        assertEquals("%m/%d/%y %I:%M %p", result.getPreferences().getTimeFormat());
        assertEquals("java", result.getPreferences().getProgrammingLanguage());
        assertEquals("sinch", result.getAuth().getMethod());
        assertEquals("password", result.getAuth().getPriorMethod());
        assertEquals("mailgun", result.getAuth().getPriorDetails().get("provider"));
        assertNull(result.getGithubUserId());
        assertNull(result.getSalesforceUserId());
        assertEquals("done", result.getMigrationStatus());
    }
}
