package io.vertx.up.web.anima;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.config.AgentExtractor;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.limit.ApiFactor;
import io.vertx.up.web.limit.Factor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GatewayScatter implements Scatter<Vertx> {

    private static final Annal LOGGER = Annal.get(GatewayScatter.class);

    private transient final Factor factor = Instance.singleton(ApiFactor.class);

    @Override
    public void connect(final Vertx vertx) {
        /** 1.Find Agent for deploy **/
        final ConcurrentMap<ServerType, Class<?>> agents = this.factor.agents();
        final Extractor<DeploymentOptions> extractor =
                Instance.instance(AgentExtractor.class);
        /** 2.Record options**/
        final ConcurrentMap<Class<?>, DeploymentOptions> options =
                new ConcurrentHashMap<>();
        Fn.itMap(agents, (type, clazz) -> {
            // 3.1 Agent deployment options
            final DeploymentOptions option = extractor.extract(clazz);
            options.put(clazz, option);
            // 3.2 Agent deployment
            Verticles.deploy(vertx, clazz, option, LOGGER);
        });
    }
}
