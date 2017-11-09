package com.tlk.agent;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.up.annotations.Routine;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/test")
@Routine
public class User {

    @GET
    public String test(final HttpServerRequest request) {
        return "Hello World";
    }
}
