package com.mailgun.model.mailing.lists;

import com.mailgun.enums.YesNo;
import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.File;

/**
 * Multipart request for {@code POST /v3/lists/{list_address}/members.csv}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/mailing-lists/post-lists-list_address-members.csv.md">Bulk upload members (CSV)</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class AddMailingListMembersCsvRequest {

    /**
     * CSV file (columns per Mailgun docs, e.g. address; max 25MB).
     */
    @FormProperty("members")
    File members;

    @FormProperty("subscribed")
    String subscribed;

    @FormProperty("upsert")
    String upsert;

    public static class AddMailingListMembersCsvRequestBuilder {

        public AddMailingListMembersCsvRequestBuilder subscribed(boolean subscribed) {
            this.subscribed = YesNo.getValue(subscribed);
            return this;
        }

        public AddMailingListMembersCsvRequestBuilder upsert(boolean upsert) {
            this.upsert = YesNo.getValue(upsert);
            return this;
        }
    }

}
