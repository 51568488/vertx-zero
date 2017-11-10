package io.vertx.up.rs.config;

import io.vertx.core.DeploymentOptions;
import io.vertx.up.annotations.Agent;
import io.vertx.up.rs.Extractor;
import org.vie.fun.HNull;
import org.vie.fun.HPool;
import org.vie.util.Instance;
import org.vie.util.log.Annal;
import org.vie.util.mirror.Anno;

import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Standard verticle deployment.
 */
public class AgentExtractor implements Extractor<DeploymentOptions> {

    private static final Annal LOGGER = Annal.get(AgentExtractor.class);

    private static final ConcurrentMap<Class<?>, DeploymentOptions>
            OPTIONS = new ConcurrentHashMap<>();

    @Override
    public DeploymentOptions extract(final Class<?> clazz) {
        HNull.exec(() -> {
            LOGGER.info(Message.AGENT_HIT, clazz.getName());
        }, clazz);
        return HPool.exec(OPTIONS, clazz, () -> transform(clazz));
    }

    private DeploymentOptions transform(final Class<?> clazz) {
        final Annotation annotation = Anno.get(clazz, Agent.class);
        // 1. Instance
        final int instances = Instance.invoke(annotation, Agent.Key.INSTANCES);
        final boolean ha = Instance.invoke(annotation, Agent.Key.HA);
        final String group = Instance.invoke(annotation, Agent.Key.GROUP);
        // 2. Record Log information
        final DeploymentOptions options = new DeploymentOptions();
        options.setHa(ha);
        options.setInstances(instances);
        options.setIsolationGroup(group);
        // 3. Disabled worker fetures.
        options.setWorker(false);
        options.setMultiThreaded(false);
        LOGGER.info(Message.AGENT_OPT, instances, group, ha, options.toJson());
        return options;
    }
}
