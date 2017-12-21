package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.tool.io.IO;
import io.vertx.zero.eon.FileSuffix;
import io.vertx.zero.eon.Strings;

public class ZeroTool {

    public static String produce(final String key) {
        if (null == key) {
            return "vertx" + Strings.DOT + FileSuffix.YML;
        } else {
            return "vertx" + Strings.DASH + key +
                    Strings.DOT + FileSuffix.YML;
        }
    }

    static JsonObject read(final String key, final boolean extension) {
        /** Read the original configuration **/
        final JsonObject original = readDirect(produce(key));
        final JsonObject merged = new JsonObject();
        if (extension) {
            /** Read the internal configuration instead **/
            final JsonObject internal = readDirect("ke/config/" + produce(key));
            if (null != internal) {
                merged.mergeIn(internal, true);
            }
        }
        if (null != original) {
            merged.mergeIn(original, true);
        }
        return merged;
    }

    private static JsonObject readDirect(final String filename) {
        // Fix Docker issue
        if (Storage.CONFIG.containsKey(filename)) {
            return Storage.CONFIG.get(filename);
        } else {
            final JsonObject data = Fn.getJvm(
                    null,
                    () -> IO.getYaml(filename), filename);
            if (null != data && !data.isEmpty()) {
                Storage.CONFIG.put(filename, data);
            }
            return data;
        }
    }
}
