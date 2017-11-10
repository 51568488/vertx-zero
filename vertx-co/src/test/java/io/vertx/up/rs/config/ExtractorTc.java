package io.vertx.up.rs.config;

import io.vertx.core.DeploymentOptions;
import io.vertx.ext.unit.TestContext;
import io.vertx.up.ce.Event;
import io.vertx.up.cv.VertxValues;
import io.vertx.up.rs.Extractor;
import io.vertx.up.web.HttpAgent;
import org.junit.Test;
import org.vie.exception.up.EventSourceException;
import org.vie.util.Instance;
import top.UnitBase;

import java.util.Set;

public class ExtractorTc extends UnitBase {

    @Test
    public void testExtractAgent(final TestContext context) {
        final Extractor<DeploymentOptions> extractor =
                Instance.singleton(AgentExtractor.class);
        final DeploymentOptions options =
                extractor.extract(HttpAgent.class);
        context.assertEquals(VertxValues.DEFAULT_GROUP, options.getIsolationGroup());
        context.assertEquals(VertxValues.DEFAULT_HA, options.isHa());
        context.assertEquals(VertxValues.DEFAULT_INSTANCES, options.getInstances());
    }

    @Test(expected = EventSourceException.class)
    public void testExtractEndpoint() {
        final Extractor<Set<Event>> extractor =
                Instance.singleton(EventExtractor.class);
        extractor.extract(getClass());
    }
}
