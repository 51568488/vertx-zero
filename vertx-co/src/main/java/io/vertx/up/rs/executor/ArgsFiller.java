package io.vertx.up.rs.executor;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.ce.Event;
import org.vie.util.mirror.Anno;

import javax.ws.rs.*;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Arguments process bus
 */
class ArgsFiller {
    /**
     * Process reference for
     *
     * @param params
     * @param context
     * @param event
     */
    public static void process(final List<Object> params,
                               final RoutingContext context,
                               final Event event) {
        // 1. Extract definition from event
        final Method method = event.getAction();
        System.out.println(context.request().headers());
        if (Anno.isMark(method, FormParam.class)) {
            // 2.1. Form Parameters
        } else if (Anno.isMark(method, QueryParam.class)) {
            // 2.2. Query Parameters
        } else if (Anno.isMark(method, HeaderParam.class)) {
            // 2.3. Header Parameters
        } else if (Anno.isMark(method, PathParam.class)) {
            // 2.4. Path Parameters
        } else if (Anno.isMark(method, CookieParam.class)) {
            // 2.5. Cookie Parameters
        } else if (Anno.isMark(method, MatrixParam.class)) {
            // 2.6. Matrix Parameters
        } else if (Anno.isMark(method, BodyParam.class)) {
            // 2.7. Body Parameters
        }
    }
}
