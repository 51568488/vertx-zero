package io.vertx.up.kidd.outcome;

import io.vertx.core.AsyncResult;
import io.vertx.up.atom.Envelop;
import io.vertx.up.func.Fn;
import io.vertx.up.kidd.Obstain;
import io.vertx.up.kidd.Readible;
import io.vertx.up.log.Annal;

import java.util.List;

/**
 * Base response for outcome request
 */
public abstract class BaseObstain<T> implements Obstain<T> {

    protected Envelop unique(final AsyncResult<T> handler,
                             final Readible internal500,
                             final Readible internal404) {
        return Fn.getSemi(handler.succeeded(), getLogger(),
                // 200. Handler executed successfully
                () -> Fn.getSemi(null == handler.result(), getLogger(),

                        // 1. null == result
                        Failure.build404Flow(getClass(), getLogger(), internal404),

                        // 200. null != result, Success
                        () -> Envelop.success(handler.result())),

                // 500. Internal Error
                Failure.build500Flow(getClass(), handler.cause(), internal500));
    }

    protected Envelop unique(final AsyncResult<List<T>> handler,
                             final Readible internal500,
                             final Readible internal404,
                             final Readible multi) {
        return Fn.getSemi(handler.succeeded(), getLogger(),
                // 200. Handler executed successfully
                () -> null,

                // 500. Internal Error
                Failure.build500Flow(getClass(), handler.cause(), internal500));
    }

    protected Annal getLogger() {
        return Annal.get(getClass());
    }
}
