package io.vertx.up.plugin.rpc;

import io.grpc.ManagedChannel;
import io.reactivex.Observable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.Shareable;
import io.vertx.servicediscovery.Record;
import io.vertx.up.exception._424RpcServiceException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.discovery.IpcOrigin;
import io.vertx.up.micro.discovery.Origin;
import io.vertx.up.micro.ipc.client.SslTool;
import io.vertx.up.tool.RxContainer;
import io.vertx.up.tool.StringUtil;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.atom.Ruler;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RpcClientImpl implements RpcClient {

    private static final Annal LOGGER = Annal.get(RpcClientImpl.class);
    private static final Origin ORIGIN = Instance.singleton(IpcOrigin.class);

    private static final String DS_LOCAL_MAP_NAME = "__vertx.IpcClient.{0}";
    private static final String RULE_KEY = "rpcclient";
    private static final String PATH = "path";
    private static final String ADDR = "addr";
    private static final String NAME = "name";
    private static final ConcurrentMap<String, JsonObject> CONFIGS
            = new ConcurrentHashMap<>();

    private final Vertx vertx;
    private final JsonObject config;
    private final String name;
    protected RpcClient client;
    protected IpcHolder holder;

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
        final Record record = getRecord(config);
        // Service Configuration
        System.out.println(record.toJson());
        System.out.println(config);
        return this;
    }

    @Override
    public <T> RpcClient connect(final String name,
                                 final String address,
                                 final JsonObject data,
                                 final Handler<AsyncResult<T>> handler) {
        final Record record = getRecord(RpcClient.on(name, address));
        // Service Configuration
        System.out.println(record.toJson());
        System.out.println(this.config);
        return this;
    }

    private Record getRecord(final JsonObject config) {
        /** Config Verify **/
        Fn.flingUp(() -> Fn.shuntZero(() -> Ruler.verify(RULE_KEY, config), config),
                LOGGER);
        // Connect remote etcd to check service
        final ConcurrentMap<String, Record> registryData = ORIGIN.getRegistryData();
        final String name = config.getString(NAME);
        final String address = config.getString(ADDR);
        LOGGER.info(Info.RPC_SERVICE, name, address);
        // Empty Found
        Fn.flingWeb(registryData.values().isEmpty(), LOGGER,
                _424RpcServiceException.class, getClass(),
                name, address);

        // Service status checking
        final RxContainer container = new RxContainer();
        // Iterator Matching
        Observable.fromIterable(registryData.values())
                .filter(Objects::nonNull)
                .filter(item -> StringUtil.notNil(item.getName()))
                .map(item -> {
                    // Service Not Found
                    Fn.flingWeb(!item.getName().equals(name), LOGGER,
                            _424RpcServiceException.class, getClass(),
                            name, address);
                    // Address Not Found
                    final String addr = item.getMetadata().getString(PATH);
                    Fn.flingWeb(!addr.equals(address), LOGGER,
                            _424RpcServiceException.class, getClass(),
                            name, address);
                    return item;
                })
                .filter(item -> name.equals(item.getName()) &&
                        address.equals(item.getMetadata().getString(PATH)))
                .subscribe(container::add);
        return container.get();
    }

    private IpcHolder lookupHolder(
            final Vertx vertx,
            final String ipcName,
            final JsonObject config) {
        synchronized (this.vertx) {
            final String name = MessageFormat.format(DS_LOCAL_MAP_NAME, config.getString("type"));
            final LocalMap<String, IpcHolder> map = this.vertx.sharedData().getLocalMap(name);
            IpcHolder holder = map.get(ipcName);
            if (null == holder) {
                holder = new IpcHolder(vertx, config, () -> removeFromMap(map, name));
                map.put(name, holder);
            } else {
                holder.incRefCount();
            }
            return holder;
        }
    }

    private void removeFromMap(final LocalMap<String, IpcHolder> map,
                               final String name) {
        synchronized (this.vertx) {
            map.remove(name);
            if (map.isEmpty()) {
                map.close();
            }
        }
    }

    private static class IpcHolder implements Shareable {
        private ManagedChannel channel;
        private final JsonObject config;
        private final Vertx vertx;
        private final Runnable closeRunner;
        private int refCount = 1;

        public IpcHolder(
                final Vertx vertx,
                final JsonObject config,
                final Runnable closeRunner) {
            this.vertx = vertx;
            this.config = config;
            this.closeRunner = closeRunner;
        }

        synchronized ManagedChannel unity() {
            this.channel = SslTool.getChannel(this.vertx, this.config);
            return this.channel;
        }

        synchronized void incRefCount() {
            this.refCount++;
        }

        synchronized void close() {
            if (--this.refCount == 0) {
                if (this.channel != null) {
                    this.channel.shutdownNow();
                }
                if (this.closeRunner != null) {
                    this.closeRunner.run();
                }
            }
        }
    }
}
