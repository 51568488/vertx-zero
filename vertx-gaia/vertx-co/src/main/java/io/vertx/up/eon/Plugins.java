package io.vertx.up.eon;

import javax.inject.Inject;
import javax.inject.infix.Jooq;
import javax.inject.infix.Mongo;
import javax.inject.infix.MySql;
import javax.inject.infix.Rpc;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Default plugin applied to vertx
 */
public interface Plugins {

    String INJECT = "inject";

    String SERVER = "server";

    String ERROR = "error";

    String RESOLVER = "resolver";

    String[] DATA = new String[]{
            INJECT, ERROR, SERVER,
            RESOLVER
    };

    // Default infix
    interface Infix {

        String MONGO = "mongo";

        String MYSQL = "mysql";

        String JOOQ = "jooq";

        String RPC = "rpc";

        String[] DATA = new String[]{
                MONGO, MYSQL, JOOQ, RPC
        };
    }

    ConcurrentMap<Class<? extends Annotation>, String> INFIX_MAP =
            new ConcurrentHashMap<Class<? extends Annotation>, String>() {
                {
                    put(Mongo.class, Infix.MONGO);
                    put(MySql.class, Infix.MYSQL);
                    put(Jooq.class, Infix.JOOQ);
                    put(Rpc.class, Infix.RPC);
                }
            };

    Set<Class<? extends Annotation>> INJECT_ANNOTATIONS = new HashSet<Class<? extends Annotation>>() {
        {
            addAll(INFIX_MAP.keySet());
            add(Inject.class);
        }
    };
}
