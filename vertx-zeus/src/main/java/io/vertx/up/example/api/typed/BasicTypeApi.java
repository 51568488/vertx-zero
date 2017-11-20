package io.vertx.up.example.api.typed;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

@EndPoint
@Path("/zero/type")
public class BasicTypeApi {

    @Path("/integer")
    @GET
    public String testInteger(
            @QueryParam("i") final int integer) {
        return "Number: " + integer;
    }

    @Path("/list")
    @GET
    public List<JsonObject> testJObject() {
        final List<JsonObject> json = new ArrayList<>();
        json.add(new JsonObject().put("Hello", "World"));
        return json;
    }
}
