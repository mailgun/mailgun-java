package com.mailgun.util;

import com.mailgun.exception.MailGunException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class StringUtil {

    /**
     * <p>
     * Method that can be used to serialize any Java value as a String.
     * </p>
     *
     * @param obj any Java value
     * @return serialized result
     */
    public String asJsonString(final Object obj) {
        try {
            return ObjectMapperUtil.getObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            log.error("Unable to serialize object.", e);
            throw new MailGunException("Unable to serialize object.");
        }
    }

}
