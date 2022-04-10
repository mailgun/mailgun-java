package com.mailgun.constants;

import lombok.experimental.UtilityClass;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@UtilityClass
public class TestConstants {

    public static final String TEST_DOMAIN = "some.test.com";
    public static final String TEST_API_KEY = "eddc1f60-5f45-11ec-bf63-0242ac130002";
    public static final String TEST_IP_1 = "1.1111.1.1";
    public static final String TEST_IP_2 = "2.2222.2.2";

    public static final String TEST_EMAIL_1 = "some-fake-address-01@example.com";
    public static final String TEST_EMAIL_2 = "some-fake-address-02@example.com";
    public static final String TEST_EMAIL_3 = "some-fake-address-03@example.com";
    public static final String TEST_EMAIL_4 = "some-fake-address-04@example.com";
    public static final String TEST_EMAIL_5 = "some-fake-address-05@example.com";

    public static final String EMAIL_RESPONSE_MESSAGE = "Queued. Thank you.";
    public static final String EMAIL_RESPONSE_ID = "20211203115317.123@sandbox123.mailgun.org";

    public static final String TEST_EMAIL_SUBJECT = "IT test";
    public static final String TEST_EMAIL_TEXT = "Testing some Mailgun awesomeness!";
    public static final String TEST_EMAIL_HTML = "<html>HTML example of the body</html>";
    public static final String TEST_TAG_1 = "test_tag_1";
    public static final String TEST_TAG_2 = "test_tag_2";
    public static final String TEST_TAG_3 = "test_tag_3";
    public static final String TEST_TAG_4 = "test_tag_4";
    public static final String TEMPLATE_VERSION = "55";

    public static final ZonedDateTime ZONED_DATE_TIME_2018_2_3_GMT = ZonedDateTime.of(2018, 2, 3, 4, 5, 6, 17, ZoneId.of("GMT"));
    public static final String ZONED_DATE_TIME_2018_2_3_GMT_STRING = "Sat, 3 Feb 2018 04:05:06 GMT";
    public static final String ZONED_DATE_TIME_2018_2_03_GMT_STRING = "Sat, 03 Feb 2018 04:05:06 GMT";
    public static final ZonedDateTime ZONED_DATE_TIME_2020_4_5_GMT = ZonedDateTime.of(2020, 4, 5, 4, 5, 6, 17, ZoneId.of("GMT"));
    public static final ZonedDateTime ZONED_DATE_TIME_2020_5_5_GMT = ZonedDateTime.of(2020, 5, 5, 4, 5, 6, 17, ZoneId.of("GMT"));

    public static final ZonedDateTime ZONED_DATE_TIME_2018 = ZonedDateTime.of(2018, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
    public static final ZonedDateTime ZONED_DATE_TIME_2022_16_02 = ZonedDateTime.of(2022, 2, 16, 13, 25, 0, 0, ZoneId.of("UTC"));
    public static final ZonedDateTime NOW = ZonedDateTime.now();

    public static final long EPOCH_TIME_FEB_22_2022 = 1645536360L;
    public static final String ZONED_DATE_TIME_2018_2_3_STRING = "Sat, 03 Feb 2018 04:05:06 +0000";
    public static final String ZONED_DATE_TIME_2020_4_5_STRING = "Sun, 05 Apr 2020 04:05:06 +0000";

    public static final String TEST_USER_NAME = "Zarathustra";
    public static final String GENDER = "gender";
    public static final String MALE = "male";
    public static final String AGE = "age";
    public static final int AGE_VALUE = 27;

}
