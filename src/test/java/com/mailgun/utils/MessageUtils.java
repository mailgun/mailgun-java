package com.mailgun.utils;

import com.mailgun.model.message.MessageResponse;
import com.mailgun.util.StringUtil;
import lombok.experimental.UtilityClass;

import static com.mailgun.constants.TestConstants.EMAIL_RESPONSE_ID;
import static com.mailgun.constants.TestConstants.EMAIL_RESPONSE_MESSAGE;

@UtilityClass
public class MessageUtils {

    public MessageResponse getMessageResponse() {
        return MessageResponse.builder()
                .id(EMAIL_RESPONSE_ID)
                .message(EMAIL_RESPONSE_MESSAGE)
                .build();
    }

    public String getMessageResponseJsonString() {
        return StringUtil.asJsonString(getMessageResponse());
    }

}
