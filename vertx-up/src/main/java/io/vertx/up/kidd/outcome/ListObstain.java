package io.vertx.up.kidd.outcome;

import io.vertx.up.atom.Envelop;
import io.vertx.up.func.Fn;
import io.vertx.up.kidd.Readible;
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
    public ListObstain<T> unique(final Readible readible, final Readible internal404) {
        return unique(readible, internal404, null);
    }

    public ListObstain<T> unique(final Readible readible, final Readible internal404, final Readible duplicated) {
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
                                    Failure.build404Flow(this.clazz, internal404)),

                            // 200 -> Response
                            () -> Fn.getSemi(Values.ONE == this.handler.result().size(), this.logger,

                                    // 200 -> Successfully
                                    () -> Fn.getSemi(null == this.spy, this.logger,
                                            // 200 -> No spy provided
                                            () -> Envelop.success(this.handler.result()),
                                            // 200 -> Spy provided
                                            () -> Envelop.success(this.spy.out(this.handler.result()))),

                                    // 400 -> Duplicated
                                    Failure.build400Flow(this.clazz, duplicated))),
                    // 500. Internal Error
                    Failure.build500Flow(this.clazz, this.handler.cause(), readible));
        }
        return this;
    }
}
