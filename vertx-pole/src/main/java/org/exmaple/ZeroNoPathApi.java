package org.exmaple;

import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@EndPoint
public class ZeroNoPathApi {

    @GET
    @Path("/up/example/non-path")
    public String getZero(
            @QueryParam("name") final String name) {
        return "No Path " + name;
    }
}
