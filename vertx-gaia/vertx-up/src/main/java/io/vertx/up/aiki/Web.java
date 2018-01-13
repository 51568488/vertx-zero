package io.vertx.up.aiki;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.up.atom.Envelop;

class Web {

    static <T> Handler<AsyncResult<T>> toHandler(
            final Class<?> clazz,
            final Message<Envelop> message
    ) {
        return handler -> {
            if (handler.succeeded()) {
                message.reply(To.toEnvelop(handler.result()));
            } else {
                message.reply(Envelop.failure(To.toError(clazz, handler.cause())));
            }
        };
    }
}
