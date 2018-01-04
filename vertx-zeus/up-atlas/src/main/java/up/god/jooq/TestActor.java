package up.god.jooq;

import io.github.jklingsporn.vertx.jooq.async.future.AsyncJooqSQLClient;
import io.vertx.up.annotations.EndPoint;

import javax.inject.infix.Jooq;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/atlas")
@EndPoint
public class TestActor {

    @Jooq
    private transient AsyncJooqSQLClient client;

    @Path("/say")
    @GET
    public String sayHell(){
        return "Hello Jooq";
    }
}
