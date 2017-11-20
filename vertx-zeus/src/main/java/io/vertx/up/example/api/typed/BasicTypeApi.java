package io.vertx.up.example.api.typed;

import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@EndPoint
@Path("/zero/type")
public class BasicTypeApi {

    @Path("/integer")
    @GET
    public String testInteger(
            @QueryParam("i") final int integer) {
        return "Number: " + integer;
    }
}
