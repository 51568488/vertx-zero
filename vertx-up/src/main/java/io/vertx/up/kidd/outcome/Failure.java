package io.vertx.up.kidd.outcome;

import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception.web._400DuplicatedRecordException;
import io.vertx.up.exception.web._404RecordNotFoundException;
import io.vertx.up.exception.web._500InternalServerException;
import io.vertx.up.func.Fn;
import io.vertx.up.kidd.Readible;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Instance;

import java.util.function.Supplier;

class Failure {
    /**
     * Build default 500 error
     *
     * @param clazz
     * @param cause
     * @param error500
     * @return
     */
    static Supplier<Envelop> build500Flow(
            final Class<?> clazz,
            final Throwable cause,
            final Readible error500) {
        final WebException error = Instance.instance(
                _500InternalServerException.class, clazz,
                Fn.get(null, () -> cause.getMessage(), cause));
        Fn.safeNull(() -> error500.interpret(error), error500);
        return () -> Envelop.failure(error);
    }

    /**
     * Build default 404 error
     *
     * @param clazz
     * @param internal404
     * @return
     */
    static Supplier<Envelop> build404Flow(
            final Class<?> clazz,
            final Readible internal404
    ) {
        final Annal logger = Annal.get(clazz);
        final WebException error = Instance.instance(
                _404RecordNotFoundException.class, clazz);
        Fn.safeNull(() -> internal404.interpret(error), internal404);
        return () -> Fn.getSemi(null == internal404, logger,
                // 200. Should not throw error
                Envelop::ok,
                // 404. Should throw error
                () -> Envelop.failure(error));
    }

    /**
     * @param clazz
     * @param internal400
     * @return
     */
    static Supplier<Envelop> build400Flow(
            final Class<?> clazz,
            final Readible internal400
    ) {
        final Annal logger = Annal.get(clazz);
        final WebException error = Instance.instance(
                _400DuplicatedRecordException.class, clazz);
        Fn.safeNull(() -> internal400.interpret(error), internal400);
        return () -> Fn.getSemi(null == internal400, logger,
                // 200. Should not throw error
                Envelop::ok,
                // 404. Should throw error
                () -> Envelop.failure(error));
    }
}
