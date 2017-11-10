package io.vertx.up.rs.config.example;

import org.vie.cv.Strings;

import javax.ws.rs.GET;

public class RMethod1 {
    @GET
    public String sayHell() {
        return Strings.EMPTY;
    }

    public String sayHell1() {
        return null;
    }
}
