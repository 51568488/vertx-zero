package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.zero.eon.FileSuffix;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.tool.io.IO;

public class ZeroUniform {

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
        return Fn.pool(Storage.CONFIG, filename, () ->
                Fn.getJvm(
                        new JsonObject(),
                        () -> IO.getYaml(filename), filename));
    }

    static String VERTX = "vertx" + Strings.DOT + FileSuffix.YML;
}
