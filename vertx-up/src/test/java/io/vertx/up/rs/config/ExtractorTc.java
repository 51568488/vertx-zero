package io.vertx.up.rs.config;

import io.vertx.core.DeploymentOptions;
import io.vertx.ext.unit.TestContext;
import io.vertx.up.eon.VertxValues;
import io.vertx.up.example.User;
import io.vertx.up.exception.up.EventSourceException;
import io.vertx.up.rs.Extractor;
import io.vertx.up.web.ZeroHttpAgent;
import io.vertx.zero.test.UnitBase;
import io.vertx.zero.tool.mirror.Instance;
import org.junit.Test;

public class ExtractorTc extends UnitBase {

    @Test
    public void testExtractAgent(final TestContext context) {
        final Extractor<DeploymentOptions> extractor =
                Instance.singleton(AgentExtractor.class);
        final DeploymentOptions options =
                extractor.extract(ZeroHttpAgent.class);
        context.assertEquals(VertxValues.DEFAULT_GROUP, options.getIsolationGroup());
        context.assertEquals(VertxValues.DEFAULT_HA, options.isHa());
        context.assertEquals(VertxValues.DEFAULT_INSTANCES, options.getInstances());
    }

    @Test(expected = EventSourceException.class)
    public void testExtractEndpoint() {
        extractor().extract(getClass());
    }

    @Test
    public void testEvent1() {
        extractor().extract(User.class);
    }
}
