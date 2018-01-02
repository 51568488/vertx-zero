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
        return Fn.safeZero(() -> {
            // 1. Verify
            Ruler.verify("wall-mongo", input);
            // 2. Convert
            return build(input);
        }, LOGGER);
    }

    private Cliff build(final JsonObject input) {
        return new Cliff();
    }
}
