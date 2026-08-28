package com.mailgun.model.ipallowlist;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/** Multipart form used to add or update an IP allowlist entry. */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class IpAllowlistEntryRequest {

    String address;

    String description;
}
