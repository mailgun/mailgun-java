package com.mailgun.form;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.ContentProcessor;
import feign.form.ContentType;
import feign.form.multipart.ByteArrayWriter;
import feign.form.multipart.DelegateWriter;
import feign.form.multipart.ManyFilesWriter;
import feign.form.multipart.ManyParametersWriter;
import feign.form.multipart.Output;
import feign.form.multipart.PojoWriter;
import feign.form.multipart.SingleFileWriter;
import feign.form.multipart.SingleParameterWriter;
import feign.form.multipart.Writer;
import lombok.val;

import static feign.form.ContentType.MULTIPART;

public class MultipartFormContentProcessor implements ContentProcessor {

    private final Deque<Writer> writers;
    private final Writer defaultProcessor;

    public MultipartFormContentProcessor(Encoder delegate) {
        writers = new LinkedList<>();
        addWriter(new ByteArrayWriter());
        addWriter(new SingleFormDataWriter());
        addWriter(new ManyFormDataWriter());
        addWriter(new SingleFileWriter());
        addWriter(new ManyFilesWriter());
        addWriter(new SingleParameterWriter());
        addWriter(new ManyParametersWriter());
        addWriter(new PojoWriter(writers));

        defaultProcessor = new DelegateWriter(delegate);
    }

    @Override
    public void process(RequestTemplate template, Charset charset, Map<String, Object> data) throws EncodeException {
        val boundary = Long.toHexString(System.currentTimeMillis());
        val output = new Output(charset);

        for (val entry : data.entrySet()) {
            if (entry == null || entry.getKey() == null || entry.getValue() == null) {
                continue;
            }
            val writer = findApplicableWriter(entry.getValue());
            writer.write(output, boundary, entry.getKey(), entry.getValue());
        }

        output.write("--").write(boundary).write("--").write(CRLF);

        val contentTypeHeaderValue = new StringBuilder()
            .append(getSupportedContentType().getHeader())
            .append("; charset=").append(charset.name())
            .append("; boundary=").append(boundary)
            .toString();

        template.header(CONTENT_TYPE_HEADER, Collections.emptyList()); // reset header
        template.header(CONTENT_TYPE_HEADER, contentTypeHeaderValue);

        val bytes = output.toByteArray();
        template.body(bytes, null);

        try {
            output.close();
        } catch (IOException ex) {
            throw new EncodeException("Output closing error", ex);
        }
    }

    @Override
    public ContentType getSupportedContentType() {
        return MULTIPART;
    }

    public final void addWriter(Writer writer) {
        writers.add(writer);
    }

    private Writer findApplicableWriter(Object value) {
        for (val writer : writers) {
            if (writer.isApplicable(value)) {
                return writer;
            }
        }
        return defaultProcessor;
    }
}
