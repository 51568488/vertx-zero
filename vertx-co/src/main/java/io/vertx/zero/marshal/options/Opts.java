package io.vertx.zero.marshal.options;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.zero.exception.DemonException;
import io.vertx.zero.exception.EmptyStreamException;
import io.vertx.zero.exception.LimeFileException;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroTool;

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
                Fn.pool(EXTENSIONS, key,
                        () -> Node.infix(key));
        final JsonObject data = new JsonObject();
        try {
            data.mergeIn(node.read());
        } catch (final EmptyStreamException ex) {
            if (data.isEmpty()) {
                throw new LimeFileException(ZeroTool.produce(key));
            }
        }
        return data;
    }
}
