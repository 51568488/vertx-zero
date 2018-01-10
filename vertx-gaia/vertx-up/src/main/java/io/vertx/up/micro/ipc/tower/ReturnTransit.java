package io.vertx.up.micro.ipc.tower;

import io.vertx.core.Future;
import io.vertx.up.atom.Envelop;
import io.vertx.up.log.Annal;

/**
 * Different return value to build Future<Envelop>
 */
class ReturnTransit {

    private static final Annal LOGGER = Annal.get(ReturnTransit.class);

    @SuppressWarnings("unchecked")
    static Future<Envelop> build(final Object returnValue) {
        if (null == returnValue) {
            // Empty Future
            return Future.succeededFuture(Envelop.ok());
        }
        final Class<?> clazz = returnValue.getClass();
        final Future<Envelop> result;
        if (Future.class.isAssignableFrom(clazz)) {
            // Fix Async Server Issue
            LOGGER.info(Info.MSG_FLOW, "Future", clazz);
            result = (Future<Envelop>) returnValue;
        } else {
            if (Envelop.class == clazz) {
                // Envelop got
                LOGGER.info(Info.MSG_FLOW, "Envelop", clazz);
                result = Future.succeededFuture((Envelop) returnValue);
            } else {
                LOGGER.info(Info.MSG_FLOW, "Other Type", clazz);
                final Envelop envelop = Envelop.success(returnValue);
                result = Future.succeededFuture(envelop);
            }
        }
        return result;
    }
}
