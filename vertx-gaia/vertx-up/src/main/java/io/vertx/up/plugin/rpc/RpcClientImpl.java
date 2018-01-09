package io.vertx.up.plugin.rpc;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.servicediscovery.Record;
import io.vertx.up.eon.em.IpcType;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Types;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RpcClientImpl implements RpcClient {

    private static final Annal LOGGER = Annal.get(RpcClientImpl.class);

    private static final String DS_LOCAL_MAP_NAME = "__vertx.IpcClient.{0}";

    private static final ConcurrentMap<String, JsonObject> CONFIGS
            = new ConcurrentHashMap<>();

    private final Vertx vertx;
    private final JsonObject config;
    private final String name;
    protected RpcClient client;
    protected RpcHolder holder;

    public RpcClientImpl(final Vertx vertx,
                         final JsonObject config,
                         final String name) {
        Objects.requireNonNull(vertx);
        Objects.requireNonNull(config);
        this.vertx = vertx;
        this.config = config;
        this.name = name;
    }

    @Override
    public <T> RpcClient connect(final JsonObject config,
                                 final JsonObject data,
                                 final Handler<AsyncResult<T>> handler) {
        final Record record = RpcHelper.getRecord(config);
        // Service Configuration
        final JsonObject normalized = RpcHelper.normalize(config.getString(Key.NAME), config, record);
        this.holder = new RpcHolder(this.vertx, normalized, null);
        // Get Channel
        final IpcType type = Types.fromStr(IpcType.class, config.getString(Key.TYPE));
        
        return this;
    }

    @Override
    public <T> RpcClient connect(final String name,
                                 final String address,
                                 final JsonObject data,
                                 final Handler<AsyncResult<T>> handler) {
        return this.connect(RpcHelper.on(name, address), data, handler);
    }


    private RpcHolder lookupHolder(
            final Vertx vertx,
            final String ipcName,
            final JsonObject config) {
        synchronized (this.vertx) {
            final String name = MessageFormat.format(DS_LOCAL_MAP_NAME, config.getString("type"));
            final LocalMap<String, RpcHolder> map = this.vertx.sharedData().getLocalMap(name);
            RpcHolder holder = map.get(ipcName);
            if (null == holder) {
                holder = new RpcHolder(vertx, config, () -> removeFromMap(map, name));
                map.put(name, holder);
            } else {
                holder.incRefCount();
            }
            return holder;
        }
    }

    private void removeFromMap(final LocalMap<String, RpcHolder> map,
                               final String name) {
        synchronized (this.vertx) {
            map.remove(name);
            if (map.isEmpty()) {
                map.close();
            }
        }
    }
}
