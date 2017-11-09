package io.vertx.zero.core.config;

import com.vie.cv.Strings;
import com.vie.fun.HBool;
import com.vie.fun.HFail;
import com.vie.fun.HNull;
import com.vie.util.StringUtil;
import com.vie.util.io.IO;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.ZeroNode;

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
            return HBool.execTrue(raw.containsKey(Keys.ZERO),
                    () -> process(raw.getJsonObject(Keys.ZERO)));
        }, new JsonObject());
    }

    private JsonObject process(final JsonObject data) {
        return HNull.get(() -> {
            /** 1. Append lime **/
            if (data.containsKey(Keys.LIME)) {
                injectLime(data);
            }
            return data;
        }, data);
    }

    private void injectLime(final JsonObject data) {
        if (null != data) {
            final String limeStr = data.getString(Keys.LIME);
            final Set<String> sets = StringUtil.split(limeStr, Strings.COMMA);
            // Error table list
            appendKey(sets, "error");
            // Server List
            appendKey(sets, "server");
            // Injection points
            appendKey(sets, "inject");
            data.put(Keys.LIME, StringUtil.join(sets));
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
