package com.mailgun.form;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import feign.codec.EncodeException;
import feign.form.FormData;
import feign.form.multipart.Output;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ManyFormDataWriterTest {
    private ManyFormDataWriter manyFormDataWriter;
    private SingleFormDataWriter mockSingleFormDataWriter;
    private Output mockOutput;

    @BeforeEach
    void setUp() {
        mockSingleFormDataWriter = mock(SingleFormDataWriter.class);
        mockOutput = mock(Output.class);
        manyFormDataWriter = new ManyFormDataWriter(mockSingleFormDataWriter);
    }

    @Test
    void isApplicableTest() {
        FormData[] formDataArray = new FormData[]{mock(FormData.class)};
        assertTrue(manyFormDataWriter.isApplicable(formDataArray));

        List<FormData> formDataList = Collections.singletonList(mock(FormData.class));
        assertTrue(manyFormDataWriter.isApplicable(formDataList));

        assertFalse(manyFormDataWriter.isApplicable("Invalid Type"));
        assertFalse(manyFormDataWriter.isApplicable(Collections.emptyList()));
    }

    @Test
    void writeTestWithFormDataArray() throws EncodeException {
        FormData[] formDataArray = new FormData[]{mock(FormData.class), mock(FormData.class)};
        manyFormDataWriter.write(mockOutput, "boundary", "key", formDataArray);

        verify(mockSingleFormDataWriter, times(2)).write(eq(mockOutput), eq("boundary"), eq("key"), any(FormData.class));
    }

    @Test
    void writeTestWithIterable() throws EncodeException {
        List<FormData> formDataList = Arrays.asList(mock(FormData.class), mock(FormData.class));
        manyFormDataWriter.write(mockOutput, "boundary", "key", formDataList);

        verify(mockSingleFormDataWriter, times(2)).write(eq(mockOutput), eq("boundary"), eq("key"), any(FormData.class));
    }

    @Test
    void writeTestWithInvalidType() {
        assertThrows(IllegalArgumentException.class, () ->
                manyFormDataWriter.write(mockOutput, "boundary", "key", "Invalid Type")
        );
    }
}