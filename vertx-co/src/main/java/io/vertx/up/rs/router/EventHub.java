package io.vertx.up.rs.router;

import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Hub;
import io.vertx.up.rs.VertxAnno;
import org.vie.cv.Strings;

import javax.ws.rs.core.MediaType;
import java.util.Set;

public class EventHub implements Hub {
    /**
     * Extract all events that will be generated route.
     */
    private static final Set<Event> EVENTS =
            VertxAnno.getEvents();

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
                // 4. Inject Handler
                route.handler(res -> {
                    // TODO: Trigger
                    System.out.println("Test");
                    res.response().end("Lang");
                });
            }
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
