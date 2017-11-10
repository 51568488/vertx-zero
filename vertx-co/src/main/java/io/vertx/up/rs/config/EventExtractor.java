package io.vertx.up.rs.config;

import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Extractor;
import org.vie.util.log.Annal;

import java.util.Set;

/**
 * Scanned @EndPoint clazz to build Event metadata
 */
public class EventExtractor implements Extractor<Set<Event>> {

    private static final Annal LOGGER = Annal.get(EventExtractor.class);

    @Override
    public Set<Event> extract(final Class<?> clazz) {

        return new ConcurrentHashSet<>();
    }
}
