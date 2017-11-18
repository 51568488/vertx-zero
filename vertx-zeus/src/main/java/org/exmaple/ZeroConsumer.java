package org.exmaple;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.atom.Envelop;

public class ZeroConsumer {

    @Address("ZERO://EVENT")
    public Envelop reply(final Envelop message) {
        final JsonObject data = message.data();
        System.out.println(data);
        return Envelop.success(data);
    }
}
