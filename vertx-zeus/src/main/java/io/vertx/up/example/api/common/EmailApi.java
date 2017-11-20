package io.vertx.up.example.api.common;

import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/zero/email")
@EndPoint
public class EmailApi {

    @Path("/send")
    @GET
    public String sayHello() {
        return "Hello Send Email";
    }
}
