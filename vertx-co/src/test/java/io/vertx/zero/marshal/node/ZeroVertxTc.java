package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.marshal.Node;
import io.vertx.zero.test.UnitBase;
import io.vertx.zero.tool.mirror.Instance;
import org.junit.Test;

import java.util.concurrent.ConcurrentMap;

public class ZeroVertxTc extends UnitBase {

    @Test
    public void testVertxRead() {
        final Node<JsonObject>
                node = Instance.singleton(ZeroVertx.class);
        final JsonObject data = node.read();
    }

    @Test
    public void testLimeRead() {
        final Node<ConcurrentMap<String, String>>
                node = Instance.singleton(ZeroLime.class);
        final ConcurrentMap<String, String> files = node.read();
    }

}
