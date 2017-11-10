package io.vertx.up.rs.config;

import io.vertx.core.DeploymentOptions;
import io.vertx.ext.unit.TestContext;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.cv.VertxValues;
import io.vertx.up.rs.Extractor;
import io.vertx.up.web.HttpAgent;
import org.junit.Test;
import org.vie.exception.up.EventSourceException;
import org.vie.util.Instance;
import top.UnitBase;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
        extractor().extract(getClass());
    }

    @Test
    public void testEvent1() {
        extractor().extract(User.class);
    }
}

@EndPoint
class User {

    @GET
    @Path("/hello")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String sayHello() {
        return "Hello";
    }

    public void test() {
    }
}
