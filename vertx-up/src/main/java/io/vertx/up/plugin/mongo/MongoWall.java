package io.vertx.up.plugin.mongo;

import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.eon.em.WallType;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.zero.atom.Ruler;
import io.vertx.zero.marshal.Transformer;

public class MongoWall implements Transformer<Cliff> {

    private static final Annal LOGGER = Annal.get(MongoWall.class);

    @Override
    public Cliff transform(final JsonObject input) {
        Fn.flingUp(() -> Ruler.verify("wall-mongo", input), LOGGER);
        final Cliff cliff = new Cliff();
        cliff.setType(WallType.MONGO);
        cliff.setConfig(input.getJsonObject("config"));
        return cliff;
    }
}
