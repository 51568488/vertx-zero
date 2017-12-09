package io.vertx.up.kidd.outcome;

import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception.web._400DuplicatedRecordException;
import io.vertx.up.exception.web._404RecordNotFoundException;
import io.vertx.up.func.Fn;
import io.vertx.up.kidd.Spy;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.eon.Values;

import java.util.ArrayList;
import java.util.List;

public class ListObstain<T> extends Obstain<List<T>> {

    public static <T> ListObstain<T> startList(final Class<?> clazz) {
        return new ListObstain<>(clazz);
    }

    public ListObstain(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public ListObstain<T> unique() {
        final WebException error404 = Instance.instance(
                _404RecordNotFoundException.class, this.clazz);
        final WebException error400 = Instance.instance(
                _400DuplicatedRecordException.class, this.clazz);
        return unique(error404, error400);
    }

    public ListObstain<T> unique(final WebException internal404,
                                 final WebException duplicated) {
        if (this.isReady()) {
            this.envelop = Fn.getSemi(this.handler.succeeded(), this.logger,
                    // 200. Handler executed successfully
                    () -> Fn.getSemi(null == this.handler.result()
                                    || this.handler.result().isEmpty(), this.logger,

                            // 404 -> Response
                            () -> Fn.getSemi(null == internal404, this.logger,
                                    // 200 with empty data
                                    () -> Envelop.success(new ArrayList<>()),
                                    // 404 Error returned
                                    Failure.build(internal404)),

                            // 200 -> Response
                            () -> Fn.getSemi(Values.ONE == this.handler.result().size(), this.logger,

                                    // 200 -> Successfully
                                    () -> Fn.getSemi(null == this.spy, this.logger,
                                            // 200 -> No spy provided
                                            () -> Envelop.success(this.handler.result()),
                                            // 200 -> Spy provided
                                            () -> Envelop.success(this.spy.out(this.handler.result()))),

                                    // 400 -> Duplicated
                                    () -> Fn.getSemi(null == duplicated, this.logger,
                                            // 200 -> Duplicated skipped
                                            () -> Envelop.success(this.handler.result()),
                                            // 400 -> Throw exception
                                            Failure.build(duplicated)))),
                    // 500. Internal Error
                    Failure.build500Flow(this.clazz, this.handler.cause()));
        }
        return this;
    }

    @Override
    public ListObstain<T> connect(final Spy<List<T>> spy) {
        this.spy = spy;
        return this;
    }
}
