package com.mailgun.integration;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.mailgun.api.v4.MailgunSeedListApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.seedlist.SeedListItem;
import com.mailgun.model.seedlist.SeedListRequest;
import com.mailgun.model.seedlist.SeedListsAttributes;
import com.mailgun.model.seedlist.SeedListsAttributesResponse;
import com.mailgun.model.seedlist.SeedListsFilter;
import com.mailgun.model.seedlist.SeedListsFilters;
import com.mailgun.model.seedlist.SeedListsFiltersResponse;
import com.mailgun.model.seedlist.SeedListsPageRequest;
import com.mailgun.model.seedlist.SeedListsResponse;
import com.mailgun.model.seedlist.SingleSeedListResponse;
import feign.Response;

import static com.mailgun.constants.IntegrationTestConstants.PRIVATE_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Use these tests as examples for a real implementation.")
class MailgunSeedListIntegrationTest {

    private static final String TARGET_EMAIL = "TODO";
    private static final String ATTRIBUTE_SENDER = "sender";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String SEED_FILTER = ".*";
    private static final String SEED_LIST_NAME = "test_seed_list";
    private static final String TEST_DOMAIN_1 = "test_seed_list_domain_1.com";
    private static final String TEST_DOMAIN_2 = "test_seed_list_domain_2.com";

    private static MailgunSeedListApi mailgunSeedListApi;

    @BeforeAll
    static void beforeAll() {
        mailgunSeedListApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunSeedListApi.class);
    }

    @Test
    void generateSeedListSuccessTest() {
        SeedListRequest request = SeedListRequest.builder()
            .seedFilter(SEED_FILTER)
            .name(SEED_LIST_NAME)
            .sendingDomains(Arrays.asList(TEST_DOMAIN_1, TEST_DOMAIN_2))
            .build();

        SeedListItem result = mailgunSeedListApi.generateSeedList(request);

        assertNotNull(result);
        assertTrue(StringUtils.isNotBlank(result.getKid()));
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());
        assertNotNull(result.getLastResultAt());
        assertTrue(StringUtils.isNotBlank(result.getTargetEmail()));
        assertTrue(CollectionUtils.isNotEmpty(result.getSendingDomains()));
        assertNotNull(result.getHasResults());
        assertNotNull(result.getName());
        assertNotNull(result.getSeedFilter());
        assertTrue(StringUtils.isNotBlank(result.getMailingList()));
        assertTrue(MapUtils.isNotEmpty(result.getDeliveryStats()));
    }

    @Test
    void updateSeedListSuccessTest() {
        SeedListRequest request = SeedListRequest.builder()
            .seedFilter(SEED_FILTER)
            .name(SEED_LIST_NAME)
            .sendingDomains(Arrays.asList(TEST_DOMAIN_1, TEST_DOMAIN_2))
            .build();

        SeedListItem result = mailgunSeedListApi.updateSeedList(TARGET_EMAIL, request);

        assertNotNull(result);
        assertTrue(StringUtils.isNotBlank(result.getKid()));
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());
        assertNotNull(result.getLastResultAt());
        assertTrue(StringUtils.isNotBlank(result.getTargetEmail()));
        assertTrue(result.getSendingDomains().containsAll(Arrays.asList(TEST_DOMAIN_1, TEST_DOMAIN_2)));
        assertNotNull(result.getHasResults());
        assertEquals(SEED_LIST_NAME, result.getName());
        assertEquals(SEED_FILTER, result.getSeedFilter());
        assertTrue(StringUtils.isNotBlank(result.getMailingList()));
        assertTrue(MapUtils.isNotEmpty(result.getDeliveryStats()));
    }

    @Test
    void getAllSeedListsSuccessTest() {
        SeedListsResponse result = mailgunSeedListApi.getAllSeedLists();

        assertNotNull(result.getPaging());
        assertNotNull(result.getTotal());

        List<SeedListItem> items = result.getItems();
        assertTrue(CollectionUtils.isNotEmpty(items));


        SeedListItem item = items.get(0);
        assertNotNull(item);
        assertTrue(StringUtils.isNotBlank(item.getKid()));
        assertNotNull(item.getCreatedAt());
        assertNotNull(item.getUpdatedAt());
        assertNotNull(item.getLastResultAt());
        assertTrue(StringUtils.isNotBlank(item.getTargetEmail()));
        assertTrue(CollectionUtils.isNotEmpty(item.getSendingDomains()));
        assertNotNull(item.getHasResults());
        assertNotNull(item.getName());
        assertNotNull(item.getSeedFilter());
        assertTrue(StringUtils.isNotBlank(item.getMailingList()));
        assertTrue(MapUtils.isNotEmpty(item.getDeliveryStats()));
    }

    @Test
    void getAllSeedListsFilterSuccessTest() {
        SeedListsPageRequest filter = SeedListsPageRequest.builder()
            .limit(2)
            .offset(1)
            .ascending(false)
            .build();

        SeedListsResponse result = mailgunSeedListApi.getAllSeedLists(filter);

        List<SeedListItem> items = result.getItems();
        assertTrue(CollectionUtils.isNotEmpty(items));
        assertEquals(2, items.size());
        assertTrue(result.getTotal() > 2);
    }

    @Test
    void getSeedListSuccessTest() {

        SingleSeedListResponse result = mailgunSeedListApi.getSeedList(TARGET_EMAIL);

        SeedListItem item = result.getSeedList();
        assertNotNull(item);
        assertTrue(StringUtils.isNotBlank(item.getKid()));
        assertNotNull(item.getCreatedAt());
        assertNotNull(item.getUpdatedAt());
        assertNotNull(item.getLastResultAt());
        assertTrue(StringUtils.isNotBlank(item.getTargetEmail()));
        assertTrue(CollectionUtils.isNotEmpty(item.getSendingDomains()));
        assertNotNull(item.getHasResults());
        assertNotNull(item.getName());
        assertNotNull(item.getSeedFilter());
        assertTrue(StringUtils.isNotBlank(item.getMailingList()));
        assertTrue(MapUtils.isNotEmpty(item.getDeliveryStats()));
    }

    @Test
    void getSeedListsAttributesSuccessTest() {
        SeedListsAttributesResponse result = mailgunSeedListApi.getSeedListsAttributes();

        SeedListsAttributes attributes = result.getItems();
        assertNotNull(attributes);
        assertTrue(StringUtils.isNotBlank(attributes.getAttribute()));
        assertTrue(CollectionUtils.isNotEmpty(attributes.getValues()));
    }

    @Test
    void getSeedListsAttributeSuccessTest() {
        SeedListsAttributesResponse result = mailgunSeedListApi.getSeedListsAttribute(ATTRIBUTE_NAME);

        SeedListsAttributes attributes = result.getItems();
        assertNotNull(attributes);
        assertTrue(StringUtils.isNotBlank(attributes.getAttribute()));
        assertTrue(CollectionUtils.isNotEmpty(attributes.getValues()));
    }

    @Test
    void getSeedListFiltersSuccessTest() {
        SeedListsFiltersResponse result = mailgunSeedListApi.getSeedListFilters();

        SeedListsFilters supportedFilters = result.getSupportedFilters();
        assertNotNull(supportedFilters);
        List<SeedListsFilter> filters = supportedFilters.getFilters();
        assertTrue(CollectionUtils.isNotEmpty(filters));
        SeedListsFilter filter = filters.get(0);
        assertTrue(StringUtils.isNotBlank(filter.getParameter()));
        assertTrue(StringUtils.isNotBlank(filter.getDescription()));
    }

    @Test
    void deleteSeedListFeignResponseSuccessTest() {
        Response result = mailgunSeedListApi.deleteSeedListFeignResponse(TARGET_EMAIL);

        assertEquals(200, result.status());
        assertEquals("OK", result.reason());
    }

    @Test
    void getResultsAttributesSuccessTest() {
        SeedListsAttributesResponse result = mailgunSeedListApi.getResultsAttributes();

        SeedListsAttributes attributes = result.getItems();
        assertNotNull(attributes);
        assertTrue(StringUtils.isNotBlank(attributes.getAttribute()));
        assertTrue(CollectionUtils.isNotEmpty(attributes.getValues()));
    }

    @Test
    void getResultsAttributeSuccessTest() {
        SeedListsAttributesResponse result = mailgunSeedListApi.getResultsAttribute(ATTRIBUTE_SENDER);

        SeedListsAttributes attributes = result.getItems();
        assertNotNull(attributes);
        assertTrue(StringUtils.isNotBlank(attributes.getAttribute()));
    }

}
