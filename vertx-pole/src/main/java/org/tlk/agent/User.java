package org.tlk.agent;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.*;

@Path("/test")
@EndPoint
public class User {

    @GET
    @Path("/{name}")
    public String testPath(@PathParam("name") final String name) {
        System.out.println(name);
        return "Hello World";
    }

    @POST
    @Path("/post")
    public void testBody(@BodyParam final JsonObject content) {
        System.out.println(content);
    }

    @POST
    @Path("/lang")
    public Model testBodyReturn(@BodyParam final JsonObject context) {
        final Model user = new Model();
        user.setEmail("lang.yu@hpe.com");
        user.setName("Lang.yu");
        return user;
    }

    @POST
    @Path("/message")
    @Address("ZERO://USER")
    public Model testEvent(@BodyParam final JsonObject context) {
        final Model user = new Model();
        user.setEmail("lang.yu@hpe.com");
        user.setName("Lang.yu");
        return user;
    }
}
