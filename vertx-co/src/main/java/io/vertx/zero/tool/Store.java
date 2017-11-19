package io.vertx.zero.tool;

import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.zero.tool.io.IO;

/**
 * Connect to vertx config to get options
 * From filename to ConfigStoreOptions
 */
public final class Store {

    /**
     * Return json
     *
     * @param filename
     * @return
     */
    public static ConfigStoreOptions getJson(final String filename) {
        return Fn.obtain(() -> {
            final JsonObject data = IO.getJObject(filename);
            return Fn.obtain(() ->
                            Fn.pool(Storage.STORE, filename,
                                    () -> new ConfigStoreOptions()
                                            .setType(StoreType.JSON.key())
                                            .setConfig(data))
                    , data);
        }, filename);
    }

    /**
     * Return yaml
     *
     * @param filename
     * @return
     */
    public static ConfigStoreOptions getYaml(final String filename) {
        return getFile(filename, StoreFormat.YAML);
    }

    /**
     * Return properties
     *
     * @param filename
     * @return
     */
    public static ConfigStoreOptions getProp(final String filename) {
        return getFile(filename, StoreFormat.PROP);
    }

    private static ConfigStoreOptions getFile(final String filename,
                                              final StoreFormat format) {
        return Fn.obtain(() -> {
            final JsonObject config = new JsonObject()
                    .put(StoreConfig.PATH.key(), IO.getPath(filename));
            return Fn.pool(Storage.STORE, filename,
                    () -> new ConfigStoreOptions()
                            .setType(StoreType.FILE.key())
                            .setFormat(format.key())
                            .setConfig(config));
        }, filename, format);
    }

    /**
     * Return yaml
     */

    private Store() {
    }
}
