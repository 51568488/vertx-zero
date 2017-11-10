package io.vertx.up.rs.config.example;

import javax.ws.rs.Path;

@Path("api")
public class Method2 {

    @Path("test////:name")
    public void test() {
    }
}
