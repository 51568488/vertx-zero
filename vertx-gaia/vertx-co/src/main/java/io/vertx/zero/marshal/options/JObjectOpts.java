package io.vertx.zero.marshal.options;

import io.vertx.core.json.JsonObject;
import io.vertx.up.tool.Ensurer;
import io.vertx.up.tool.Jackson;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.marshal.Visitor;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroVertx;

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
