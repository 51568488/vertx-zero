package up.god.jooq;

import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@EndPoint
@Path("/test")
public class TestActor {

    @Path("get")
    @GET
    public String sayHell() {
        return "Hello";
    }
}
