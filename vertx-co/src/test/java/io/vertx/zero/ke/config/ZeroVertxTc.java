package io.vertx.zero.ke.config;

import com.vie.util.Instance;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.ke.ZeroNode;
import org.junit.Test;
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
