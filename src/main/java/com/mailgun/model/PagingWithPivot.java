package com.mailgun.model;

import com.mailgun.enums.Page;
import feign.Param;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Template page request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-templates.html">Templates</a>
 */
@Value
@Jacksonized
@Builder
public class PagingWithPivot {

    /**
     * <p>
     * Number of records to retrieve.
     * Default value is 10.
     * </p>
     */
    Integer limit;

    /**
     * <p>
     * Name of a page to retrieve.
     * <code>FIRST</code>, <code>LAST</code>, <code>NEXT</code> or <code>PREV</code>.
     * </p>
     */
    String page;

    /**
     * <p>
     * Pivot is used to retrieve records in chronological order.
     * </p>
     */
    @Param("p")
    String pivot;

    public static class PagingWithPivotBuilder {

        /**
         * <p>
         * Name of a page to retrieve.
         * <code>FIRST</code>, <code>LAST</code>, <code>NEXT</code> or <code>PREV</code>.
         * </p>
         *
         * @param page {@link Page}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public PagingWithPivot.PagingWithPivotBuilder page(Page page) {
            this.page = page.getValue();
            return this;
        }
    }

}
