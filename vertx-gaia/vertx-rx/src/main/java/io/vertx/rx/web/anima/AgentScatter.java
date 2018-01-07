package io.vertx.rx.web.anima;

import io.vertx.core.DeploymentOptions;
import io.vertx.reactivex.core.Vertx;
import io.vertx.rx.web.limit.RxFactor;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.config.AgentExtractor;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.anima.Scatter;
import io.vertx.up.web.limit.Factor;

import java.util.concurrent.ConcurrentMap;

public class AgentScatter implements Scatter<Vertx> {

    private static final Annal LOGGER = Annal.get(AgentScatter.class);

    private transient final Factor factor = Instance.singleton(RxFactor.class);

    @Override
    public void connect(final Vertx vertx) {
        /** 1.Find Agent for deploy **/
        final ConcurrentMap<ServerType, Class<?>> agents = this.factor.agents();

        final Extractor<DeploymentOptions> extractor =
                Instance.instance(AgentExtractor.class);

        Fn.itMap(agents, (type, clazz) -> {
            // 2.1. Agent deployment options
            final DeploymentOptions option = extractor.extract(clazz);
            // 2.2. Agent deployment
            Verticles.deploy(vertx, clazz, option, LOGGER);
        });
    }
}
