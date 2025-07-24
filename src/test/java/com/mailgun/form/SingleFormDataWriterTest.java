package com.mailgun.form;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import feign.codec.EncodeException;
import feign.form.FormData;
import feign.form.multipart.Output;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

class SingleFormDataWriterTest {

    @InjectMocks
    SingleFormDataWriter writer;

    @Mock
    Output output;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isApplicable_returnsTrueOnlyForFormData() {
        byte[] dummy = {1, 2, 3};
        FormData fd = new FormData("text/plain", "file.txt", dummy);

        assertTrue(writer.isApplicable(fd));
        assertFalse(writer.isApplicable("string"));
        assertFalse(writer.isApplicable(123));
        assertFalse(writer.isApplicable(null));
    }

    @Test
    void write_emitsRawDataPayload() throws EncodeException {
        byte[] data = {10, 20, 30, 40};
        FormData fd = FormData.builder()
                .contentType("application/octet-stream")
                .fileName("myfile.bin")
                .data(data)
                .build();

        writer.write(output, "myField", fd);

        // verify that at least one write(data) happened
        verify(output).write(data);

        // (optionally) you can assert no other *payload* writes beyond metadata,
        // but you can’t rule out the metadata write here, so we'll stop verifying further.
    }

}