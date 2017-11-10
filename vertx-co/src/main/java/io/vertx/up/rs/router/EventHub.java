package io.vertx.up.rs.router;

import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.annotations.Address;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Executor;
import io.vertx.up.rs.Hub;
import io.vertx.up.rs.executor.DirectHandler;
import io.vertx.up.rs.executor.EventBusHandler;
import io.vertx.up.web.ZeroAnno;
import org.vie.cv.Strings;
import org.vie.exception.up.EventActionNoneException;
import org.vie.fun.HBool;
import org.vie.util.Instance;
import org.vie.util.log.Annal;
import org.vie.util.mirror.Anno;

import javax.ws.rs.core.MediaType;
import java.lang.reflect.Method;
import java.util.Set;

public class EventHub implements Hub {
    private static final Annal LOGGER = Annal.get(EventHub.class);
    /**
     * Extract all events that will be generated route.
     */
    private static final Set<Event> EVENTS =
            ZeroAnno.getEvents();

    @Override
    public void mount(final Router router) {
        // Extract Event foreach
        for (final Event event : EVENTS) {
            // 1. Build Route
            if (null != event) {
                final Route route = router.route();
                // 2. Path, Method, Order
                route.path(event.getPath())
                        .method(event.getMethod())
                        .order(event.getOrder());
                // 3. Consumes/Produces
                buildMedia(route, event);
                // 4. Inject handler, event dispatch
                route.handler(res -> dispatch(res, event));
            }
        }
    }

    private void dispatch(final RoutingContext context,
                          final Event event) {
        // 1. Get method from Event
        final Method method = event.getAction();
        HBool.execUp(null == method, LOGGER, EventActionNoneException.class,
                getClass(), event);
        // 2. Scan method to check @Address
        final boolean annotated = Anno.isMark(method, Address.class);
        if (annotated) {
            // 3.1. Event Bus Executing
            LOGGER.info(Message.DISPATCH, "EventBus", EventBusHandler.class.getName());
            final Executor executor = Instance.singleton(EventBusHandler.class);
            executor.execute(context, event);
        } else {
            LOGGER.info(Message.DISPATCH, "Non-EventBus", DirectHandler.class.getName());
            // 3.2. Response directly
            final Executor executor = Instance.singleton(DirectHandler.class);
            executor.execute(context, event);
        }
    }

    private void buildMedia(final Route route, final Event event) {
        // produces
        final Set<MediaType> produces = event.getProduces();
        for (final MediaType type : produces) {
            final String item = type.getType() + Strings.SLASH + type.getSubtype();
            route.produces(item);
        }
        // consumes
        final Set<MediaType> consumes = event.getProduces();
        for (final MediaType type : consumes) {
            final String item = type.getType() + Strings.SLASH + type.getSubtype();
            route.consumes(item);
        }
    }
}
