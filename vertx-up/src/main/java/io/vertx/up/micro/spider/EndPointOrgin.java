package io.vertx.up.micro.spider;

import io.reactivex.Observable;
import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.types.HttpEndpoint;
import io.vertx.up.eon.em.EtcdPath;
import io.vertx.up.tool.Codec;
import io.vertx.up.web.center.ZeroRegistry;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.eon.Values;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class EndPointOrgin implements Orgin {

    private final transient ZeroRegistry registry
            = ZeroRegistry.create(getClass());

    @Override
    public ConcurrentMap<String, Record> getRegistryData() {
        final Set<JsonObject> results = this.registry.readData(EtcdPath.ENDPOINT, (key, value) -> {
            final String id = Codec.sha256(key);
            final String[] data = key.split(Strings.COLON);
            final JsonObject result = new JsonObject();
            if (3 == data.length) {
                result.put(NAME, data[Values.ZERO]);
                result.put(HOST, data[Values.ONE]);
                result.put(PORT, Integer.parseInt(data[Values.TWO]));
                result.put(META, new JsonObject().put(ID, id));
            }
            return result;
        });
        // Build discovery record with metadata to identifier the key.
        final ConcurrentMap<String, Record> map = new ConcurrentHashMap<>();
        Observable.fromIterable(results)
                .filter(Objects::nonNull)
                .map(item -> {
                    final String name = item.getString(NAME);
                    final String host = item.getString(HOST);
                    final Integer port = item.getInteger(PORT);
                    return HttpEndpoint.createRecord(name,
                            host, port, "/*",
                            item.getJsonObject(META));
                }).subscribe(item -> {
            final JsonObject meta = item.getMetadata();
            map.put(meta.getString(ID), item);
        });
        return map;
    }
}
