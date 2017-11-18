package org.exmaple;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;

import javax.ws.rs.BodyParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/up/example")
public class ZeroSender {

    @Path("/event")
    @POST
    @Address("ZERO://EVENT")
    public JsonObject sayEvent(
            @BodyParam final JsonObject data) {
        return data;
    }
}
