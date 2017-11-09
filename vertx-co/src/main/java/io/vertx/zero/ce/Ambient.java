package io.vertx.zero.ce;

import com.vie.fun.HJson;
import com.vie.fun.HTry;
import com.vie.util.Instance;
import com.vie.util.log.Annal;
import com.vie.util.log.internal.Log4JAnnal;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.node.Opts;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Critical Environment
 *
 * @author lang
 */
public final class Ambient {

    private static final String KEY = "inject";
    /**
     * Avoid dead lock, use internal Log only.
     **/
    private static final Annal LOGGER = new Log4JAnnal(Ambient.class);

    private static final ConcurrentMap<String, Class<?>> INJECTIONS;

    private static final Opts<JsonObject> OPTS = Opts.get();

    static {
        INJECTIONS = new ConcurrentHashMap<>();
        HTry.exec(() -> {
            final JsonObject opt = OPTS.ingest(KEY);
            HJson.execIt(opt, (item, field) -> {
                INJECTIONS.put(field, Instance.clazz(item.toString()));
            });
        }, LOGGER);
    }

    public static Class<?> get(final String key) {
        return INJECTIONS.get(key);
    }

    private Ambient() {
    }
}
