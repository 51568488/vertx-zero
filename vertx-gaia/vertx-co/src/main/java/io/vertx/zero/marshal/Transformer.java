package io.vertx.zero.marshal;

import io.vertx.core.json.JsonObject;

public interface Transformer<T> {

    T transform(JsonObject input);
}
