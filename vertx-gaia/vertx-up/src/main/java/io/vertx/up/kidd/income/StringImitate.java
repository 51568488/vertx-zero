package io.vertx.up.kidd.income;

import io.vertx.core.eventbus.Message;
import io.vertx.up.atom.Envelop;

@Deprecated
public class StringImitate extends BaseImitate<String> {

    @Override
    public String request(
            final Message<Envelop> message
    ) {
        return this.request(message, String.class);
    }

    @Override
    public String request(
            final Message<Envelop> message,
            final int index
    ) {
        return this.request(message, index, String.class);
    }
}
