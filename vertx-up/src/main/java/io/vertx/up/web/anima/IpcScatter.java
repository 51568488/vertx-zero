package io.vertx.up.web.anima;

import io.vertx.core.Vertx;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;

import java.util.concurrent.ConcurrentMap;

public class IpcScatter implements Scatter<Vertx> {
    private static final Node<ConcurrentMap<String, String>> node =
            Instance.singleton(ZeroUniform.class);

    @Override
    public void connect(final Vertx vertx) {
        System.out.println(node.read());
    }
}
