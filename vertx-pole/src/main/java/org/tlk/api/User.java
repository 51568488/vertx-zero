package org.tlk.api;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.*;

@Path("/test")
@EndPoint
public class User {

    @GET
    @Path("/path/{name}")
    public String testPath(@PathParam("name") final String name) {
        System.out.println(name);
        return "Hello World";
    }

    @POST
    @Path("/return/model")
    public Model testBodyReturn(@BodyParam final JsonObject context) {
        final Model user = new Model();
        user.setEmail("silentbalanceyh@126.com");
        user.setName("Lang.yu");
        return user;
    }

    @POST
    @Path("/send/message")
    @Address("ZERO://USER")
    public Model testEvent(@BodyParam final JsonObject context) {
        final Model user = new Model();
        user.setEmail("silentbalanceyh@126.com");
        user.setName("Lang.yu");
        return user;
    }
}
