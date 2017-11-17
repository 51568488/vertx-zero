package io.vertx.up.eon;

import javax.inject.infix.Mongo;
import javax.inject.infix.MySql;
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
    /**
     * Internal
     **/
    String FAILURE = "failure";

    String[] DATA = new String[]{
            INJECT, ERROR, FAILURE, SERVER
    };

    // Default infix
    interface Infix {

        String MONGO = "mongo";

        String MYSQL = "mysql";

        String[] DATA = new String[]{
                MONGO, MYSQL
        };
    }

    ConcurrentMap<Class<? extends Annotation>, String> INFIX_MAP =
            new ConcurrentHashMap<Class<? extends Annotation>, String>() {
                {
                    put(Mongo.class, Infix.MONGO);
                    put(MySql.class, Infix.MYSQL);
                }
            };
}
