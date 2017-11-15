package io.vertx.zero.marshal.options;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.marshal.Node;
import io.vertx.zero.marshal.Visitor;
import io.vertx.zero.marshal.node.ZeroVertx;
import io.vertx.zero.tool.Ensurer;
import io.vertx.zero.tool.Jackson;
import io.vertx.zero.tool.mirror.Instance;

public class JArrayOpts implements Visitor<JsonArray> {

    private static final Node<JsonObject> NODE
            = Instance.singleton(ZeroVertx.class);

    @Override
    public JsonArray visit(final String... nodes) {
        Ensurer.gtLength(getClass(), 0, (Object[]) nodes);
        final JsonObject tree = NODE.read();
        return Jackson.visitJArray(tree, nodes);
    }
}
