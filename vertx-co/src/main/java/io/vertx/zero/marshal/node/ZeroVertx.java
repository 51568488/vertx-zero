package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Plugins;
import io.vertx.up.eon.Strings;
import io.vertx.zero.func.HBool;
import io.vertx.zero.func.HFail;
import io.vertx.zero.func.HNull;
import io.vertx.zero.marshal.Node;
import io.vertx.zero.tool.StringUtil;
import io.vertx.zero.tool.io.IO;

import java.util.Set;

/**
 * @author lang
 */
public class ZeroVertx implements Node<JsonObject> {

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
            /**
             * server, inject, error
             */
            for (final String item : Plugins.DATA) {
                appendKey(sets, item);
            }
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
