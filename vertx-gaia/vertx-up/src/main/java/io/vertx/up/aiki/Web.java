package io.vertx.up.aiki;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Envelop;

class Web {

    static <T> Handler<AsyncResult<T>> toHandler(
            final Message<Envelop> message
    ) {
        return handler -> {
            if (handler.succeeded()) {
                message.reply(To.toEnvelop(handler.result()));
            } else {
                // Readible codec for configured information, error flow needed.
                message.reply(Envelop.failure(To.toError(Web.class, handler.cause())));
            }
        };
    }

    static <T> Handler<AsyncResult<Boolean>> toHandler(
            final Message<Envelop> message,
            final JsonObject data
    ) {
        return handler -> {
            if (handler.succeeded() && handler.result()) {
                message.reply(To.toEnvelop(data));
            } else {
                message.reply(To.toEnvelop(Boolean.FALSE));
            }
        };
    }
}
