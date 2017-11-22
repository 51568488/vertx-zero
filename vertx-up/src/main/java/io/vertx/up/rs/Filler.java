package io.vertx.up.rs;

import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.rs.argument.*;
import io.vertx.zero.tool.mirror.Instance;

import javax.ws.rs.*;
import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Fill the arguments into reference list
 * as arguments
 */
public interface Filler {
    /**
     * @param name
     * @param paramType
     * @param datum
     * @return
     */
    Object apply(String name,
                 Class<?> paramType,
                 RoutingContext datum);

    ConcurrentMap<Class<? extends Annotation>, Filler> PARAMS =
            new ConcurrentHashMap<Class<? extends Annotation>, Filler>() {
                {
                    // JSR311 Provided
                    put(QueryParam.class, Instance.singleton(QueryFiller.class));
                    put(FormParam.class, Instance.singleton(FormFiller.class));
                    put(MatrixParam.class, Instance.singleton(QueryFiller.class));
                    put(PathParam.class, Instance.singleton(PathFiller.class));
                    put(HeaderParam.class, Instance.singleton(HeaderFiller.class));
                    put(CookieParam.class, Instance.singleton(CookieFiller.class));
                    // Extension
                    put(BodyParam.class, Instance.singleton(EmptyFiller.class));
                    put(StreamParam.class, Instance.singleton(EmptyFiller.class));
                    put(SessionParam.class, Instance.singleton(SessionFiller.class));
                }
            };

    Set<Class<? extends Annotation>> NO_VALUE =
            new ConcurrentHashSet<Class<? extends Annotation>>() {
                {
                    add(BodyParam.class);
                    add(StreamParam.class);
                }
            };
}
