package org.exmaple;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;

import javax.ws.rs.*;

@Path("/up/example")
public class ZeroExpApi {

    @GET
    @Path("/first/{name}")
    public String sayZero(
            @PathParam("name") final String name) {
        return "Hello " + name;
    }

    @POST
    @Path("/body/json")
    public JsonObject sayBody(
            @BodyParam final JsonObject data
    ) {
        return data;
    }

    @POST
    @Path("/body/pojo")
    public Demo sayPojo(
            @BodyParam final Demo data
    ) {
        return data;
    }

    @GET
    @Path("/typed/request")
    public String sayBody(
            final HttpServerRequest request
    ) {
        return request.absoluteURI();
    }
}
