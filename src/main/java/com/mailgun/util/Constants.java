package com.mailgun.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static final String DEFAULT_BASE_URL_US_REGION = "https://api.mailgun.net/";
    public static final String EU_REGION_BASE_URL = "https://api.eu.mailgun.net";

//    "Z" ==> "+0900"
    public static final String RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NUMERIC = "EEE, dd MMM yyyy HH:mm:ss Z";
//    "z" ==> "UTC"
    public static final String RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME = "EEE, d MMM yyyy HH:mm:ss z";
    public static final String RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME_DAY_OCTAL_LITERAL = "EEE, dd MMM yyyy HH:mm:ss z";

    public static final String FIELD_CANNOT_BE_NULL_OR_EMPTY = "Field '%s' cannot be null or empty!";
    public static final String DURATION_MUST_BE_GREATER_THAN_ZERO = "Duration' must be greater than zero: %d";

}
