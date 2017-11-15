package io.vertx.zero.marshal.options;

import io.vertx.core.json.JsonObject;
import io.vertx.up.exception.zero.LimeFileException;
import io.vertx.zero.exception.DemonException;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.func.HPool;
import io.vertx.zero.marshal.Node;
import io.vertx.zero.marshal.node.ZeroLime;
import io.vertx.zero.marshal.node.ZeroPlugin;
import io.vertx.zero.tool.mirror.Instance;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Options for different configuration
 *
 * @param <T>
 */
public interface Opts<T> {
    /**
     * Read data from files
     *
     * @return
     * @throws DemonException
     */
    T ingest(String node) throws ZeroException;

    /**
     * Read reference of Opts
     *
     * @return
     */
    static Opts<JsonObject> get() {
        return YamlOpts.create();
    }
}

class YamlOpts implements Opts<JsonObject> {

    private static final ConcurrentMap<String, Node<JsonObject>>
            EXTENSIONS = new ConcurrentHashMap<>();

    /**
     * Default package scope, manually implement singleton
     * instead of Instance.singleton.
     *
     * @return
     */
    public static Opts<JsonObject> create() {
        if (null == INSTANCE) {
            synchronized (YamlOpts.class) {
                if (null == INSTANCE) {
                    INSTANCE = new YamlOpts();
                }
            }
        }
        return INSTANCE;
    }

    private static Opts<JsonObject> INSTANCE;

    private YamlOpts() {
    }

    ;

    @Override
    public JsonObject ingest(final String key)
            throws ZeroException {
        final Node<JsonObject> node =
                HPool.exec(EXTENSIONS, key,
                        () -> Instance.instance(ZeroPlugin.class, key));
        final JsonObject data = node.read();
        if (null == data) {
            throw new LimeFileException(ZeroLime.calculatePath(key));
        }
        return data;
    }
}
