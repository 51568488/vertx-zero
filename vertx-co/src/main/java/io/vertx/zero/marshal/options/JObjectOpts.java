package io.vertx.zero.marshal.options;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.marshal.Node;
import io.vertx.zero.marshal.Visitor;
import io.vertx.zero.marshal.node.ZeroVertx;
import io.vertx.zero.tool.Ensurer;
import io.vertx.zero.tool.mirror.Instance;
import io.vertx.zero.tool.Jackson;

public class JObjectOpts implements Visitor<JsonObject> {

    private static final Node<JsonObject> NODE
            = Instance.singleton(ZeroVertx.class);

    @Override
    public JsonObject visit(final String... nodes)
            throws ZeroException {
        Ensurer.gtLength(getClass(), 0, (Object[]) nodes);
        // Tree Data
        final JsonObject tree = NODE.read();
        return Jackson.visitJObject(tree, nodes);
    }
}
