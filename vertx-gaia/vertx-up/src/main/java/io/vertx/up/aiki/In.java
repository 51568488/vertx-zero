package io.vertx.up.aiki;

import io.vertx.core.eventbus.Message;
import io.vertx.up.atom.Envelop;
import io.vertx.up.func.Fn;

class In {

    static <T> T request(
            final Message<Envelop> message,
            final Class<T> clazz
    ) {
        final Envelop body = message.body();
        return Fn.getSemi(null == body, null, Fn::nil,
                () -> body.data(clazz));
    }

    static <T> T request(
            final Message<Envelop> message,
            final Integer index,
            final Class<T> clazz) {
        final Envelop body = message.body();
        return Fn.getSemi(null == body, null, Fn::nil,
                () -> body.data(index, clazz));
    }
}
