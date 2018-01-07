package io.vertx.up.plugin.mongo;

import io.reactivex.Observable;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.up.atom.Envelop;
import io.vertx.up.concurrent.Runner;
import io.vertx.up.func.Fn;
import io.vertx.up.kidd.outcome.ListObstain;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Jackson;
import io.vertx.up.tool.StringUtil;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.eon.Values;
import io.vertx.zero.exception.XtorConnectException;
import io.vertx.zero.exception.XtorExecuteException;
import io.vertx.zero.exception.XtorNotReadyException;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;

/**
 * Tool for mongodb, simply the query with MongoClient
 * Read thread executor -> Rtor
 */
public class MongoRtor {

    private static final Annal LOGGER = Annal.get(MongoRtor.class);

    private transient final MongoClient client;

    private transient Class<?> hitted;
    private transient Annal logger;

    private transient String collection;
    private transient FindOptions options = new FindOptions();
    private transient Function aggregate = (item) -> item;
    private final transient JsonObject filter = new JsonObject();

    public static MongoRtor init(final MongoClient client) {
        return new MongoRtor(client);
    }

    private MongoRtor(final MongoClient client) {
        // Invalid constructor
        Fn.flingUp(null == client, LOGGER,
                XtorConnectException.class, getClass(),
                "client = " + client, "constructor(MongoClient)");
        this.client = client;
    }

    @Fluent
    public MongoRtor connect(final Class<?> clazz) {
        // Invalid connect
        Fn.flingUp(null == clazz, LOGGER,
                XtorConnectException.class, getClass(),
                "hitted = " + clazz, "connect(Class)");
        this.hitted = clazz;
        this.logger = (null == clazz) ? Annal.get(MongoWtor.class) : Annal.get(clazz);
        return this;
    }

    @Fluent
    public MongoRtor connect(final String collection) {
        // Invalid connect
        Fn.flingUp(StringUtil.isNil(collection), LOGGER,
                XtorConnectException.class, getClass(),
                "collection = " + collection, "connect(String)");
        this.collection = collection;
        return this;
    }

    @Fluent
    public MongoRtor connect(final FindOptions options) {
        // FindOptions could be null
        if (null != options) {
            this.options = options;
        }
        return this;
    }

    @Fluent
    public MongoRtor connect(final JsonObject filter) {
        if (null != filter) {
            this.filter.mergeIn(filter);
        }
        return this;
    }

    @Fluent
    public <T, R> MongoRtor connect(final Function<T, R> aggregate) {
        if (null != aggregate) {
            this.aggregate = aggregate;
        }
        return this;
    }

    /**
     * Single query default to unique
     *
     * @param dataObject
     * @param refKey
     * @param verticalKey
     * @param mountField
     */
    public JsonObject read(final JsonObject dataObject,
                           final String refKey,
                           final String verticalKey,
                           final String mountField) {
        return read(dataObject, refKey, verticalKey, mountField, true);
    }

    /**
     * Single query
     *
     * @param dataObject
     * @param refKey
     * @param verticalKey
     * @param mountField
     * @return
     */
    public JsonObject read(final JsonObject dataObject,
                           final String refKey,
                           final String verticalKey,
                           final String mountField,
                           final boolean unique) {
        final JsonArray dataArray = new JsonArray().add(dataObject);
        this.read(dataArray, refKey, verticalKey, mountField, unique);
        return dataArray.getJsonObject(Values.IDX);
    }

    /**
     * Secondary query method to enable concurrent query
     * This operation will not mount to field, but merge instead.
     *
     * @param dataArray
     * @param refKey
     * @param verticalKey
     * @return
     */
    public JsonArray read(
            final JsonArray dataArray,
            final String refKey,
            final String verticalKey
    ) {
        this.ensure();
        return Fn.getJvm(new JsonArray(), () -> {
            final JsonArray ids = new JsonArray();
            // Collect data
            Observable.fromIterable(dataArray)
                    .filter(Objects::nonNull)
                    .map(item -> (JsonObject) item)
                    .filter(item -> item.containsKey(verticalKey))
                    .map(item -> item.getValue(verticalKey))
                    .subscribe(ids::add);
            final CountDownLatch counter = new CountDownLatch(1);
            final JsonArray result = new JsonArray();
            Runner.run(() -> {
                // Set filter
                final JsonObject filter = new JsonObject().put(refKey,
                        new JsonObject().put("$in", ids));
                filter.mergeIn(this.filter);
                this.logger.info(Info.FILTER_INFO, this.collection, filter);
                // Fix bug for page two
                this.client.findWithOptions(this.collection, filter, new FindOptions(), res -> {
                    if (res.succeeded()) {
                        // Build response model
                        final Envelop envelop = ListObstain.<JsonObject>startList(this.hitted)
                                .connect(res).result().to();
                        final JsonArray data = envelop.data();
                        // Zip Join for two JsonArray
                        this.logger.info(Info.MERGE_INFO, dataArray, data, verticalKey, refKey);
                        final JsonArray merged = Jackson.mergeZip(dataArray, data, verticalKey, refKey);
                        result.addAll(merged);
                    } else {
                        Fn.flingUp(true, LOGGER,
                                XtorExecuteException.class,
                                getClass(), cause(res.cause()));
                    }
                    counter.countDown();
                });
            }, "concurrent-secondary-flip");
            // Await
            try {
                counter.await();
            } catch (final InterruptedException ex) {
                this.logger.jvm(ex);
            }
            // Convert to target mountKey
            return result;
        }, refKey, verticalKey);
    }

    /**
     * Multi minor, default to false
     *
     * @param dataArray
     * @param refKey
     * @param verticalKey
     * @param mountField
     */
    public JsonArray read(
            final JsonArray dataArray,
            final String refKey,
            final String verticalKey,
            final String mountField
    ) {
        return read(dataArray, refKey, verticalKey, mountField, false);
    }

    /**
     * Secondary query method to enable concurrent query
     * For example
     * Topic = [{...},{...},{...}]
     * Then query Videos belong to Topic for each element.
     * 1. Extract filter condition with refKey = item.get(verticalKey);
     * 2. Put options, filter to query result to JsonArray
     * 3. Mount the jsonArray to item.put(mountField,jsonArray)
     *
     * @param dataArray
     * @param refKey
     * @param verticalKey
     * @param mountField
     * @return
     */
    @SuppressWarnings("unchecked")
    public JsonArray read(
            final JsonArray dataArray,
            final String refKey,
            final String verticalKey,
            final String mountField,
            final boolean unique
    ) {
        this.ensure();
        return Fn.getJvm(new JsonArray(), () -> {
            // Build counter.
            final CountDownLatch counter = new CountDownLatch(dataArray.size());
            Fn.itJArray(dataArray, JsonObject.class, (item, index) -> {
                // Get item value by verticalKey
                final Object value = item.getValue(verticalKey);
                Fn.safeNull(() -> Runner.run(() -> {
                    // Direct set filter
                    final JsonObject filter = new JsonObject().put(refKey, value);
                    filter.mergeIn(this.filter);
                    this.logger.info(Info.FILTER_INFO, this.collection, filter);
                    this.client.findWithOptions(this.collection, filter, this.options, res -> {
                        if (res.succeeded()) {
                            // Build response model
                            final Envelop envelop = ListObstain.<JsonObject>startList(this.hitted)
                                    .connect(res).result().to();
                            final JsonArray data = envelop.data();
                            if (null != data) {
                                if (unique) {
                                    final JsonObject replaced = data.getJsonObject(Values.IDX);
                                    item.put(mountField, this.aggregate.apply(replaced));
                                } else {
                                    item.put(mountField, this.aggregate.apply(data));
                                }
                            }
                        } else {
                            Fn.flingUp(true, LOGGER,
                                    XtorExecuteException.class,
                                    getClass(), cause(res.cause()));
                        }
                        counter.countDown();
                    });
                }, "concurrent-secondary-" + value), value);
            });
            // Await
            try {
                counter.await();
            } catch (final InterruptedException ex) {
                this.logger.jvm(ex);
            }
            // Convert to target mountKey
            return dataArray;
        }, refKey, verticalKey, mountField);
    }

    private String cause(final Throwable error) {
        return null != error ? error.getMessage() : Strings.EMPTY;
    }

    private void ensure() {
        Fn.flingUp(null == this.client || null == this.collection ||
                        null == this.hitted || null == this.logger, LOGGER,
                XtorNotReadyException.class, getClass());
    }
}
