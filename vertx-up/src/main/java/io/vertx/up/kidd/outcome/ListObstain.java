package io.vertx.up.kidd.outcome;

import io.vertx.core.AsyncResult;
import io.vertx.core.json.JsonArray;
import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception._400DuplicatedRecordException;
import io.vertx.up.exception._404RecordNotFoundException;
import io.vertx.up.func.Fn;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.eon.Values;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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

    public <E> ListObstain<T> result() {
        /**
         * Convert function for each item of JsonObject
         * Fix issue: map, empty for List<JsonObject> type serialization
         */
        final Function<List<T>, JsonArray> convert = (from) -> {
            final JsonArray array = new JsonArray();
            from.forEach(array::add);
            return array;
        };
        if (this.isReady()) {
            this.envelop = Fn.getSemi(this.handler.succeeded(), this.logger,
                    // 200. Handler executed successfully
                    () -> Fn.getSemi(null == this.handler.result(), this.logger,
                            // 200. Result
                            () -> Envelop.success(new ArrayList<>()),
                            // 200. Result
                            () -> Envelop.success(convert.apply(this.handler.result()))),
                    // 500. Internal Error
                    Failure.build500Flow(this.clazz, this.handler.cause()));
        }
        return this;
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
                                    () -> Envelop.success(this.handler.result()),

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
    /**
     * Connect to message handler
     *
     * @param handler
     * @return
     */
    public ListObstain<T> connect(final AsyncResult<List<T>> handler) {
        Fn.safeSemi(null == handler, this.logger,
                () -> this.logger.error(Info.ERROR_HANDLER, handler, this.clazz));
        this.handler = handler;
        return this;
    }
}
