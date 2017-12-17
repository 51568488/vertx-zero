package io.vertx.up.plugin.mongo;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.mongo.UpdateOptions;
import io.vertx.up.concurrent.Runner;
import io.vertx.up.exception.XtorConnectException;
import io.vertx.up.exception.XtorExecuteException;
import io.vertx.up.exception.XtorNotReadyException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.StringUtil;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Tool for mongodb, simply the writing with MongoClient
 * Write thread executor -> Wtor
 */
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
                XtorConnectException.class, getClass(),
                "client = " + client, "constructor(MongoClient)");
        this.client = client;
    }

    public MongoWtor connect(final Class<?> clazz) {
        // Invalid connect
        Fn.flingUp(null == clazz, LOGGER,
                XtorConnectException.class, getClass(),
                "hitted = " + clazz, "connect(Class)");
        this.hitted = clazz;
        this.logger = (null == clazz) ? Annal.get(MongoWtor.class) : Annal.get(clazz);
        return this;
    }

    public MongoWtor connect(final String collection) {
        // Invalid connect
        Fn.flingUp(StringUtil.isNil(collection), LOGGER,
                XtorConnectException.class, getClass(),
                "collection = " + collection, "connect(String)");
        this.collection = collection;
        return this;
    }

    public MongoWtor connect(final UpdateOptions option) {
        this.option = option;
        return this;
    }

    public <T, R> MongoWtor connect(final Function<T, R> aggregate) {
        if (null != aggregate) {
            this.aggregate = aggregate;
        }
        return this;
    }

    /**
     * 1. Query data by condition.
     * 2. Update the data with condition.
     * 3. If there exist itemFuns, process each field by function.
     *
     * @param condition
     * @param latest
     * @param itemFuns
     * @param <T>
     * @return
     */
    public <T> JsonObject manorBy(
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
                if (itemFuns.isEmpty()) {
                    this.client.findOneAndUpdate(this.collection, condition, latest, res -> {
                        if (res.succeeded()) {
                            // Result: Update directly successfully
                            data.mergeIn(res.result());
                        } else {
                            Fn.flingUp(true, LOGGER,
                                    XtorExecuteException.class,
                                    getClass(), res.cause().getMessage());
                        }
                        counter.countDown();
                    });
                } else {
                    this.client.findOne(this.collection, condition, null, res -> {
                        if (res.succeeded()) {
                            // Old data
                            final JsonObject oldData = res.result();
                            final JsonObject newData = new JsonObject();
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
                                // Update with latest
                                this.client.updateCollection(this.collection, condition, newData, inner -> {
                                    if (inner.succeeded()) {
                                        // Result: Update successfully
                                        data.mergeIn(inner.result().toJson());
                                    } else {
                                        Fn.flingUp(true, LOGGER,
                                                XtorExecuteException.class,
                                                getClass(), res.cause().getMessage());
                                    }
                                    counter.countDown();
                                });
                            }
                        } else {
                            Fn.flingUp(true, LOGGER,
                                    XtorExecuteException.class,
                                    getClass(), res.cause().getMessage());
                            counter.countDown();
                        }
                    });
                }
            }, "concurrent-update");
            return data;
        }, itemFuns, condition, latest);
    }

    private void ensure() {
        Fn.flingUp(null == this.client || null == this.collection ||
                        null == this.hitted || null == this.logger, LOGGER,
                XtorNotReadyException.class, getClass());
    }
}
