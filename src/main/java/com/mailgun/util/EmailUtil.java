package com.mailgun.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class EmailUtil {

    /**
     * <p>
     * Method concatenates the username and email address of the user.
     * </p>
     *
     * @param name username
     * @param email email address of the user
     * @return concatenated result
     */
    public static String nameWithEmail(String name, String email) {
        return StringUtils.isBlank(name) ? email : name + " <" + email + ">";
    }

}
