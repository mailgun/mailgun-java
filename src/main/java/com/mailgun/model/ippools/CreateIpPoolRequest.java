package com.mailgun.model.ippools;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Request to create a dedicated IP pool (DIPP).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ip-pools/post-v3-ip-pools">Add a new DIPP</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class CreateIpPoolRequest {

    /**
     * Description of the DIPP.
     */
    String description;

    /**
     * IP addresses to add to the DIPP (may be specified multiple times).
     */
    @FormProperty("ip")
    List<String> ip;

    /**
     * Short name of the DIPP.
     */
    String name;

}
