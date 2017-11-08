package com.tlk.agent;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/test")
public class UserAgent {

    @GET
    public String test() {

        return "Hello World";
    }
}
