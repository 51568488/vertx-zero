package io.vertx.tp.etcd.center;

import io.reactivex.Observable;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Jackson;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.eon.Values;
import io.vertx.zero.exception.EtcdConfigEmptyException;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;
import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.promises.EtcdResponsePromise;
import mousio.etcd4j.requests.EtcdKeyGetRequest;
import mousio.etcd4j.requests.EtcdKeyPutRequest;
import mousio.etcd4j.responses.EtcdKeysResponse;

import java.net.URI;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class EtcdData {
    private static final Annal LOGGER = Annal.get(EtcdData.class);
    private static final Node<JsonObject> NODE = Instance.singleton(ZeroUniform.class);
    private static final ConcurrentMap<Class<?>, EtcdData> POOL
            = new ConcurrentHashMap<>();
    /**
     * Config data
     */
    private static final String KEY = "etcd";
    private static final String PORT = "port";
    private static final String HOST = "host";
    private static final String TIMEOUT = "timeout";
    private static final String NODES = "nodes";
    /**
     * Etcd Client
     */
    private final transient JsonArray config = new JsonArray();
    private final transient EtcdClient client;
    private final transient Class<?> clazz;
    private final transient Annal logger;
    private transient long timeout = -1;

    public static EtcdData create(final Class<?> clazz) {
        return Fn.pool(POOL, clazz, () ->
                Fn.get(null, () -> new EtcdData(clazz), clazz));
    }


    /**
     * Whether Etcd Enabled.
     *
     * @return
     */
    public static boolean enabled() {
        final JsonObject config = NODE.read();
        return null != config && config.containsKey("etcd");
    }

    private EtcdData(final Class<?> clazz) {
        this.clazz = clazz;
        this.logger = Annal.get(clazz);
        // Read configuration
        final JsonObject config = NODE.read();
        if (config.containsKey(KEY)) {
            final JsonObject root = config.getJsonObject(KEY);
            if (root.containsKey(TIMEOUT)) {
                this.timeout = root.getLong(TIMEOUT);
            }
            // Nodes
            if (root.containsKey(NODES)) {
                this.config.addAll(root.getJsonArray(NODES));
            }
            LOGGER.info("[ ZERO ] Etcd Client timeout = {0}s with nodes = {1}",
                    this.timeout, this.config.size());
        }
        Fn.flingUp(this.config.isEmpty(), this.logger,
                EtcdConfigEmptyException.class, this.clazz);

        final Set<URI> uris = new HashSet<>();
        Observable.fromIterable(this.config)
                .filter(Objects::nonNull)
                .map(item -> (JsonObject) item)
                .filter(item -> item.containsKey(PORT) && item.containsKey(HOST))
                .map(item -> "http://" + item.getString(HOST) + ":" + item.getInteger(PORT))
                .map(URI::create)
                .subscribe(uris::add);
        this.client = new EtcdClient(uris.toArray(new URI[]{}));
    }

    public EtcdClient getClient() {
        return this.client;
    }

    public JsonArray getConfig() {
        return this.config;
    }

    public String read(final String path) {
        return Fn.getJvm(null, () -> {
            final EtcdKeyGetRequest request = this.client.get(path);
            /** Timeout **/
            if (-1 != this.timeout) {
                request.timeout(this.timeout, TimeUnit.SECONDS);
            }
            final EtcdResponsePromise<EtcdKeysResponse> promise = request.send();
            final EtcdKeysResponse response = promise.get();
            final EtcdKeysResponse.EtcdNode node = response.getNode();
            return node.getValue();
        }, path);
    }

    public <T> JsonObject write(final String path, final T data, final int ttl) {
        return Fn.getJvm(null, () -> {
            final EtcdKeyPutRequest request = this.client.put(path,
                    Fn.getSemi(data instanceof JsonObject || data instanceof JsonArray,
                            LOGGER,
                            () -> Instance.invoke(data, "encode"),
                            data::toString));
            if (Values.ZERO != ttl) {
                request.ttl(ttl);
            }
            /** Timeout **/
            if (-1 != this.timeout) {
                request.timeout(this.timeout, TimeUnit.SECONDS);
            }
            final EtcdResponsePromise<EtcdKeysResponse> promise = request.send();
            final EtcdKeysResponse response = promise.get();
            return Jackson.serializeJson(response.getNode());
        }, path, data);
    }
}
