package org.vie.util;

import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.json.JsonObject;
import org.vie.fun.HFail;
import org.vie.fun.HPool;
import org.vie.util.io.IO;

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
        return HFail.exec(() -> {
            final JsonObject data = IO.getJObject(filename);
            return HFail.exec(() ->
                            HPool.exec(Storage.STORE, filename,
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
        return HFail.exec(() -> {
            final JsonObject config = new JsonObject()
                    .put(StoreConfig.PATH.key(), IO.getPath(filename));
            return HPool.exec(Storage.STORE, filename,
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
