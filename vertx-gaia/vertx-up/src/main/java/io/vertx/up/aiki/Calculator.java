package io.vertx.up.aiki;

import io.reactivex.Observable;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

class Calculator {

    static void appendJson(final JsonObject target, final JsonObject source) {
        Observable.fromIterable(source.fieldNames())
                .filter(key -> !target.containsKey(key))
                .subscribe(key -> target.put(key, source.getValue(key)));
    }

    static JsonObject groupBy(final JsonArray array, final String field) {
        final JsonObject result = new JsonObject();
        Observable.fromIterable(array)
                .map(item -> (JsonObject) item)
                .groupBy(item -> item.getValue(field))
                .subscribe(item -> {
                    final JsonArray values = new JsonArray();
                    item.subscribe(values::add);
                    result.put(item.getKey().toString(), values);
                });
        return result;
    }
}
