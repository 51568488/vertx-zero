package io.vertx.up.rs.executor;

import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@EndPoint
public class HelloParam {

    public HelloParam() {
    }

    public HelloParam(final String name) {
    }

    @Path("/query")
    @GET
    public void sayHelly(final String name) {
        System.out.println(name);
    }
}
