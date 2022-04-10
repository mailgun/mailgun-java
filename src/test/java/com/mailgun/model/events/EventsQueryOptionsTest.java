package com.mailgun.model.events;

import com.mailgun.enums.EventType;
import com.mailgun.enums.Severity;
import com.mailgun.enums.YesNo;
import org.junit.jupiter.api.Test;

import static com.mailgun.constants.TestConstants.EPOCH_TIME_FEB_22_2022;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_1;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_2;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_3;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_SUBJECT;
import static com.mailgun.constants.TestConstants.TEST_TAG_1;
import static com.mailgun.constants.TestConstants.ZONED_DATE_TIME_2022_16_02;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EventsQueryOptionsTest {

    @Test
    void builderTimeRangeZonedDateTimeSuccessTest() {
        EventsQueryOptions queryOptions = EventsQueryOptions.builder()
                .begin(ZONED_DATE_TIME_2022_16_02)
                .end(EPOCH_TIME_FEB_22_2022)
                .build();

        assertEquals(1645017900, queryOptions.getBegin());
        assertEquals(1645536360, queryOptions.getEnd());
    }

    @Test
    void builderTimeRangeLongSuccessTest() {
        EventsQueryOptions queryOptions = EventsQueryOptions.builder()
                .begin(1645017900L)
                .end(1645536360L)
                .build();

        assertEquals(1645017900, queryOptions.getBegin());
        assertEquals(1645536360, queryOptions.getEnd());
    }

    @Test
    void builderFilterOtherFieldsSuccessTest() {
        String sizeFilterExpression = ">10000 <20000";
        String attachment = "hello_world.txt";
        String messageId = "12345";
        EventsQueryOptions eventsQueryOptions = EventsQueryOptions.builder()
                .ascending(true)
                .limit(2)
                .event(EventType.DELIVERED)
                .attachment(attachment)
                .from(TEST_EMAIL_1)
                .messageId(messageId)
                .subject(TEST_EMAIL_SUBJECT)
                .to(TEST_EMAIL_2)
                .size(sizeFilterExpression)
                .recipient(TEST_EMAIL_3)
                .tags(TEST_TAG_1)
                .severity(Severity.TEMPORARY)
                .build();

        assertEquals(YesNo.YES.getValue(), eventsQueryOptions.getAscending());
        assertEquals(2, eventsQueryOptions.getLimit());
        assertEquals(EventType.DELIVERED.getValue(), eventsQueryOptions.getEvent());
        assertEquals(attachment, eventsQueryOptions.getAttachment());
        assertEquals(TEST_EMAIL_1, eventsQueryOptions.getFrom());
        assertEquals(messageId, eventsQueryOptions.getMessageId());
        assertEquals(TEST_EMAIL_SUBJECT, eventsQueryOptions.getSubject());
        assertEquals(TEST_EMAIL_2, eventsQueryOptions.getTo());
        assertEquals(sizeFilterExpression, eventsQueryOptions.getSize());
        assertEquals(TEST_EMAIL_3, eventsQueryOptions.getRecipient());
        assertEquals(TEST_TAG_1, eventsQueryOptions.getTags());
        assertEquals(Severity.TEMPORARY.getValue(), eventsQueryOptions.getSeverity());
    }

}
