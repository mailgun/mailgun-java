package com.mailgun.model.templates;

import com.mailgun.enums.YesNo;
import org.junit.jupiter.api.Test;

import static com.mailgun.constants.IntegrationTestConstants.TEMPLATE_2;
import static com.mailgun.constants.IntegrationTestConstants.TEMPLATE_COMMENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateTemplateVersionRequestTest {

    @Test
    void builderSuccessTest() {
        UpdateTemplateVersionRequest request = UpdateTemplateVersionRequest.builder()
                .template(TEMPLATE_2)
                .comment(TEMPLATE_COMMENT)
                .active(true)
                .build();

        assertEquals(TEMPLATE_2, request.getTemplate());
        assertEquals(TEMPLATE_COMMENT, request.getComment());
        assertEquals(YesNo.YES.getValue(), request.getActive());
    }

}
