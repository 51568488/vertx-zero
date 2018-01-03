package io.vertx.up.plugin.mongo;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.mongo.MongoAuth;
import io.vertx.ext.web.handler.BasicAuthHandler;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.eon.em.WallType;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.zero.atom.Ruler;
import io.vertx.zero.marshal.Transformer;

public class MongoRampart implements Transformer<Cliff> {

    private static final Annal LOGGER = Annal.get(MongoRampart.class);

    @Override
    public Cliff transform(final JsonObject input) {
        Fn.flingUp(() -> Ruler.verify("wall-mongo", input), LOGGER);
        final Cliff cliff = new Cliff();
        cliff.setType(WallType.MONGO);
        cliff.setConfig(input.getJsonObject("config"));
        cliff.setHandler(() -> {
            // Set detail for provider processing.
            final MongoAuth auth =
                    MongoAuth.create(MongoInfix.getClient(),
                            cliff.getConfig());
            // Mount data for authenticate
            return BasicAuthHandler.create(auth);
        });
        return cliff;
    }
}
