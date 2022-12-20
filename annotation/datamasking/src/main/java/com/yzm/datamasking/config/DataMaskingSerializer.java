package com.yzm.datamasking.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.util.Objects;

public final class DataMaskingSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private DataMaskingOperation operation;
    private String maskChar;

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        DataMasking annotation = beanProperty.getAnnotation(DataMasking.class);
        if (Objects.isNull(annotation)) return null;

        this.operation = annotation.maskFunc().operation();
        this.maskChar = annotation.maskChar();
        return this;
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String content = value;
        if (Objects.nonNull(operation)) {
            content = operation.mask(value, maskChar);
        }
        gen.writeString(content);
    }
}
