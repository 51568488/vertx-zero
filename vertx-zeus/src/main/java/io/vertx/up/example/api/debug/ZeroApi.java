package io.vertx.up.example.api.debug;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.BodyParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@EndPoint
@Path("/up/example")
public interface ZeroApi {
    @Path("/event")
    @POST
    @Address("ZERO://EVENT")
    public JsonObject send(
            @BodyParam final JsonObject body);
}
