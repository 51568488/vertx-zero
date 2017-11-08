package com.tlk.agent;

import io.vertx.up.annotations.Agent;
import io.vertx.up.annotations.Route;

@Agent
public class UserAgent {

    @Route
    public String test() {

        return "";
    }
}
