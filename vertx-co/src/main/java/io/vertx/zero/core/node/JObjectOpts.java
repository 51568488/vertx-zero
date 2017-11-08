package io.vertx.zero.core.node;

import com.vie.exception.ZeroException;
import com.vie.util.Ensurer;
import com.vie.util.Instance;
import com.vie.util.Jackson;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.Visitor;
import io.vertx.zero.core.ZeroNode;
import io.vertx.zero.core.config.ZeroVertx;

public class JObjectOpts implements Visitor<JsonObject> {

    private static final ZeroNode<JsonObject> NODE
            = Instance.singleton(ZeroVertx.class);

    @Override
    public JsonObject visit(final String... nodes)
            throws ZeroException {
        Ensurer.gtLength(getClass(), 0, nodes);
        // Tree Data
        final JsonObject tree = NODE.read();
        return Jackson.visitJObject(tree, nodes);
    }
}
