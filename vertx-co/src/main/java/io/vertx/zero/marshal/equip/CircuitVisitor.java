package io.vertx.zero.marshal.equip;

import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Plugins;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Ensurer;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.marshal.Visitor;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;

public class CircuitVisitor implements Visitor<CircuitBreakerOptions> {

    private static final Annal LOGGER = Annal.get(CircuitVisitor.class);
    private final transient Node<JsonObject> node =
            Instance.singleton(ZeroUniform.class);

    @Override
    public CircuitBreakerOptions visit(final String... key)
            throws ZeroException {
        // 1. Must be the first line, fixed position.
        Ensurer.eqLength(getClass(), 0, (Object[]) key);
        // 2. Read data
        final JsonObject data = this.node.read();
        // 2. CircuitBreakerOptions building.
        final JsonObject config =
                Fn.getSemi(data.containsKey(Plugins.Micro.CIRCUIT) &&
                                null != data.getValue(Plugins.Micro.CIRCUIT), LOGGER,
                        () -> data.getJsonObject(Plugins.Micro.CIRCUIT),
                        JsonObject::new);
        return new CircuitBreakerOptions(config);
    }
}
