package com.mailgun.model;

import com.mailgun.model.accountmanagement.HttpSigningKeyResponse;
import com.mailgun.model.alerts.AlertsSlackWorkspace;
import com.mailgun.model.alerts.AlertsWebhookSigningKeyResponse;
import com.mailgun.model.alerts.AlertsWebhooksSettings;
import com.mailgun.model.credentials.SmtpCredentialCreateRequest;
import com.mailgun.model.credentials.SmtpCredentialOperationResponse;
import com.mailgun.model.credentials.SmtpCredentialUpdateRequest;
import com.mailgun.model.domainkeys.CreateDomainKeyRequest;
import com.mailgun.model.domains.DomainCredentials;
import com.mailgun.model.domains.DomainUpdateRequest;
import com.mailgun.model.events.Storage;
import com.mailgun.model.keys.ApiKey;
import com.mailgun.model.keys.PublicApiKeyResponse;
import com.mailgun.model.logs.ItemStorage;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SensitiveToStringTest {

    private static final String SENSITIVE = "sensitive-test-value";

    @Test
    void sensitiveFieldsAreExcludedFromToStringTest() {
        assertRedacted(SmtpCredentialCreateRequest.builder().login("sender@example.com").password(SENSITIVE).build(),
                "sender@example.com");
        assertRedacted(SmtpCredentialUpdateRequest.builder().password(SENSITIVE).build(), null);
        assertRedacted(SmtpCredentialOperationResponse.builder()
                .message("created")
                .credentials(Collections.singletonMap("sender@example.com", SENSITIVE))
                .build(), "created");
        assertRedacted(DomainCredentials.builder().login("sender@example.com").password(SENSITIVE).build(),
                "sender@example.com");
        assertRedacted(DomainUpdateRequest.builder().mailfromHost("mail.example.com").smtpPassword(SENSITIVE).build(),
                "mail.example.com");
        assertRedacted(CreateDomainKeyRequest.builder()
                .signingDomain("example.com")
                .pemContent(SENSITIVE)
                .build(), "example.com");
        assertRedacted(ApiKey.builder().id("key-id").secret(SENSITIVE).build(), "key-id");
        assertRedacted(PublicApiKeyResponse.builder().message("regenerated").key(SENSITIVE).build(), "regenerated");
        assertRedacted(HttpSigningKeyResponse.builder().message("regenerated").httpSigningKey(SENSITIVE).build(),
                "regenerated");
        assertRedacted(AlertsSlackWorkspace.builder().teamName("Mail team").token(SENSITIVE).build(), "Mail team");
        assertRedacted(AlertsWebhookSigningKeyResponse.builder().signingKey(SENSITIVE).build(), null);
        assertRedacted(AlertsWebhooksSettings.builder().signingKey(SENSITIVE).build(), null);
        assertRedacted(Storage.builder().url(SENSITIVE).key(SENSITIVE).build(), null);
        assertRedacted(ItemStorage.builder()
                .region("us")
                .key(SENSITIVE)
                .url(Collections.singletonList(SENSITIVE))
                .build(), "us");
    }

    private static void assertRedacted(Object value, String visibleValue) {
        String stringValue = value.toString();
        assertFalse(stringValue.contains(SENSITIVE), stringValue);
        if (visibleValue != null) {
            assertTrue(stringValue.contains(visibleValue), stringValue);
        }
    }
}
