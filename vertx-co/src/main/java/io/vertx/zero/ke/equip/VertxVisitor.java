package io.vertx.zero.ke.equip;

import com.vie.hors.ZeroException;
import com.vie.log.Annal;
import com.vie.util.Ensurer;
import com.vie.util.Instance;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.ke.ZeroNode;
import io.vertx.zero.ke.config.ZeroVertx;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class VertxVisitor implements UprightVisitor {
    private static final Annal LOGGER = Annal.get(HttpServerVisitor.class);

    private transient final ZeroNode<JsonObject> NODE
            = Instance.singleton(ZeroVertx.class);

    @Override
    public ConcurrentMap<String, VertxOptions> visit(final String... keys)
            throws ZeroException {
        // 1. Must be the first line, fixed position.
        Ensurer.eqLength(getClass(), 0, keys);
        // 2. Visit the node for vertx
        final JsonObject data = this.NODE.read();
        System.out.println(data);
        return null;
    }

    private ConcurrentMap<String, VertxOptions> visit(final JsonArray vertxData)
            throws ZeroException {
        final ConcurrentMap<String, VertxOptions> map =
                new ConcurrentHashMap<>();

        return map;
    }
}
