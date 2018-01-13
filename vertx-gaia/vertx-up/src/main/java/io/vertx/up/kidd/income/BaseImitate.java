package io.vertx.up.kidd.income;

import io.vertx.core.eventbus.Message;
import io.vertx.up.atom.Envelop;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;

/**
 * Abstract imitate to process request
 */
@Deprecated
public abstract class BaseImitate<T> implements Imitate<T> {

    protected T request(
            final Message<Envelop> message,
            final Class<T> clazz
    ) {
        final Envelop body = message.body();
        return Fn.getSemi(null == body, this.getLogger(), Fn::nil,
                () -> body.data(clazz));
    }

    protected T request(
            final Message<Envelop> message,
            final Integer index,
            final Class<T> clazz) {
        final Envelop body = message.body();
        return Fn.getSemi(null == body, this.getLogger(), Fn::nil,
                () -> body.data(index, clazz));
    }

    protected Annal getLogger() {
        return Annal.get(this.getClass());
    }
}
