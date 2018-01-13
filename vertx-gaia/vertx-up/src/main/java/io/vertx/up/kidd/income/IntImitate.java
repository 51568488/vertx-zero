package io.vertx.up.kidd.income;

import io.vertx.core.eventbus.Message;
import io.vertx.up.atom.Envelop;

@Deprecated
public class IntImitate extends BaseImitate<Integer> {

    @Override
    public Integer request(
            final Message<Envelop> message
    ) {
        return this.request(message, Integer.class);
    }

    @Override
    public Integer request(
            final Message<Envelop> message,
            final int index
    ) {
        return this.request(message, index, Integer.class);
    }
}
