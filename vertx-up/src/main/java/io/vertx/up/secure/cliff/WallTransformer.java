package io.vertx.up.secure.cliff;

import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.eon.em.WallType;
import io.vertx.up.func.Fn;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.marshal.Transformer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WallTransformer implements Transformer<Cliff> {

    private static final ConcurrentMap<WallType, Transformer<Cliff>>
            WALL_TRANSFORMER = new ConcurrentHashMap<WallType, Transformer<Cliff>>() {
        {
            put(WallType.MONGO, Instance.singleton(MongoWallTransformer.class));
        }
    };

    @Override
    public Cliff transform(final JsonObject input) {
        return Fn.getJvm(() -> {
            if (input.containsKey(Wall.TYPE)) {
                // Standard
                final Transformer<Cliff> transformer =
                        WALL_TRANSFORMER.get(WallType.valueOf(input.getString(Wall.TYPE)));
                return transformer.transform(input);
            } else {
                // Non Standard
                return new Cliff();
            }
        }, input);
    }
}
