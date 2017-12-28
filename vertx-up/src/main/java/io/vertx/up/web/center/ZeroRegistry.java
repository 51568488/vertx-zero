package io.vertx.up.web.center;

import io.vertx.core.ServidorOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.etcd.center.EtcdData;
import io.vertx.up.eon.em.Etat;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Net;
import io.vertx.zero.eon.Values;

import java.text.MessageFormat;

/**
 * Zero registry center to write/read data with Etcd for zero micro service
 * This will be called by ZeroRpcAgent class to write service meta.
 * 1. Status RUNNING/STOPPED/FAILED
 * /zero/ipc/status/{name}/{host}:{port}
 * 2. Services for discovery
 * /zero/ipc/services/{name}/{host}/{port}
 */
public class ZeroRegistry {

    private static final String PATH_STATUS = "/zero/ipc/status/{0}:{1}:{2}";

    private static final String PATH_DISCOVERY = "/zero/ipc/services/{0}/{1}/{2}";

    private final transient Annal logger;
    private final transient EtcdData etcd;

    public static ZeroRegistry create(final Class<?> useCls) {
        return new ZeroRegistry(useCls);
    }

    private ZeroRegistry(final Class<?> useCls) {
        this.etcd = EtcdData.create(useCls);
        this.logger = Annal.get(useCls);
    }

    /**
     * Get current etcd configuration information that initialized
     * in zero system.
     *
     * @return
     */
    public JsonArray getConfig() {
        return this.etcd.getConfig();
    }

    public void registryStatus(final ServidorOptions options, final Etat etat) {
        final String path = pathStatus(options);
        this.logger.info(Info.ETCD_STATUS, options.getName(), etat, path);
        this.etcd.write(path, etat, Values.ZERO);
    }

    
    public void registryData(final ServidorOptions options) {
        final String path = pathData(options);
        final JsonObject data = new JsonObject();
        data.put("name", options.getName());
        // Get ip v4 address from host name.
        data.put("host", Net.getIPv4());
        data.put("port", options.getPort());
        this.logger.info(Info.ETCD_DATA, options.getName(), data, path);
        this.etcd.write(path, data, Values.ZERO);
    }

    public void unregistryData(final ServidorOptions options) {
        final String path = pathData(options);
        this.logger.info(Info.ETCD_UN_DATA, path, options.getName());
        this.etcd.delete(path);
    }

    private String pathStatus(final ServidorOptions options) {
        return MessageFormat.format(PATH_STATUS,
                options.getName(), options.getHost(), String.valueOf(options.getPort()));
    }

    private String pathData(final ServidorOptions options) {
        return MessageFormat.format(PATH_DISCOVERY,
                options.getName(), options.getHost(), String.valueOf(options.getPort()));
    }
}
