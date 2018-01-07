package io.vertx.up.rs.router;

import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.agent.Depot;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Aim;
import io.vertx.up.rs.Axis;
import io.vertx.up.rs.Sentry;
import io.vertx.up.rs.dispatch.ModeSplitter;
import io.vertx.up.rs.dispatch.StandardVerifier;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.ZeroAnno;
import io.vertx.up.web.failure.CommonEndurer;

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
    private transient final Sentry<RoutingContext> verifier =
            Fn.poolThread(Pool.VERIFIERS,
                    () -> Instance.instance(StandardVerifier.class));

    /**
     * Secreter for security limitation
     * 1. Authorization
     * 2. Authorize
     */

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
                        final Aim<RoutingContext> aim = this.splitter.distribute(event);

                        /**
                         * 6. Handler chain
                         * 1) Mime Analyzer ( Build arguments )
                         * 2) Validation
                         * 3) Execute handler ( Code Logical )
                         * 4) Uniform failure handler
                         */
                        route.handler(this.verifier.signal(depot))
                                .handler(aim.attack(event))
                                .failureHandler(CommonEndurer.create());
                    });
        });
    }
}
