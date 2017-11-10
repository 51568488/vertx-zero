package io.vertx.up.rs.config;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.VertxHelper;
import org.vie.exception.up.EventSourceException;
import org.vie.fun.HBool;
import org.vie.fun.HNull;
import org.vie.util.log.Annal;
import org.vie.util.mirror.Anno;

import javax.ws.rs.Path;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Scanned @EndPoint clazz to build Event metadata
 */
public class EndPointExtractor implements Extractor<Set<Event>> {

    private static final Annal LOGGER = Annal.get(EndPointExtractor.class);

    @Override
    public Set<Event> extract(final Class<?> clazz) {
        return HNull.get(clazz, () -> {
            // 1. Event Source Checking
            HBool.execUp(!Anno.isMark(clazz, EndPoint.class),
                    LOGGER, EventSourceException.class,
                    getClass(), clazz.getName());
            // 2. Check whether clazz annotated with @PATH
            final Set<Event> result = new ConcurrentHashSet<>();
            HBool.exec(Anno.isMark(clazz, Path.class), LOGGER,
                    () -> {
                        // 3.1. Append Root Path
                        final Path path = VertxHelper.getPath(clazz);
                        assert null != path : "Path should not be null.";
                        result.addAll(extract(clazz, PathResolver.resolve(path)));
                    },
                    () -> {
                        // 3.2. Use method Path directly
                        result.addAll(extract(clazz, null));
                    });
            return result;
        }, new ConcurrentHashSet<>());
    }

    private Set<Event> extract(final Class<?> clazz, final String root) {
        final Set<Event> events = new ConcurrentHashSet<>();
        // 0.Preparing
        final Method[] methods = clazz.getDeclaredMethods();
        for (final Method method : methods) {
            // 1.Get Path from method
            final Path path = VertxHelper.getPath(method);
            if (null != path) {
                // 2.Build Event
                final Event event = extract(method, root);
                if (null != event) {
                    events.add(event);
                }
            }
        }
        // 1.Path resolving
        return events;
    }

    /**
     * Scan for single
     *
     * @param method
     * @param root
     * @return
     */
    private Event extract(final Method method, final String root) {
        // 1.Method path
        final Event event = new Event();
        // 2.Method resolve
        final HttpMethod httpMethod = MethodResolver.resolve(method);
        if (null == httpMethod) {
            // Ignored the method could not be annotated.
            return null;
        } else {
            event.setMethod(httpMethod);
        }
        // 3.Path resolve
        final String path = PathResolver.resolve(
                VertxHelper.getPath(method), root);
        event.setPath(path);
        // 4.Action
        event.setAction(method);
        // 6.Mime resolve
        event.setConsumes(MediaResolver.consumes(method));
        event.setProduces(MediaResolver.produces(method));
        System.out.println(event);
        return event;
    }
}
