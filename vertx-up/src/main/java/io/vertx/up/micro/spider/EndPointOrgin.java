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

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EndPointOrgin implements Orgin {

    private final transient ZeroRegistry registry
            = ZeroRegistry.create(getClass());

    @Override
    public Set<Record> getRegistryData() {
        final Set<JsonObject> results = this.registry.readData(EtcdPath.ENDPOINT, (key, value) -> {
            final String id = Codec.sha256(key);
            final String[] data = key.split(Strings.COLON);
            final JsonObject result = new JsonObject();
            if (3 == data.length) {
                result.put(NAME, data[Values.ZERO]);
                result.put(HOST, data[Values.ONE]);
                result.put(PORT, Integer.parseInt(data[Values.TWO]));
                result.put(ID, id);
            }
            return result;
        });
        // Build discovery record
        final Set<Record> records = new HashSet<>();
        Observable.fromIterable(results)
                .filter(Objects::nonNull)
                .map(item -> {
                    final String name = item.getString(NAME);
                    final String host = item.getString(HOST);
                    final Integer port = item.getInteger(PORT);
                    return HttpEndpoint
                            .createRecord(name, host, port, "/*")
                            .setRegistration(item.getString(ID));
                }).subscribe(records::add);
        return records;
    }
}
