package com.mailgun.util;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleLogger extends Logger {

    @Override
    protected void log(String configKey, String format, Object... args) {
        log.info(String.format(String.format("%s%s", methodTag(configKey), format), args));
    }

}
