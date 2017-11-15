package io.vertx.up.eon;

import io.vertx.up.annotations.infix.Mongo;

import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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

    // Default infix
    interface Infix {

        String MONGO = "mongo";

        String[] DATA = new String[]{
                MONGO
        };
    }

    ConcurrentMap<Class<? extends Annotation>, String> INFIX_MAP =
            new ConcurrentHashMap<Class<? extends Annotation>, String>() {
                {
                    put(Mongo.class, Infix.MONGO);
                }
            };
}
