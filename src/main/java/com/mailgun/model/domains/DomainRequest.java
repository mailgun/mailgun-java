package com.mailgun.model.domains;

import com.mailgun.enums.SpamAction;
import com.mailgun.enums.WebScheme;
import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * <p>
 * New domain request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-domains.html#domains">Domains</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class DomainRequest {

    /**
     * <p>
     * Name of the domain (ex. domain.com).
     * </p>
     */
    String name;

    /**
     * <p>
     * Spam action: <code>disabled</code>, <code>block</code> or <code>tag</code>.
     * </p>
     * {@link SpamAction}
     *
     * <p>
     * If <code>disabled</code>, no spam filtering will occur for inbound messages.
     * If <code>block</code>, inbound spam messages will not be delivered.
     * If <code>tag</code>, inbound messages will be tagged with a spam header.
     * </p>
     *
     * <p>
     * The default is <code>disabled</code>.
     * </p>
     */
    @FormProperty("spam_action")
    String spamAction;

    /**
     * <p>
     * Determines whether the domain will accept email for sub-domains when sending messages.
     * <code>true</code> or <code>false</code>
     * </p>
     *
     * <p>
     * The default is <code>false</code>.
     * </p>
     */
    Boolean wildcard;

    /**
     * <p>
     * <code>true</code> or <code>false</code>
     * </p>
     *
     * <p>
     * If set to <code>true</code>, the domain will be the DKIM authority for itself
     * even if the root domain is registered on the same mailgun account
     * </p>
     *
     * <p>
     * If set to <code>false</code>, the domain will have the same DKIM authority as the root domain
     * registered on the same mailgun account
     * </p>
     *
     * <p>
     * The default is <code>false</code>.
     * </p>
     */
    @FormProperty("force_dkim_authority")
    Boolean forceDkimAuthority;

    /**
     * <p>
     * Set the length of your domain’s generated DKIM key
     * </p>
     *
     * <p>
     * <code>1024</code> or <code>2048</code>
     * </p>
     *
     * <p>
     * The default is <code>1024</code>.
     * </p>
     */
    @FormProperty("dkim_key_size")
    Integer dkimKeySize;

    /**
     * <p>
     * An optional, comma-separated list of IP addresses to be assigned to this domain.
     * If not specified, all dedicated IP addresses on the account will be assigned.
     * If the request cannot be fulfilled (e.g. a requested IP is not assigned to the account, etc), a 400 will be returned.
     * </p>
     */
    List<String> ips;

    /**
     * <p>
     * The id of the IP Pool that you wish to assign to the domain.
     * The pool must contain at least 1 IP.
     * </p>
     * <p>
     * Note: IP Pools are only available on certain plans;
     * </p>
     *
     * @see <a href="http://mailgun.com/pricing">Pricing</a>
     */
    @FormProperty("pool_id")
    String poolId;

    /**
     * <p>
     * Set your open, click and unsubscribe URLs to use <code>http</code> or <code>https</code>.
     * </p>
     * {@link WebScheme}
     */
    @FormProperty("web_scheme")
    String webScheme;

    public static class DomainRequestBuilder {

        /**
         * <p>
         * Spam action: <code>disabled</code>, <code>block</code> or <code>tag</code>.
         * </p>
         * {@link SpamAction}
         *
         * <p>
         * If <code>disabled</code>, no spam filtering will occur for inbound messages.
         * If <code>block</code>, inbound spam messages will not be delivered.
         * If <code>tag</code>, inbound messages will be tagged with a spam header.
         * </p>
         *
         * @param spamAction {@link SpamAction}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public DomainRequest.DomainRequestBuilder spamAction(SpamAction spamAction) {
            this.spamAction = spamAction.getValue();
            return this;
        }

        /**
         * <p>
         * Set your open, click and unsubscribe URLs to use <code>http</code> or <code>https</code>.
         * </p>
         *
         * @param webScheme {@link WebScheme}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public DomainRequest.DomainRequestBuilder webScheme(WebScheme webScheme) {
            this.webScheme = webScheme.getValue();
            return this;
        }
    }

}

