package com.mailgun.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class EmailUtil {

    /**
     * Method concatenates the username and email address of the user.
     *
     * @param name  username
     * @param email email address of the user
     */
    public static String nameWithEmail(String name, String email) {
        return StringUtils.isBlank(name) ? email : name + " <" + email + ">";
    }

}
