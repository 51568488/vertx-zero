package io.vertx.zero.core.config;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.ZeroNode;
import org.vie.cv.FileTypes;
import org.vie.cv.Strings;
import org.vie.fun.HPool;
import org.vie.util.Instance;
import org.vie.util.StringUtil;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * @author lang
 */
public class ZeroLime implements ZeroNode<ConcurrentMap<String, String>> {

    private transient final ZeroNode<JsonObject> node
            = Instance.singleton(ZeroVertx.class);

    @Override
    public ConcurrentMap<String, String> read() {
        // 1. Read all zero configuration: zero
        final JsonObject data = this.node.read();
        // 2. Read the string node "lime" for extensions
        final String literal = data.getString(Key.LIME);
        return build(literal);
    }

    private ConcurrentMap<String, String> build(final String literal) {
        final Set<String> sets = StringUtil.split(literal, Strings.COMMA);
        if (null != literal) {
            for (final String set : sets) {
                if (!StringUtil.isNil(set)) {
                    HPool.exec(Storage.DATA_LIME, set,
                            () -> calculatePath(set));
                }
            }
        }
        return Storage.DATA_LIME;
    }

    public static String calculatePath(final String key) {
        return Limes.PREFIX + Strings.DASH + key +
                Strings.DOT + FileTypes.YML;
    }
}
