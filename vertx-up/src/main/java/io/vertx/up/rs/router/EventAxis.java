package io.vertx.up.rs.router;

import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.up.atom.Depot;
import io.vertx.up.atom.Event;
import io.vertx.up.exception.EventActionNoneException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.HttpZeroEndurer;
import io.vertx.up.rs.Aim;
import io.vertx.up.rs.Axis;
import io.vertx.up.rs.Sentry;
import io.vertx.up.rs.dispatcher.ModeSplitter;
import io.vertx.up.rs.sentry.MimeAnalyzer;
import io.vertx.up.rs.sentry.StandardVerifier;
import io.vertx.up.web.ZeroAnno;
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
    private transient final Sentry analyzer =
            Fn.poolThread(Pool.ANALYZERS,
                    () -> Instance.instance(MimeAnalyzer.class));

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

                        // 4. Request validation
                        final Depot depot = Depot.create(event);
                        // 5. Request workflow executor: handler
                        final Aim aim = this.splitter.distribute(event);

                        /**
                         * 6. Handler chain
                         * 1) Mime Analyzer ( Build arguments )
                         * 2) Validation
                         * 3) Execute handler ( Code Logical )
                         * 4) Uniform failure handler
                         */
                        route.handler(this.analyzer.signal(depot))
                                .handler(this.verifier.signal(depot))
                                .handler(aim.attack(event))
                                .failureHandler(HttpZeroEndurer.create());
                    });
        });
    }

    private void verify(final Event event) {
        final Method method = event.getAction();
        Fn.flingUp(null == method, LOGGER, EventActionNoneException.class,
                getClass(), event);
    }

}
