package com.mailgun.expanders;

import com.mailgun.enums.YesNoHtml;
import feign.Param;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumExpanderTest {

    @Test
    void expandSuccessTest() {
        Param.Expander enumExpander = new EnumExpander();

        String result = enumExpander.expand(YesNoHtml.YES);

        assertEquals(YesNoHtml.YES.getValue(), result);
    }

}
