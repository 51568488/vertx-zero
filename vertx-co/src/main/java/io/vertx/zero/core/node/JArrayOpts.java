package io.vertx.zero.core.node;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.Visitor;
import io.vertx.zero.core.ZeroNode;
import io.vertx.zero.core.config.ZeroVertx;
import org.vie.util.Ensurer;
import org.vie.util.Instance;
import org.vie.util.Jackson;

public class JArrayOpts implements Visitor<JsonArray> {

    private static final ZeroNode<JsonObject> NODE
            = Instance.singleton(ZeroVertx.class);

    @Override
    public JsonArray visit(final String... nodes) {
        Ensurer.gtLength(getClass(), 0, (Object[]) nodes);
        final JsonObject tree = NODE.read();
        return Jackson.visitJArray(tree, nodes);
    }
}
