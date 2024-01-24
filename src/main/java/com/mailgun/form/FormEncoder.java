package com.mailgun.form;

import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.ContentProcessor;
import feign.form.ContentType;
import feign.form.UrlencodedFormContentProcessor;
import feign.form.util.CharsetUtil;
import lombok.val;

import static com.mailgun.form.PojoUtil.isUserPojo;
import static com.mailgun.form.PojoUtil.toMap;
import static java.util.Arrays.asList;

public class FormEncoder implements Encoder {

    private static final String CONTENT_TYPE_HEADER;

    private static final Pattern CHARSET_PATTERN;

    static {
        CONTENT_TYPE_HEADER = "Content-Type";
        CHARSET_PATTERN = Pattern.compile("(?<=charset=)([\\w\\-]+)");
    }

    private final Encoder delegate;

    private final Map<ContentType, ContentProcessor> processors;

    public FormEncoder(Encoder delegate) {
        this.delegate = delegate;

        val list = asList(
            new MultipartFormContentProcessor(delegate),
            new UrlencodedFormContentProcessor()
        );

        processors = new HashMap<>(list.size(), 1.F);
        for (ContentProcessor processor : list) {
            processors.put(processor.getSupportedContentType(), processor);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        String contentTypeValue = getContentTypeValue(template.headers());
        val contentType = ContentType.of(contentTypeValue);
        if (!processors.containsKey(contentType)) {
            delegate.encode(object, bodyType, template);
            return;
        }

        Map<String, Object> data;
        if (MAP_STRING_WILDCARD.equals(bodyType)) {
            data = (Map<String, Object>) object;
        } else if (isUserPojo(bodyType)) {
            data = toMap(object);
        } else {
            delegate.encode(object, bodyType, template);
            return;
        }

        val charset = getCharset(contentTypeValue);
        processors.get(contentType).process(template, charset, data);
    }

    private String getContentTypeValue(Map<String, Collection<String>> headers) {
        for (val entry : headers.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase(CONTENT_TYPE_HEADER)) {
                continue;
            }
            for (val contentTypeValue : entry.getValue()) {
                if (contentTypeValue == null) {
                    continue;
                }
                return contentTypeValue;
            }
        }
        return null;
    }

    private Charset getCharset(String contentTypeValue) {
        val matcher = CHARSET_PATTERN.matcher(contentTypeValue);
        return matcher.find()
            ? Charset.forName(matcher.group(1))
            : CharsetUtil.UTF_8;
    }
}
