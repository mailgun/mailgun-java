package com.mailgun.model.templates;

import com.mailgun.enums.YesNo;
import org.junit.jupiter.api.Test;

import static com.mailgun.constants.IntegrationTestConstants.TEMPLATE_2;
import static com.mailgun.constants.IntegrationTestConstants.TEMPLATE_COMMENT;
import static com.mailgun.constants.IntegrationTestConstants.TEMPLATE_ENGINE;
import static com.mailgun.constants.IntegrationTestConstants.TEMPLATE_VERSION_TAG_2;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TemplateVersionRequestTest {

    @Test
    void builderSuccessTest() {
        TemplateVersionRequest request = TemplateVersionRequest.builder()
                .template(TEMPLATE_2)
                .tag(TEMPLATE_VERSION_TAG_2)
                .engine(TEMPLATE_ENGINE)
                .comment(TEMPLATE_COMMENT)
                .active(true)
                .build();

        assertEquals(TEMPLATE_2, request.getTemplate());
        assertEquals(TEMPLATE_VERSION_TAG_2, request.getTag());
        assertEquals(TEMPLATE_ENGINE, request.getEngine());
        assertEquals(TEMPLATE_COMMENT, request.getComment());
        assertEquals(YesNo.YES.getValue(), request.getActive());
    }

}
