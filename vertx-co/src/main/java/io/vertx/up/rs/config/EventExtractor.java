package io.vertx.up.rs.config;

import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Extractor;
import org.vie.exception.up.EventSourceException;
import org.vie.fun.HBool;
import org.vie.fun.HNull;
import org.vie.util.log.Annal;
import org.vie.util.mirror.Anno;

import javax.ws.rs.Path;
import java.util.Set;

/**
 * Scanned @EndPoint clazz to build Event metadata
 */
public class EventExtractor implements Extractor<Set<Event>> {

    private static final Annal LOGGER = Annal.get(EventExtractor.class);

    @Override
    public Set<Event> extract(final Class<?> clazz) {
        return HNull.get(clazz, () -> {
            // 1. Event Source Checking
            HBool.execUp(!Anno.isMark(clazz, EndPoint.class),
                    LOGGER, EventSourceException.class,
                    getClass(), clazz.getName());
            // 2. Check whether clazz annotated with @PATH
            HBool.exec(Anno.isMark(clazz, Path.class), LOGGER,
                    () -> {
                        // 3.1. Append Root Path
                    },
                    () -> {
                        // 3.2. Use method Path directly
                    });
            return new ConcurrentHashSet<>();
        }, new ConcurrentHashSet<>());
    }
}
