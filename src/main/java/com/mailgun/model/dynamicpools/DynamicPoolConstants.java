package com.mailgun.model.dynamicpools;

import lombok.experimental.UtilityClass;

/**
 * Special values for Dynamic IP Pool operations.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dynamic-ip-pools">Dynamic IP Pools</a>
 */
@UtilityClass
public class DynamicPoolConstants {

    /**
     * Replacement IP value: assign a shared IP when the account is eligible.
     */
    public static final String REPLACEMENT_SHARED_IP = "shared";

}
