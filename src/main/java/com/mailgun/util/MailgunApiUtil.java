package com.mailgun.util;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;

@UtilityClass
public class MailgunApiUtil {

    public String getFullUrl(Class<? extends MailgunApi> apiType, String baseUrl) {
        MailgunApi mailgunApi = (MailgunApi) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{apiType},
                (proxy, method, args) -> {
                    Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
                            .getDeclaredConstructor(Class.class);
                    constructor.setAccessible(true);

                    return constructor.newInstance(apiType)
                            .in(apiType)
                            .unreflectSpecial(method, apiType)
                            .bindTo(proxy)
                            .invokeWithArguments(args);
                }
        );

        ApiVersion apiVersion = mailgunApi.getApiVersion();

        return getFullUrl(baseUrl, apiVersion.getValue());
    }

    private String getFullUrl(String baseUrl, String apiVersion) {
        return (StringUtils.endsWith(baseUrl, "/") ? baseUrl + apiVersion : baseUrl + "/" + apiVersion);
    }

}
