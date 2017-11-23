package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.quiz.ZeroBase;
import io.vertx.zero.tool.mirror.Instance;
import org.junit.Test;

import java.util.concurrent.ConcurrentMap;

public class ZeroVertxTc extends ZeroBase {

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
