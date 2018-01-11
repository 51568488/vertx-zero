package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import io.vertx.up.aiki.Blade;

import java.io.IOException;

/**
 * @author Lang
 */
public class BladeSerializer extends JsonSerializer<Blade> {
    @Override
    public void serialize(final Blade blade,
                          final JsonGenerator jgen,
                          final SerializerProvider provider) throws IOException {
        jgen.writeObject(blade.to().getMap());
    }
}
