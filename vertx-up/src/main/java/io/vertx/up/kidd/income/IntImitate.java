package io.vertx.up.kidd.income;

import io.vertx.core.eventbus.Message;
import io.vertx.up.atom.Envelop;

public class IntImitate extends BaseImitate<Integer> {

    @Override
    public Integer request(
            final Message<Envelop> message
    ) {
        return request(message, Integer.class);
    }

    @Override
    public Integer request(
            final Message<Envelop> message,
            final int index
    ) {
        return request(message, index, Integer.class);
    }
}
