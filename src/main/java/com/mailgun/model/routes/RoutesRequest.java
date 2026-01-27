package com.mailgun.model.routes;

import com.mailgun.util.CollectionUtil;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * Routes request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/routes">Routes</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class RoutesRequest {

    /**
     * <p>
     * Integer: smaller number indicates higher priority.
     * Higher priority routes are handled first.
     * </p>
     * Defaults to 0.
     */
    Integer priority;

    /**
     * <p>
     * Route description (an arbitrary string).
     * </p>
     */
    String description;

    /**
     * <p>
     * A filter expression like <code>match_recipient('.*@gmail.com')</code>.
     * </p>
     */
    String expression;

    /**
     * <p>
     * Route action.
     * </p>
     * <p>
     * This action is executed when the expression evaluates to <code>True</code>.
     * </p>
     * <p>
     * Example: <code>forward("alice@example.com")</code>
     * </p>
     * <p>
     * You can pass multiple action parameters.
     * </p>
     */
    Set<String> action;

    public static class RoutesRequestBuilder {

        /**
         * <p>
         * Route action.
         * </p>
         * <p>
         * This action is executed when the expression evaluates to <code>True</code>.
         * </p>
         * <p>
         * Example: <code>forward("alice@example.com")</code>
         * </p>
         * <p>
         * You can pass multiple action parameters.
         * </p>
         *
         * @param action Route action
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public RoutesRequest.RoutesRequestBuilder action(String action) {
            this.action = CollectionUtil.addToSet(this.action, action);
            return this;
        }

        /**
         * <p>
         * Route actions.
         * </p>
         * <p>
         * This action is executed when the expression evaluates to <code>True</code>.
         * </p>
         * <p>
         * Example: <code>forward("alice@example.com")</code>
         * </p>
         *
         * @param actions list if route actions.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public RoutesRequest.RoutesRequestBuilder actions(List<String> actions) {
            this.action = CollectionUtil.addToSet(this.action, actions);
            return this;
        }
    }

}
