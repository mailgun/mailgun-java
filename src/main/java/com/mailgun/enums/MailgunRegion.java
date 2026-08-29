package com.mailgun.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.mailgun.util.Constants.DEFAULT_BASE_URL_US_REGION;
import static com.mailgun.util.Constants.EU_REGION_BASE_URL;

/**
 * Mailgun API regions.
 */
@Getter
@RequiredArgsConstructor
public enum MailgunRegion {

    US(DEFAULT_BASE_URL_US_REGION),
    EU(EU_REGION_BASE_URL);

    private final String baseUrl;
}
