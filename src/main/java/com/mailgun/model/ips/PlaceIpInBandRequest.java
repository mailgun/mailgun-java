package com.mailgun.model.ips;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Form request for {@code POST /v3/ips/{addr}/ip_band}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/post-v3-ips--addr--ip-band">Place IP into dedicated IP band</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class PlaceIpInBandRequest {

    @FormProperty("ip_band")
    String ipBand;

}
