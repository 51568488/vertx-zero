package io.vertx.up.plugin.mongo;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.mongo.UpdateOptions;
import io.vertx.up.concurrent.Runner;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.StringUtil;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.exception.XtorConnectException;
import io.vertx.zero.exception.XtorExecuteException;
import io.vertx.zero.exception.XtorNotReadyException;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Tool for mongodb, simply the writing with MongoClient
 * Write thread executor -> Wtor
 */
@Deprecated
public class MongoWtor {

    private static final Annal LOGGER = Annal.get(MongoWtor.class);
    private transient final MongoClient client;

    private transient Class<?> hitted;
    private transient Annal logger;
    private transient String collection;
    private transient UpdateOptions option;
    private transient Function aggregate;

    public static MongoWtor init(final MongoClient client) {
        return new MongoWtor(client);
    }

    private MongoWtor(final MongoClient client) {
        // Invalid constructor
        Fn.flingUp(null == client, LOGGER,
                XtorConnectException.class, this.getClass(),
                "client = " + client, "constructor(MongoClient)");
        this.client = client;
    }

    @Fluent
    public MongoWtor connect(final Class<?> clazz) {
        // Invalid connect
        Fn.flingUp(null == clazz, LOGGER,
                XtorConnectException.class, this.getClass(),
                "hitted = " + clazz, "connect(Class)");
        this.hitted = clazz;
        this.logger = (null == clazz) ? Annal.get(MongoWtor.class) : Annal.get(clazz);
        return this;
    }

    @Fluent
    public MongoWtor connect(final String collection) {
        // Invalid connect
        Fn.flingUp(StringUtil.isNil(collection), LOGGER,
                XtorConnectException.class, this.getClass(),
                "collection = " + collection, "connect(String)");
        this.collection = collection;
        return this;
    }

    @Fluent
    public MongoWtor connect(final UpdateOptions option) {
        this.option = option;
        return this;
    }

    @Fluent
    public <T, R> MongoWtor connect(final Function<T, R> aggregate) {
        if (null != aggregate) {
            this.aggregate = aggregate;
        }
        return this;
    }

    /**
     * Decreased/Increase Operation
     *
     * @param condition
     * @param field
     * @return
     */
    public JsonObject decreaseBy(
            final JsonObject condition,
            final String field,
            final int step
    ) {
        return this.execute(condition, field, this.decrease(step));
    }

    public JsonObject decreaseBy(
            final JsonObject condition,
            final String field
    ) {
        return this.execute(condition, field, this.decrease(1));
    }

    public JsonObject increaseBy(
            final JsonObject condition,
            final String field,
            final int step
    ) {
        return this.execute(condition, field, this.increase(step));
    }

    public JsonObject increaseBy(
            final JsonObject condition,
            final String field
    ) {
        return this.execute(condition, field, this.increase(1));
    }

    /**
     * 1. Query data by condition.
     * 2. Update the data with condition.
     * 3. If there exist itemFuns, process each field by function.
     *
     * @param condition
     * @param latest
     * @param itemFuns
     * @return
     */
    public JsonObject write(
            final JsonObject condition,
            final JsonObject latest,
            final ConcurrentMap<String, BiFunction<Object, Object, Object>> itemFuns
    ) {
        this.ensure();
        return Fn.getJvm(new JsonObject(), () -> {
            // Single thread counter
            final CountDownLatch counter = new CountDownLatch(1);
            final JsonObject data = new JsonObject();
            Runner.run(() -> {
                // If itemFuns is empty, it means directly update to latest by condition
                this.logger.debug(Info.UPDATE_INFO, this.collection, condition, latest);
                if (itemFuns.isEmpty()) {
                    this.logger.info(Info.UPDATE_FLOW, "( Pure Update )", condition, latest);
                    this.client.findOneAndUpdate(this.collection, condition, latest, res -> {
                        if (res.succeeded()) {
                            // Result: Update directly successfully
                            data.mergeIn(res.result());
                        } else {
                            Fn.flingUp(true, LOGGER,
                                    XtorExecuteException.class,
                                    this.getClass(), this.cause(res.cause()));
                        }
                        counter.countDown();
                    });
                } else {
                    this.client.findOne(this.collection, condition, null, res -> {
                        if (res.succeeded()) {
                            // Old data
                            final JsonObject oldData = res.result();
                            this.logger.debug(Info.UPDATE_QUERY, this.collection, condition, oldData);
                            final JsonObject newData = new JsonObject();
                            if (null != oldData) {
                                for (final String field : oldData.fieldNames()) {
                                    // iterator
                                    Object value = oldData.getValue(field);
                                    if (itemFuns.containsKey(field)) {
                                        // Function existing
                                        final BiFunction<Object, Object, Object> fun = itemFuns.get(field);
                                        final Object oldValue = oldData.getValue(field);
                                        value = fun.apply(oldValue, value);
                                    } else {
                                        // New -> Old
                                        if (latest.containsKey(field)) {
                                            value = latest.getValue(field);
                                        }
                                    }
                                    newData.put(field, value);
                                }
                                this.logger.info(Info.UPDATE_FLOW, "( Complex Update )", condition, newData);
                                // Update with latest
                                this.client.findOneAndReplace(this.collection, condition, newData, inner -> {
                                    if (inner.succeeded()) {
                                        // Result: Update successfully
                                        data.mergeIn(inner.result());
                                    } else {
                                        Fn.flingUp(true, LOGGER,
                                                XtorExecuteException.class,
                                                this.getClass(), this.cause(res.cause()));
                                    }
                                    counter.countDown();
                                });
                            } else {
                                // No old data, it means that nothing happened.
                                counter.countDown();
                            }
                        } else {
                            Fn.flingUp(true, LOGGER,
                                    XtorExecuteException.class,
                                    this.getClass(), this.cause(res.cause()));
                            counter.countDown();
                        }
                    });
                }
            }, "concurrent-update");
            return data;
        }, itemFuns, condition, latest);
    }

    private String cause(final Throwable error) {
        return null != error ? error.getMessage() : Strings.EMPTY;
    }

    private void ensure() {
        Fn.flingUp(null == this.client || null == this.collection ||
                        null == this.hitted || null == this.logger, LOGGER,
                XtorNotReadyException.class, this.getClass());
    }

    private JsonObject execute(
            final JsonObject condition,
            final String field,
            final BiFunction<Object, Object, Object> func
    ) {
        final ConcurrentMap<String, BiFunction<Object, Object, Object>> funcMap =
                new ConcurrentHashMap<String, BiFunction<Object, Object, Object>>() {
                    {
                        this.put(field, func);
                    }
                };
        return this.write(condition, new JsonObject(), funcMap);
    }

    private BiFunction<Object, Object, Object> increase(final int step) {
        return (oldVal, newVale) -> Fn.getJvm(0,
                () -> Integer.parseInt(oldVal.toString()) + step, oldVal);
    }

    private BiFunction<Object, Object, Object> decrease(final int step) {
        return (oldVal, newValue) -> Fn.getJvm(0,
                () -> Integer.parseInt(oldVal.toString()) - 1, oldVal);
    }
}
