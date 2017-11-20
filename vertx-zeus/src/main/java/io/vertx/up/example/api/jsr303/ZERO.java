package io.vertx.up.example.api.jsr303;

interface Validation {

    interface UserName {

        String NOT_NULL = "{user.login.username.required}";
    }

    interface Password {
        String NOT_NULL = "{user.login.password.required}";
    }
}
