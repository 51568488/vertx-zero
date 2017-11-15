package io.vertx.up.example;

import io.vertx.up.eon.Strings;

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
