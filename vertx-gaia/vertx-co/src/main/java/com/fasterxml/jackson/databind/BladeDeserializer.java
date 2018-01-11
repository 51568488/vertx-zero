package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonParser;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.Blade;

import java.io.IOException;

/**
 * @author Lang
 */
public class BladeDeserializer extends JsonDeserializer<Blade> { // NOPMD
    /**
     *
     */
    @Override
    public Blade deserialize(final JsonParser parser,
                             final DeserializationContext context)
            throws IOException {
        final JsonNode node = parser.getCodec().readTree(parser);
        return Blade.create(new JsonObject(node.toString()));
    }
}
