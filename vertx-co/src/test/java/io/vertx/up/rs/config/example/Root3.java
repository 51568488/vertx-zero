package io.vertx.up.rs.config.example;

import javax.ws.rs.Path;

/**
 * 3. Root: api//
 */
@Path("///api///")
public class Root3 {

    @Path("/test")
    public void test() {
    }
}
