package io.vertx.up.kidd;

import io.vertx.core.json.JsonObject;
import io.vertx.up.kidd.id.ReactSpy;
import io.vertx.up.kidd.id.ReactsSpy;
import io.vertx.up.kidd.outcome.ListObstain;
import io.vertx.up.kidd.outcome.Obstain;
import io.vertx.up.tool.mirror.Instance;

import java.util.List;

/**
 * Factory to build center controller
 */
public final class Heart {

    public static Obstain<JsonObject> getReact(final Class<?> clazz) {
        final Spy<JsonObject> spy = Instance.singleton(ReactSpy.class);
        return Obstain.<JsonObject>start(clazz)
                .connect(spy);
    }

    public static ListObstain<JsonObject> getReacts(final Class<?> clazz) {
        final Spy<List<JsonObject>> spy = Instance.singleton(ReactsSpy.class);
        return ListObstain.<JsonObject>startList(clazz).connect(spy);
    }
}
