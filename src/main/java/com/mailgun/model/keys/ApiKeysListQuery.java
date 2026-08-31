package com.mailgun.model.keys;

import com.mailgun.enums.ApiKeyKind;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Optional filters for {@code GET /v1/keys}.
 */
@Value
@Jacksonized
@Builder
public class ApiKeysListQuery {

    String domain_name;

    String kind;

    public static class ApiKeysListQueryBuilder {

        public ApiKeysListQueryBuilder kindEnum(ApiKeyKind kind) {
            this.kind = kind != null ? kind.getValue() : null;
            return this;
        }
    }
}
