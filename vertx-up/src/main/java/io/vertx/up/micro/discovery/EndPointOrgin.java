package io.vertx.up.micro.discovery;

import io.reactivex.Observable;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.types.HttpEndpoint;
import io.vertx.up.eon.em.EtcdPath;
import io.vertx.up.micro.center.ZeroRegistry;
import io.vertx.up.tool.Codec;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.eon.Values;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class EndPointOrgin implements Orgin {

    private final transient ZeroRegistry registry
            = ZeroRegistry.create(getClass());

    @Override
    public ConcurrentMap<String, Record> getRegistryData() {
        // Get End Points.
        final Set<String> results = this.registry.getServices(EtcdPath.ENDPOINT);
        // Get records by results.
        final Set<JsonObject> routes = new HashSet<>();
        Observable.fromIterable(results)
                .map(key -> this.registry.getData(EtcdPath.ENDPOINT, key, this::getItem))
                .subscribe(routes::addAll);
        // Build discovery record with metadata to identifier the key.
        final ConcurrentMap<String, Record> map = new ConcurrentHashMap<>();
        // Convert to map
        Observable.fromIterable(routes)
                .subscribe(item -> {
                    final String key = item.getJsonObject(META)
                            .getString(ID);
                    final Record record = createRecord(item);
                    map.put(key, record);
                });
        return map;
    }

    private Record createRecord(final JsonObject item) {
        final String name = item.getString(NAME);
        final String host = item.getString(HOST);
        final Integer port = item.getInteger(PORT);
        final JsonObject meta = item.getJsonObject(META);
        return HttpEndpoint.createRecord(
                name, host, port, "/*", meta
        );
    }

    private Set<JsonObject> getItem(final String key, final JsonArray value) {
        // Build JsonObject with value array
        final Set<JsonObject> sets = new HashSet<>();
        final String[] meta = key.split(Strings.COLON);
        if (3 == meta.length) {
            Observable.fromIterable(value)
                    .filter(Objects::nonNull)
                    .map(Object::toString)
                    .map(item -> {
                        final String name = meta[Values.ZERO];
                        final String host = meta[Values.ONE];
                        final String port = meta[Values.TWO];
                        final String id = Codec.sha256(key + item);
                        return new JsonObject()
                                .put(NAME, name)
                                .put(HOST, host)
                                .put(PORT, Integer.parseInt(port))
                                .put(META, new JsonObject()
                                        .put(ID, id)
                                        .put(PATH, item));
                    })
                    .subscribe(sets::add);
        }
        return sets;
    }
}
