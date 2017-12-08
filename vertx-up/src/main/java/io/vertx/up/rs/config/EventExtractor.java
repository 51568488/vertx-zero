package io.vertx.up.rs.config;

import com.google.common.collect.Sets;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.atom.Event;
import io.vertx.up.atom.Virtual;
import io.vertx.up.exception.AccessProxyException;
import io.vertx.up.exception.EventSourceException;
import io.vertx.up.exception.NoArgConstructorException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Extractor;
import io.vertx.up.tool.StringUtil;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.ZeroHelper;

import javax.ws.rs.Path;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/**
 * Scanned @EndPoint clazz to build Event metadata
 */
public class EventExtractor implements Extractor<Set<Event>> {

    private static final Annal LOGGER = Annal.get(EventExtractor.class);

    @Override
    public Set<Event> extract(final Class<?> clazz) {
        return Fn.get(Sets.newConcurrentHashSet(), () -> {
            // 1. Class verify
            verify(clazz);
            // 2. Check whether clazz annotated with @PATH
            final Set<Event> result = new ConcurrentHashSet<>();
            Fn.safeSemi(clazz.isAnnotationPresent(Path.class), LOGGER,
                    () -> {
                        // 3.1. Append Root Path
                        final Path path = ZeroHelper.getPath(clazz);
                        assert null != path : "Path should not be null.";
                        result.addAll(extract(clazz, PathResolver.resolve(path)));
                    },
                    () -> {
                        // 3.2. Use method Path directly
                        result.addAll(extract(clazz, null));
                    });
            return result;
        }, clazz);
    }

    private void verify(final Class<?> clazz) {
        // Check basic specification: No Arg Constructor
        if (!clazz.isInterface()) {
            // Class direct.
            Fn.flingUp(!Instance.noarg(clazz), LOGGER,
                    NoArgConstructorException.class,
                    getClass(), clazz);
        }
        Fn.flingUp(!Modifier.isPublic(clazz.getModifiers()), LOGGER,
                AccessProxyException.class,
                getClass(), clazz);
        // Event Source Checking
        Fn.flingUp(!clazz.isAnnotationPresent(EndPoint.class),
                LOGGER, EventSourceException.class,
                getClass(), clazz.getName());
    }

    private Set<Event> extract(final Class<?> clazz, final String root) {
        final Set<Event> events = new HashSet<>();
        // 0.Preparing
        final Method[] methods = clazz.getDeclaredMethods();
        for (final Method method : methods) {
            // 1.Build Event
            final Event event = extract(method, root);
            if (null != event) {
                events.add(event);
            }
        }
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
        {
            // 3.1. Get path from method
            final Path path = ZeroHelper.getPath(method);
            if (null == path) {
                // 3.2. Check root double check
                if (!StringUtil.isNil(root)) {
                    // Use root directly.
                    event.setPath(root);
                }
            } else {
                final String result = PathResolver.resolve(
                        path, root);
                event.setPath(result);
            }
        }
        // 4.Action
        event.setAction(method);
        // 6.Mime resolve
        event.setConsumes(MediaResolver.consumes(method));
        event.setProduces(MediaResolver.produces(method));
        // 7. Instance clazz for proxy
        final Class<?> clazz = method.getDeclaringClass();
        final Object proxy;
        if (clazz.isInterface()) {
            final Class<?> implClass = Instance.uniqueChild(clazz);
            if (null != implClass) {
                proxy = Instance.singleton(implClass);
            } else {
                /**
                 * SPEC5: Interface only, direct api, in this situation,
                 * The proxy is null and the agent do nothing. The data will
                 * send to event bus direct. It's not needed to set
                 * implementation class.
                 */
                proxy = Virtual.create();
            }
        } else {
            proxy = Instance.singleton(method.getDeclaringClass());
        }
        event.setProxy(proxy);
        return event;
    }
}
