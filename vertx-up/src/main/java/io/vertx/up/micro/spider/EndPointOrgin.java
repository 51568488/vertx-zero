package io.vertx.up.micro.spider;

import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.em.EtcdPath;
import io.vertx.up.web.center.ZeroRegistry;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.eon.Values;

import java.util.Set;

public class EndPointOrgin implements Orgin {

    private final transient ZeroRegistry registry
            = ZeroRegistry.create(getClass());

    @Override
    public Set<JsonObject> getRegistryData() {
        return this.registry.readData(EtcdPath.ENDPOINT, (key, value) -> {
            final String[] data = key.split(Strings.COLON);
            final JsonObject result = new JsonObject();
            if (3 == data.length) {
                result.put("name", data[Values.ZERO]);
                result.put("host", data[Values.ONE]);
                result.put("port", data[Values.TWO]);
            }
            return result;
        });
    }
}
