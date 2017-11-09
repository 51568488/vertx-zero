package io.vertx.zero.core.config;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.ZeroNode;
import org.junit.Test;
import org.vie.util.Instance;
import top.UnitBase;

import java.util.concurrent.ConcurrentMap;

public class ZeroVertxTc extends UnitBase {

    @Test
    public void testVertxRead() {
        final ZeroNode<JsonObject>
                node = Instance.singleton(ZeroVertx.class);
        final JsonObject data = node.read();
    }

    @Test
    public void testLimeRead() {
        final ZeroNode<ConcurrentMap<String, String>>
                node = Instance.singleton(ZeroLime.class);
        final ConcurrentMap<String, String> files = node.read();
    }

}
