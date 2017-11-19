package io.vertx.up.rs.router;

import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.up.atom.Event;
import io.vertx.up.exception.EventActionNoneException;
import io.vertx.up.func.Fn;
import io.vertx.up.rs.Aim;
import io.vertx.up.rs.Axis;
import io.vertx.up.rs.Splitter;
import io.vertx.up.rs.hunt.ModeSplitter;
import io.vertx.up.web.ZeroAnno;
import io.vertx.up.log.Annal;
import io.vertx.zero.tool.mirror.Instance;

import java.lang.reflect.Method;
import java.util.Set;

public class EventAxis implements Axis {
    private static final Annal LOGGER = Annal.get(EventAxis.class);
    /**
     * Extract all events that will be generated route.
     */
    private static final Set<Event> EVENTS =
            ZeroAnno.getEvents();
    private transient final Splitter splitter =
            Fn.poolThread(Pool.THREADS,
                    () -> Instance.instance(ModeSplitter.class));

    @Override
    public void mount(final Router router) {
        // Extract Event foreach
        EVENTS.forEach(event -> {
            // Build Route and connect to each Action
            Fn.safeSemi(null == event, LOGGER,
                    () -> LOGGER.warn(Info.NULL_EVENT, getClass().getName()),
                    () -> {
                        // 1. Verify
                        this.verify(event);

                        final Route route = router.route();
                        // 2. Path, Method, Order
                        Hub hub = Fn.poolThread(Pool.URIHUBS,
                                () -> Instance.instance(UriHub.class));
                        hub.mount(route, event);
                        // 3. Consumes/Produces
                        hub = Fn.poolThread(Pool.MEDIAHUBS,
                                () -> Instance.instance(MediaHub.class));
                        hub.mount(route, event);

                        final Aim aim = this.splitter.distribute(event);
                        // 4. Inject handler, event dispatch
                        route.handler(res -> {
                            System.out.println("Hello");
                            res.next();
                        }).handler(aim.attack(event));
                    });
        });
    }

    private void verify(final Event event) {
        final Method method = event.getAction();
        Fn.flingUp(null == method, LOGGER, EventActionNoneException.class,
                getClass(), event);
    }

}
