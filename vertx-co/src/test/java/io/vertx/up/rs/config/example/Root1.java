package io.vertx.up.rs.config.example;

import javax.ws.rs.Path;

/**
 * 1. Root: api
 */
@Path("api")
public class Root1 {

    @Path("/test")
    public void test() {
    }
}
