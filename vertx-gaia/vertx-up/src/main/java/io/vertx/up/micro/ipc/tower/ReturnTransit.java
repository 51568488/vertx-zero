package io.vertx.up.micro.ipc.tower;

import io.vertx.core.Future;
import io.vertx.up.atom.Envelop;

/**
 * Different return value to build Future<Envelop>
 */
class ReturnTransit {

    @SuppressWarnings("unchecked")
    static Future<Envelop> build(final Object returnValue) {
        if (null == returnValue) {
            // Empty Future
            return Future.succeededFuture(Envelop.ok());
        }
        final Class<?> clazz = returnValue.getClass();
        final Future<Envelop> result;
        if (Future.class == clazz) {
            result = (Future<Envelop>) returnValue;
        } else {
            if (Envelop.class == clazz) {
                // Envelop got
                result = Future.succeededFuture((Envelop) returnValue);
            } else {
                final Envelop envelop = Envelop.success(returnValue);
                result = Future.succeededFuture(envelop);
            }
        }
        return result;
    }
}
