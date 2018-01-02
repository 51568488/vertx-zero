package io.vertx.up.kidd.outcome;

import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception._500InternalServerException;
import io.vertx.up.func.Fn;
import io.vertx.up.kidd.Readible;
import io.vertx.up.tool.mirror.Instance;

import java.util.function.Supplier;

class Failure {
    /**
     * Build default 500 error
     *
     * @param clazz
     * @param cause
     * @return
     */
    static Supplier<Envelop> build500Flow(
            final Class<?> clazz,
            final Throwable cause) {
        final WebException error = Instance.instance(
                _500InternalServerException.class, clazz,
                Fn.get(null, () -> cause.getMessage(), cause));
        return build(error);
    }

    static Supplier<Envelop> build(
            final WebException error) {
        final Readible readible = Readible.get();
        readible.interpret(error);
        return () -> Envelop.failure(error);
    }
}
