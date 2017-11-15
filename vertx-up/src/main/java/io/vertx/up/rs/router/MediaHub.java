package io.vertx.up.rs.router;

import io.vertx.ext.web.Route;
import io.vertx.up.atom.Event;
import io.vertx.zero.eon.Strings;

import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Hub for media type
 * Producer/Consumer
 * register to route to generate media support
 */
public class MediaHub implements Hub {

    @Override
    public void mount(final Route route,
                      final Event event) {
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
