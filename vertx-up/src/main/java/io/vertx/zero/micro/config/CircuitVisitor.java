package io.vertx.zero.micro.config;

import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Micro;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Ensurer;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.atom.Ruler;
import io.vertx.zero.eon.Info;
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
        // 3. CircuitBreakerOptions building.
        final JsonObject config =
                Fn.getSemi(data.containsKey(Micro.CIRCUIT) &&
                                null != data.getValue(Micro.CIRCUIT), LOGGER,
                        () -> data.getJsonObject(Micro.CIRCUIT),
                        JsonObject::new);
        // 4. Verify the configuration data
        return visit(config);
    }

    private CircuitBreakerOptions visit(final JsonObject data)
            throws ZeroException {
        LOGGER.info(Info.INF_B_VERIFY, Micro.CIRCUIT, "Micro - Circuit", data.encode());
        Ruler.verify("circuit", data);
        return new CircuitBreakerOptions(data);
    }
}
