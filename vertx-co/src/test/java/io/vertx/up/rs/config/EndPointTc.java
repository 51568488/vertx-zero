package io.vertx.up.rs.config;

import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.ext.unit.TestContext;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.ce.Event;
import org.junit.Test;
import top.UnitBase;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

public class EndPointTc extends UnitBase {

    @Test
    public void testEndPoint(final TestContext context) {
        final Set<Event> all = new ConcurrentHashSet<>();
        all.addAll(extractor().extract(ED1.class));
        all.addAll(extractor().extract(ED.class));
        for (final Event event : all) {
            getLogger().info("[TEST] Extract event: {0}.", event);
        }
    }
}

@EndPoint
class ED {

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

@EndPoint
class ED1 {

    @GET
    @Path("/hello")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String sayHello() {
        return "Hello";
    }

    @POST
    @Path("/hello")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String sayHelloP() {
        return "Hello";
    }

    public void test() {
    }
}
