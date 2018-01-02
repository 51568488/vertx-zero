package io.vertx.up.plugin.mongo;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.mongo.impl.MongoAuthImpl;
import io.vertx.ext.auth.mongo.impl.MongoUser;
import io.vertx.ext.web.handler.impl.BasicAuthHandlerImpl;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.eon.em.WallType;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.atom.Ruler;
import io.vertx.zero.marshal.Transformer;

public class MongoRampart implements Transformer<Cliff> {

    private static final Annal LOGGER = Annal.get(MongoRampart.class);

    @Override
    public Cliff transform(final JsonObject input) {
        Fn.flingUp(() -> Ruler.verify("wall-mongo", input), LOGGER);
        final Cliff cliff = new Cliff();
        cliff.setType(WallType.MONGO);
        // Fixed/Constant Class Values, ignore other configuration.
        cliff.setUser(MongoUser.class);
        // Must not be interface, provider/handler must be implementation classes instead.
        cliff.setProvider(MongoAuthImpl.class);
        cliff.setHandler(BasicAuthHandlerImpl.class);
        cliff.setSecreter(Instance.singleton(MongoSecreter.class));
        // Config
        cliff.setConfig(input.getJsonObject("config"));
        return cliff;
    }
}
