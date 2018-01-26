package up.god.apollo.exp7;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Codex;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.BodyParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@EndPoint
@Path("/exp7")
public interface TestApi {

    @Path("/verify")
    @POST
    @Address("ZERO://EXP7/VERIFY")
    JsonObject verify(@Codex @BodyParam JsonObject data);


    @Path("/wrong")
    @POST
    @Address("ZERO://EXP7/WRONG")
    JsonObject wrong(@BodyParam JsonObject data);
}
