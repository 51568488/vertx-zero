package io.vertx.up.secure.handler;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.web.handler.AuthHandler;

/**
 * User defined authentication for Zero System
 */
@VertxGen
public interface DefaultOstium extends AuthHandler {
    /**
     * Default realm to use
     */
    String DEFAULT_REALM = "zero-auth";
}
