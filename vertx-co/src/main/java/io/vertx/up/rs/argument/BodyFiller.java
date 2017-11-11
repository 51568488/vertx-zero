package io.vertx.up.rs.argument;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.rs.Filler;
import org.vie.util.Jackson;

import java.util.LinkedHashMap;

public class BodyFiller implements Filler {
    @Override
    public Object apply(final String name,
                        final Class<?> paramType,
                        final RoutingContext context) {
        final Object returnValue;
        if (JsonArray.class == paramType) {
            // JsonArray
            returnValue = Jackson.validJArray(context::getBodyAsJsonArray);
        } else if (JsonObject.class == paramType ||
                LinkedHashMap.class == paramType) {
            // JsonObject
            returnValue = Jackson.validJObject(context::getBodyAsJson);
        } else if (Buffer.class == paramType) {
            // Buffer
            returnValue = context.getBody();
        } else if (String.class == paramType) {
            // String
            returnValue = context.getBodyAsString();
        } else {
            // Object and other
            final String content = context.getBodyAsString();
            returnValue = Jackson.deserialize(content, paramType);
        }
        return returnValue;
    }
}
