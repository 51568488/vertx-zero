package io.vertx.zero.marshal.node;

import io.reactivex.Observable;
import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.tool.StringUtil;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.eon.Strings;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author lang
 */
public class ZeroLime implements Node<ConcurrentMap<String, String>> {

    private transient final Node<JsonObject> node
            = Instance.singleton(ZeroVertx.class);

    private static final ConcurrentMap<String, String> INTERNALS
            = new ConcurrentHashMap<String, String>() {
        {
            put("error", ZeroTool.produce("error"));
            put("inject", ZeroTool.produce("inject"));
            put("server", ZeroTool.produce("server"));
            put("resolver", ZeroTool.produce("resolver"));
        }
    };

    @Override
    public ConcurrentMap<String, String> read() {
        // 1. Read all zero configuration: zero
        final JsonObject data = this.node.read();
        // 2. Read the string node "lime" for extensions
        return build(data.getString(Key.LIME));
    }

    private ConcurrentMap<String, String> build(final String literal) {
        final Set<String> sets = StringUtil.split(literal, Strings.COMMA);
        if (null != literal) {
            // RxJava2
            Observable.fromIterable(sets)
                    .filter(Objects::nonNull)
                    .subscribe(item -> Fn.pool(INTERNALS, item,
                            () -> ZeroTool.produce(item)));
        }
        return INTERNALS;
    }
}
