package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonParser;
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
            throws IOException {
        final JsonNode node = parser.getCodec().readTree(parser);
        return Instance.clazz(node.asText().trim());
    }
}
