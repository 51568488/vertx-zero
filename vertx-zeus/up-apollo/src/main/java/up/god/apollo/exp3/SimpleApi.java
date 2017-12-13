package up.god.apollo.exp3;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Codex;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.BodyParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@EndPoint
@Path("/exp3")
public interface SimpleApi {

    @Path("")
    @POST
    @Address("EXP3://QUEUE/VALIDATE")
    String sayHello(@BodyParam @Codex JsonObject data);
}
