package io.vertx.zero.core.config;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.ZeroNode;
import org.vie.cv.Strings;
import org.vie.fun.HBool;
import org.vie.fun.HFail;
import org.vie.fun.HNull;
import org.vie.util.StringUtil;
import org.vie.util.io.IO;

import java.util.Set;

/**
 * @author lang
 */
public class ZeroVertx implements ZeroNode<JsonObject> {

    @Override
    public JsonObject read() {
        // Not null because execNil
        return HFail.execDft(() -> {
            final JsonObject raw = IO.getYaml(Path.KE_VERTX);
            return HBool.execTrue(raw.containsKey(Key.ZERO),
                    () -> process(raw.getJsonObject(Key.ZERO)));
        }, new JsonObject());
    }

    private JsonObject process(final JsonObject data) {
        return HNull.get(() -> {
            /** 1. Append lime **/
            if (data.containsKey(Key.LIME)) {
                injectLime(data);
            }
            return data;
        }, data);
    }

    private void injectLime(final JsonObject data) {
        if (null != data) {
            final String limeStr = data.getString(Key.LIME);
            final Set<String> sets = StringUtil.split(limeStr, Strings.COMMA);
            // Error table list
            appendKey(sets, "error");
            // Server List
            appendKey(sets, "server");
            // Injection points
            appendKey(sets, "inject");
            data.put(Key.LIME, StringUtil.join(sets));
        }
    }

    private void appendKey(final Set<String> inject,
                           final String key) {
        if (null != inject && !StringUtil.isNil(key)) {
            HBool.execTrue(!inject.contains(key),
                    () -> inject.add(key));
        }
    }
}
