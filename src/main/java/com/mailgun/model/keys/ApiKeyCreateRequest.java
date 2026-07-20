package com.mailgun.model.keys;

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
}
