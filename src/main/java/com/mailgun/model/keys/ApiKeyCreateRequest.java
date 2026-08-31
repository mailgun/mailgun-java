package com.mailgun.model.keys;

import com.mailgun.enums.ApiKeyKind;
import com.mailgun.enums.ApiKeyRole;
import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Form fields for creating a Mailgun API key.
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ApiKeyCreateRequest {

    @FormProperty("domain_name")
    String domainName;

    String kind;

    String description;

    Integer expiration;

    String role;

    @FormProperty("user_id")
    String userId;

    @FormProperty("user_name")
    String userName;

    String email;

    public static class ApiKeyCreateRequestBuilder {

        public ApiKeyCreateRequestBuilder kindEnum(ApiKeyKind kind) {
            this.kind = kind != null ? kind.getValue() : null;
            return this;
        }

        public ApiKeyCreateRequestBuilder roleEnum(ApiKeyRole role) {
            this.role = role != null ? role.getValue() : null;
            return this;
        }
    }
}
