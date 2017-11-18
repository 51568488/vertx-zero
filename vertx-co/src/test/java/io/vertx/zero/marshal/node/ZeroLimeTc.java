package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.marshal.Node;
import io.vertx.zero.tool.mirror.Instance;
import org.junit.Test;

import java.util.concurrent.ConcurrentMap;

public class ZeroLimeTc {

    private transient final Node<ConcurrentMap<String, String>> node
            = Instance.singleton(ZeroLime.class);

    @Test
    public void testGen() {
        final Node<JsonObject> dyanmic = Instance.singleton(ZeroDynamic.class);
        final JsonObject data = dyanmic.read();
        System.out.println(data.encodePrettily());
    }
}
