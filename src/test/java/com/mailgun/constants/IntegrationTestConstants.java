package com.mailgun.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class IntegrationTestConstants {

    public static final String PRIVATE_API_KEY = System.getenv("PRIVATE_API_KEY");
    public static final String MAIN_DOMAIN = System.getenv("MAIN_DOMAIN");
    public static final String EMAIL_FROM = System.getenv("EMAIL_FROM");
    public static final String EMAIL_TO = System.getenv("EMAIL_TO");
    public static final String MAILING_LIST_ADDRESS = System.getenv("ADDRESS_MAILING_LIST");

    public static final String DOMAIN_NAME = System.getenv("TEST_DOMAIN_NAME");
    public static final String TEST_LOGIN = System.getenv("TEST_LOGIN");
    public static final String IP_1 = System.getenv("TEST_IP_1");
    public static final String IP_2 = System.getenv("TEST_IP_2");


    public static final String WEBHOOK_BASE_URL = "http://bin.mailgun.net";
    public static final String WEBHOOK_URL_1 = WEBHOOK_BASE_URL + "/111";
    public static final String WEBHOOK_URL_2 = WEBHOOK_BASE_URL + "/222";
    public static final String WEBHOOK_URL_3 = WEBHOOK_BASE_URL + "/333";
    public static final String WEBHOOK_URL_4 = WEBHOOK_BASE_URL + "/444";

    public static final String TEMPLATE_NAME = "test_temp_template";
    public static final String TEMPLATE_VERSION_TAG_1 = "v_1";
    public static final String TEMPLATE_VERSION_TAG_2 = "v_2";
    public static final String TEMPLATE_ENGINE = "handlebars";
    public static final String TEMPLATE_DESCRIPTION = "test_temp_template description";
    public static final String TEMPLATE_COMMENT = "Some comment";
    public static final String TEMPLATE_1 = "Hey, {{name}}!";
    public static final String TEMPLATE_2 = "Hello, {{name}}!";

}
