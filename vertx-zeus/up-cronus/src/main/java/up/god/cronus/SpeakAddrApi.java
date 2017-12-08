package up.god.cronus;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.BodyParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@EndPoint
@Path("/cronus")
public interface SpeakAddrApi {

    @Path("/direct")
    @POST
    @Address("ZUES://DIRECT")
    JsonObject speak(@BodyParam JsonObject data);
}
