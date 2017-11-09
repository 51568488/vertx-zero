package io.vertx.zero.core.node;

import com.vie.util.Ensurer;
import com.vie.util.Instance;
import com.vie.util.Jackson;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.Visitor;
import io.vertx.zero.core.ZeroNode;
import io.vertx.zero.core.config.ZeroVertx;

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
