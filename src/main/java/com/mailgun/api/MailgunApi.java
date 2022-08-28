package com.mailgun.api;

import com.mailgun.enums.ApiVersion;

public interface MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_3;
    }

}
