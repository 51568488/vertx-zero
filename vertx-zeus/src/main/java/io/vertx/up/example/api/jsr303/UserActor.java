package io.vertx.up.example.api.jsr303;

public class UserActor implements UserApi {

    @Override
    public String login(final String username,
                        final String password) {
        return "Hello";
    }
}
