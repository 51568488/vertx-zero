package org.tlk.app;

import io.vertx.up.annotations.Up;
import io.vertx.up.VertxApplication;

@Up
public class Driver {

    public static void main(final String[] args) {
        VertxApplication.run(Driver.class);
    }
}
