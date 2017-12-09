package io.vertx.up.kidd.income;

import io.vertx.core.eventbus.Message;
import io.vertx.up.atom.Envelop;

@SuppressWarnings("unchecked")
public class StringImitate extends BaseImitate<String> {

    @Override
    public String request(
            final Message<Envelop> message
    ) {
        return request(message, String.class);
    }

    @Override
    public String request(
            final Message<Envelop> message,
            final int index
    ) {
        return request(message, index, String.class);
    }
}
