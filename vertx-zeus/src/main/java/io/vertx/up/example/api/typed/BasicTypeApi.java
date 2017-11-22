package io.vertx.up.example.api.typed;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Codex;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@EndPoint
@Path("/zero/type")
public class BasicTypeApi {

    @Path("/json")
    @POST
    @Consumes(MediaType.APPLICATION_ATOM_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public String testInteger(
            @BodyParam
            @Codex final JsonObject data) {
        return "Number: " + data.encode();
    }
}
