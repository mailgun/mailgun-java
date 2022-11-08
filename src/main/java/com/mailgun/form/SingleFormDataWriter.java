package com.mailgun.form;

import feign.codec.EncodeException;
import feign.form.FormData;
import feign.form.multipart.AbstractWriter;
import feign.form.multipart.Output;
import lombok.val;

public class SingleFormDataWriter extends AbstractWriter {

    @Override
    public boolean isApplicable (Object value) {
        return value instanceof FormData;
    }

    @Override
    protected void write(Output output, String key, Object value) throws EncodeException {
        val formData = (FormData) value;
        writeFileMetadata(output, key, formData.getFileName(), formData.getContentType());
        output.write(formData.getData());
    }
}
