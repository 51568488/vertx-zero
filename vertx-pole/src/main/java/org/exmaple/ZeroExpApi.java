package org.exmaple;

import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/up/example")
@EndPoint
public class ZeroExpApi {

    @GET
    @Path("/first/{name}")
    public String sayZero(
            @PathParam("name") final String name) {
        return "Hello " + name;
    }
}
