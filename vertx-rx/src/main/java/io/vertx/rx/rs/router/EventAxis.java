package io.vertx.rx.rs.router;

import io.vertx.reactivex.ext.web.Route;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.up.atom.Depot;
import io.vertx.up.atom.Event;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Aim;
import io.vertx.up.rs.Axis;
import io.vertx.up.rs.Sentry;
import io.vertx.up.rs.dispatcher.ModeSplitter;
import io.vertx.up.rs.router.Hub;
import io.vertx.up.rs.router.Verifier;
import io.vertx.up.rs.sentry.StandardVerifier;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.ZeroAnno;

import java.util.Set;

public class EventAxis implements Axis<Router> {
    private static final Annal LOGGER = Annal.get(EventAxis.class);
    /**
     * Extract all events that will be generated route.
     */
    private static final Set<Event> EVENTS =
            ZeroAnno.getEvents();
    /**
     * Splitter
     */
    private transient final ModeSplitter splitter =
            Fn.poolThread(Pool.THREADS,
                    () -> Instance.instance(ModeSplitter.class));
    /**
     * Sentry
     */
    private transient final Sentry verifier =
            Fn.poolThread(Pool.VERIFIERS,
                    () -> Instance.instance(StandardVerifier.class));

    @Override
    public void mount(final Router router) {
        // Extract Event foreach
        EVENTS.forEach(event -> {
            // Build Route and connect to each Action
            Fn.safeSemi(null == event, LOGGER,
                    () -> LOGGER.warn(Info.NULL_EVENT, getClass().getName()),
                    () -> {
                        // 1. Verify
                        Verifier.verify(event);

                        final Route route = router.route();
                        // 2. Path, Method, Order
                        Hub<Route> hub = Fn.poolThread(Pool.URIHUBS,
                                () -> Instance.instance(UriHub.class));
                        hub.mount(route, event);
                        // 3. Consumes/Produces
                        hub = Fn.poolThread(Pool.MEDIAHUBS,
                                () -> Instance.instance(MediaHub.class));
                        hub.mount(route, event);

                        // 4. Request validation
                        final Depot depot = Depot.create(event);
                        // 5. Request workflow executor: handler
                        final Aim aim = this.splitter.distribute(event);
                        route.handler(context -> {
                            System.out.println("Hello");
                            context.next();
                        });
                    });
        });
    }
}
