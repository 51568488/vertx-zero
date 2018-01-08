package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.vertx.up.tool.mirror.Instance;

import java.io.IOException;

/**
 * @author Lang
 */
public class ClassDeserializer extends JsonDeserializer<Class<?>> { // NOPMD
    /**
     *
     */
    @Override
    public Class<?> deserialize(final JsonParser parser,
                                final DeserializationContext context)
            throws IOException, JsonProcessingException {
        return Instance.clazz(parser.getText());
    }
}
