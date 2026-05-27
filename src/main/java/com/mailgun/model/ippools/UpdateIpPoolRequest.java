package com.mailgun.model.ippools;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Request to edit a dedicated IP pool (DIPP) ({@code PATCH /v3/ip_pools/{pool_id}}).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/patch-v3-ip-pools--pool-id-">Edit DIPP</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class UpdateIpPoolRequest {

    /**
     * IPs to add to the DIPP (may be specified multiple times).
     */
    @FormProperty("add_ip")
    List<String> addIp;

    /**
     * New description for the DIPP.
     */
    String description;

    /**
     * Domain IDs to link to the DIPP (may be specified multiple times).
     */
    @FormProperty("link_domain")
    List<String> linkDomain;

    /**
     * New short name for the DIPP.
     */
    String name;

    /**
     * IPs to remove from the DIPP (may be specified multiple times).
     */
    @FormProperty("remove_ip")
    List<String> removeIp;

    /**
     * Domain IDs to unlink from the DIPP (may be specified multiple times).
     */
    @FormProperty("unlink_domain")
    List<String> unlinkDomain;

}
