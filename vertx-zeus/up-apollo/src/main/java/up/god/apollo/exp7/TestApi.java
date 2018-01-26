package up.god.apollo.exp7;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Codex;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.BodyParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@EndPoint
@Path("/exp7")
public class TestApi {

    @Path("/verify")
    @POST
    public JsonObject verify(@Codex @BodyParam final JsonObject data) {
        return data;
    }
}
