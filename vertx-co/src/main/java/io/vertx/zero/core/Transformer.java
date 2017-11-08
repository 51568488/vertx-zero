package io.vertx.zero.core;

import io.vertx.core.json.JsonObject;

public interface Transformer<T> {

    T transform(JsonObject input);
}
