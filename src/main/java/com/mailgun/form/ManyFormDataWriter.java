package com.mailgun.form;

import feign.codec.EncodeException;
import feign.form.FormData;
import feign.form.multipart.AbstractWriter;
import feign.form.multipart.Output;
import lombok.val;

public class ManyFormDataWriter extends AbstractWriter {

    private final SingleFormDataWriter formDataWriter;

    public ManyFormDataWriter(SingleFormDataWriter formDataWriter) {
        this.formDataWriter = formDataWriter;
    }

    public ManyFormDataWriter() {
        this.formDataWriter = new SingleFormDataWriter();
    }

    @Override
    public boolean isApplicable(Object value) {
        if (value instanceof FormData[]) {
            return true;
        }
        if (!(value instanceof Iterable)) {
            return false;
        }
        val iterable = (Iterable<?>) value;
        val iterator = iterable.iterator();

        return iterator.hasNext() && iterator.next() instanceof FormData;
    }

    @Override
    public void write(Output output, String boundary, String key, Object value) throws EncodeException {
        if (value instanceof FormData[]) {
            val formDatas = (FormData[]) value;
            for (val formData : formDatas) {
                formDataWriter.write(output, boundary, key, formData);
            }
        } else if (value instanceof Iterable) {
            val iterable = (Iterable<?>) value;
            for (val formData : iterable) {
                formDataWriter.write(output, boundary, key, formData);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}