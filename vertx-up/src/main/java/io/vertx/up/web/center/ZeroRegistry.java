package io.vertx.up.web.center;

import io.reactivex.Observable;
import io.vertx.core.ServidorOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.etcd.center.EtcdData;
import io.vertx.up.eon.em.Etat;
import io.vertx.up.eon.em.EtcdPath;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Net;
import io.vertx.up.tool.mirror.Types;
import io.vertx.zero.eon.Values;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;

/**
 * Zero registry center to write/read data with Etcd for zero micro service
 * This will be called by ZeroRpcAgent class to write service meta.
 * 1. Status RUNNING/STOPPED/FAILED
 * /zero/ipc/status/{name}/{host}:{port}
 * 2. Services for discovery
 * /zero/ipc/services/{name}/{host}/{port}
 */
public class ZeroRegistry {

    private static final String PATH_STATUS = "/zero/{0}/services/{1}:{2}:{3}";

    private static final String PATH_CATALOG = "/zero/{0}/services";

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

    public Set<JsonObject> readData(final EtcdPath etcdPath,
                                    final BiFunction<String, String, JsonObject> convert) {
        final String path = MessageFormat.format(PATH_CATALOG, etcdPath.toString().toLowerCase());
        this.logger.info(Info.ETCD_READ, path);
        final ConcurrentMap<String, String> nodes = this.etcd.readDir(path);
        final Set<JsonObject> sets = new HashSet<>();

        Observable.fromIterable(nodes.entrySet())
                .filter(Objects::nonNull)
                .filter(item -> Objects.nonNull(item.getKey()) && Objects.nonNull(item.getValue()))
                .filter(item -> Etat.RUNNING == Types.fromStr(Etat.class, item.getValue()))
                .map(item -> convert.apply(item.getKey(), item.getValue()))
                .filter(Objects::nonNull)
                .filter(item -> !item.isEmpty())
                .subscribe(sets::add);
        return sets;
    }

    public void registryHttp(final String service,
                             final HttpServerOptions options, final Etat etat) {
        final String path = MessageFormat.format(PATH_STATUS,
                EtcdPath.ENDPOINT.toString().toLowerCase(), service,
                Net.getIPv4(), String.valueOf(options.getPort()));
        this.logger.info(Info.ETCD_STATUS, service, etat, path);
        this.etcd.write(path, etat, Values.ZERO);
    }

    public void registryRpc(final ServidorOptions options, final Etat etat) {
        final String path = MessageFormat.format(PATH_STATUS,
                EtcdPath.IPC.toString().toLowerCase(), options.getName(),
                Net.getIPv4(), String.valueOf(options.getPort()));
        this.logger.info(Info.ETCD_STATUS, options.getName(), etat, path);
        this.etcd.write(path, etat, Values.ZERO);
    }
}
