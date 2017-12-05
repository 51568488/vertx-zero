package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.up.tool.mirror.Instance;
import org.junit.Test;

import java.util.concurrent.ConcurrentMap;

public class ZeroLimeTc {

    private transient final Node<ConcurrentMap<String, String>> node
            = Instance.singleton(ZeroLime.class);

    @Test
    public void testGen() {
        final Node<JsonObject> dyanmic = Instance.singleton(ZeroUniform.class);
        final JsonObject data = dyanmic.read();
        System.out.println(data.encodePrettily());
    }
}
