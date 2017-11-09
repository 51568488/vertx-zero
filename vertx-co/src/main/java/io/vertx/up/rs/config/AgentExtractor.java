package io.vertx.up.rs.config;

import io.vertx.core.DeploymentOptions;
import io.vertx.up.rs.Extractor;

/**
 * Standard verticle deployment.
 */
public class AgentExtractor implements Extractor<DeploymentOptions> {

    @Override
    public DeploymentOptions extract(final Class<?> clazz) {

        return null;
    }
}
