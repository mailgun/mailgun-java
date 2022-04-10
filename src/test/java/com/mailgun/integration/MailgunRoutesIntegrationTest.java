package com.mailgun.integration;

import com.mailgun.api.v3.MailgunRoutesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.routes.Route;
import com.mailgun.model.routes.RoutesListResponse;
import com.mailgun.model.routes.RoutesPageRequest;
import com.mailgun.model.routes.RoutesRequest;
import com.mailgun.model.routes.RoutesResponse;
import com.mailgun.model.routes.SingleRouteResponse;
import feign.FeignException;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.List;

import static com.mailgun.constants.IntegrationTestConstants.PRIVATE_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_2;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_3;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_4;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Use these tests as examples for a real implementation.")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MailgunRoutesIntegrationTest {

    private static String routeId;


    private static MailgunRoutesApi mailgunRoutesApi;

    @BeforeAll
    static void beforeAll() {
        mailgunRoutesApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunRoutesApi.class);
    }

    @Order(1)
    @Test
    void getRoutesListSuccessTest() {
        RoutesListResponse result = mailgunRoutesApi.getRoutesList();

        assertNotNull(result.getTotalCount());
        assertTrue(CollectionUtils.isNotEmpty(result.getItems()));
        Route route = result.getItems().get(0);
        assertNotNull(route.getId());
        assertNotNull(route.getPriority());
        assertNotNull(route.getExpression());
        assertNotNull(route.getDescription());
        assertTrue(CollectionUtils.isNotEmpty(route.getActions()));
    }

    @Order(2)
    @Test
    void getRoutesListPageRequestSuccessTest() {
        RoutesPageRequest pageRequest = RoutesPageRequest.builder()
                .limit(2)
                .skip(2)
                .build();

        RoutesListResponse result = mailgunRoutesApi.getRoutesList(pageRequest);

        assertNotNull(result.getTotalCount());
        assertTrue(CollectionUtils.isNotEmpty(result.getItems()));
        assertEquals(2, result.getItems().size());
    }

    @Order(3)
    @Test
    void getSingleRouteSuccessTest() {
        List<Route> routes = mailgunRoutesApi.getRoutesList(RoutesPageRequest.builder().limit(1).build()).getItems();
        assertTrue(CollectionUtils.isNotEmpty(routes));

        SingleRouteResponse result = mailgunRoutesApi.getSingleRoute(routes.get(0).getId());

        Route route = result.getRoute();
        assertNotNull(route.getId());
        assertNotNull(route.getPriority());
        assertNotNull(route.getExpression());
        assertNotNull(route.getDescription());
        assertTrue(CollectionUtils.isNotEmpty(route.getActions()));
    }

    @Order(4)
    @Test
    void createRouteSuccessTest() {
        String action_1 = "forward('" + TEST_EMAIL_2 + "')";
        String action_2 = "forward('" + TEST_EMAIL_3 + "')";
        String action_3 = "forward('https://myhost.com/messages')";
        String action_4 = "stop()";
        RoutesRequest routesRequest = RoutesRequest.builder()
                .priority(2)
                .description("Java test route")
                .expression("match_recipient('.*fake-address-01@example.com')")
                .action(action_1)
                .action(action_2)
                .actions(Arrays.asList(action_3, action_4))
                .build();

        RoutesResponse result = mailgunRoutesApi.createRoute(routesRequest);

        String expected = "Route has been created";
        assertEquals(expected, result.getMessage());
        Route route = result.getRoute();
        assertNotNull(route.getId());
        routeId = route.getId();
        assertNotNull(route.getPriority());
        assertEquals(2, route.getPriority());
        assertNotNull(route.getExpression());
        assertNotNull(route.getDescription());
        List<String> actions = route.getActions();
        assertTrue(CollectionUtils.isNotEmpty(actions));
        assertEquals(4, actions.size());
        assertTrue(actions.containsAll(Arrays.asList(action_1, action_2, action_3, action_4)));
    }

    @Order(5)
    @Test
    void updateRouteSuccessTest() {
        String newAction = "forward('" + TEST_EMAIL_4 + "')";
        RoutesRequest routesRequest = RoutesRequest.builder()
                .priority(1)
                .action(newAction)
                .build();

        Route result = mailgunRoutesApi.updateRoute(routeId, routesRequest);

        assertEquals(routeId, result.getId());
        assertNotNull(result.getPriority());
        assertEquals(1, result.getPriority());
        assertNotNull(result.getExpression());
        assertNotNull(result.getDescription());
        List<String> actions = result.getActions();
        assertTrue(CollectionUtils.isNotEmpty(actions));
        assertEquals(1, actions.size());
        assertTrue(actions.contains(newAction));
    }

    @Order(6)
    @Test
    void deleteRouteSuccessTest() {
        ResponseWithMessage result = mailgunRoutesApi.deleteRoute(routeId);

        assertEquals("Route has been deleted", result.getMessage());

        FeignException exception = assertThrows(FeignException.class, () ->
                mailgunRoutesApi.getSingleRoute(routeId)
        );

        assertTrue(exception.getMessage().contains("Route not found"));
        assertEquals(404, exception.status());
    }

}
