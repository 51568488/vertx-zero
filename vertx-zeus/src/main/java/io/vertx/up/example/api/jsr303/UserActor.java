package io.vertx.up.example.api.jsr303;

import io.vertx.core.json.JsonObject;
import io.vertx.up.example.domain.Demo;

public class UserActor implements UserApi {

    @Override
    public String login(
            final String username,
            final String password) {
        return "Hello";
    }

    @Override
    public Demo authorize(
            final Demo demo) {
        System.out.println(demo);
        return demo;
    }

    @Override
    public JsonObject serialize(
            final JsonObject data) {
        return data;
    }
}
