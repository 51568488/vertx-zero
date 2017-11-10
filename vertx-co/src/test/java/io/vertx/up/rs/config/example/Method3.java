package io.vertx.up.rs.config.example;

import javax.ws.rs.Path;

@Path("api")
public class Method3 {

    @Path("test////:name")
    public void test() {
    }

    @Path("///test/:id")
    public void test1() {
    }
}
