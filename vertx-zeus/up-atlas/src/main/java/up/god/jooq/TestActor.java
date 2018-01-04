package up.god.jooq;

import io.vertx.ext.sql.SQLClient;
import io.vertx.up.annotations.EndPoint;

import javax.inject.infix.MySql;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@EndPoint
@Path("/test")
public class TestActor {

    @MySql
    private transient SQLClient client;

    @Path("get")
    @GET
    public String sayHell() {
        System.out.println(this.client.getConnection(res -> {
            System.out.println(res);
        }));
        return "Hello";
    }
}
