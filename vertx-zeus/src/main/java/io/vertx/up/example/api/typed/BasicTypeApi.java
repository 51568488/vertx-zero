package io.vertx.up.example.api.typed;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Codex;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.BodyParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@EndPoint
@Path("/zero/type")
public class BasicTypeApi {

    @Path("/json")
    @POST
    public String testInteger(
            @BodyParam
            @Codex final JsonObject data) {
        return "Number: " + data.encode();
    }
}
