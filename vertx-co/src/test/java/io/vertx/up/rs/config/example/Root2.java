package io.vertx.up.rs.config.example;

import javax.ws.rs.Path;

@Path("api/")
public class Root2 {

    @Path("/test")
    public void test() {
    }
}
