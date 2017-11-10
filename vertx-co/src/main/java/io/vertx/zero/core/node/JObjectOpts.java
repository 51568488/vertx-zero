package io.vertx.zero.core.node;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.Visitor;
import io.vertx.zero.core.ZeroNode;
import io.vertx.zero.core.config.ZeroVertx;
import org.vie.exception.ZeroException;
import org.vie.util.Ensurer;
import org.vie.util.Instance;
import org.vie.util.Jackson;

public class JObjectOpts implements Visitor<JsonObject> {

    private static final ZeroNode<JsonObject> NODE
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
