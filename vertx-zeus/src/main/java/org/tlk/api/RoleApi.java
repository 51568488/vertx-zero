package org.tlk.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/zero")
public class RoleApi {

    @GET
    @Path("/start")
    public Role testHello(@QueryParam("name") final String name) {
        final Role role = new Role();
        role.setName(name);
        // ... Do something
        return role;
    }
}
