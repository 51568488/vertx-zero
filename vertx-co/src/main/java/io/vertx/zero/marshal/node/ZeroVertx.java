package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Plugins;
import io.vertx.up.func.Fn;
import io.vertx.zero.eon.Strings;
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
        return Fn.getJvm(
                new JsonObject(),
                () -> {
                    final JsonObject raw = IO.getYaml(Path.KE_VERTX);
                    return (raw.containsKey(Key.ZERO)) ?
                            process(raw.getJsonObject(Key.ZERO)) : null;
                });
    }

    private JsonObject process(final JsonObject data) {
        return Fn.get(() -> {
            /** 1. Append lime **/
            if (data.containsKey(Key.LIME)) {
                injectLime(data);
            }
            return data;
        }, data);
    }

    private void injectLime(final JsonObject data) {
        Fn.safeNull(() -> {
            final String limeStr = data.getString(Key.LIME);
            final Set<String> sets = StringUtil.split(limeStr, Strings.COMMA);
            /**
             * server, inject, error
             */
            for (final String item : Plugins.DATA) {
                appendKey(sets, item);
            }
            data.put(Key.LIME, StringUtil.join(sets));
        }, data);
    }

    private void appendKey(final Set<String> inject,
                           final String key) {
        if (null != inject
                && !StringUtil.isNil(key)
                && !inject.contains(key)) {
            inject.add(key);
        }
    }
}
