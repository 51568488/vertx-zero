package org.exmaple;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.ce.Envelop;

@Queue
public class ZeroConsumer {

    @Address("ZERO://EVENT")
    public Envelop reply(final Envelop message) {
        final JsonObject data = message.data();
        System.out.println(data);
        return Envelop.success(data);
    }
}
