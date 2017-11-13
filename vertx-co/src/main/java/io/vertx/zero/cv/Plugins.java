package io.vertx.zero.cv;

/**
 * Default plugin applied to vertx
 */
public interface Plugins {

    String INJECT = "inject";

    String SERVER = "server";

    String ERROR = "error";

    String[] DATA = new String[]{
            INJECT, SERVER, ERROR
    };
}
