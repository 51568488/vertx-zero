package io.vertx.zero.marshal.node;

import io.reactivex.Observable;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Plugins;
import io.vertx.up.func.Fn;
import io.vertx.up.tool.StringUtil;
import io.vertx.zero.eon.Strings;

import java.util.Set;

/**
 * @author lang
 */
public class ZeroVertx implements Node<JsonObject> {

    @Override
    public JsonObject read() {
        // Not null because execNil
        final JsonObject config = ZeroTool.read(null, true);
        // Injection Lime
        final JsonObject zero = Fn.getJvm(new JsonObject(),
                () -> config.getJsonObject(Key.ZERO), config);
        if (null != zero && zero.containsKey(Key.LIME)) {
            prodcessLime(zero);
        }
        // Return to zero configuration part
        return zero;
    }

    private JsonObject process(final JsonObject data) {
        return Fn.get(() -> {
            /** 1. Append lime **/
            if (data.containsKey(Key.LIME)) {
                prodcessLime(data);
            }
            return data;
        }, data);
    }

    private void prodcessLime(final JsonObject data) {
        Fn.safeNull(() -> {
            final String limeStr = data.getString(Key.LIME);
            final Set<String> sets = StringUtil.split(limeStr, Strings.COMMA);
            /**
             * server, inject, error, resolver
             * RxJava2
             */
            Observable.fromArray(Plugins.DATA)
                    .map(item -> item)
                    .subscribe(sets::add);
            data.put(Key.LIME, StringUtil.join(sets));
        }, data);
    }
}
