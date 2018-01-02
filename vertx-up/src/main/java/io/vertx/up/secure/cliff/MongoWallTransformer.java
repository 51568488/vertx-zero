package io.vertx.up.secure.cliff;

import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.zero.atom.Ruler;
import io.vertx.zero.marshal.Transformer;

public class MongoWallTransformer implements Transformer<Cliff> {

    private static final Annal LOGGER = Annal.get(MongoWallTransformer.class);

    @Override
    public Cliff transform(final JsonObject input) {
        Fn.flingUp(() -> Ruler.verify("wall-mongo", input), LOGGER);
        return build(input);
    }

    private Cliff build(final JsonObject input) {
        final Cliff cliff = new Cliff();
        System.out.println(input);
        return cliff;
    }
}
