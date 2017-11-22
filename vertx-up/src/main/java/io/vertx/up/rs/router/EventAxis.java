package io.vertx.up.rs.router;

import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.up.atom.Depot;
import io.vertx.up.atom.Event;
import io.vertx.up.exception.AnnotationRepeatException;
import io.vertx.up.exception.EventActionNoneException;
import io.vertx.up.exception.ParamAnnotationException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.ZeroHttpEndurer;
import io.vertx.up.rs.Aim;
import io.vertx.up.rs.Axis;
import io.vertx.up.rs.Filler;
import io.vertx.up.rs.Sentry;
import io.vertx.up.rs.dispatcher.ModeSplitter;
import io.vertx.up.rs.sentry.StandardVerifier;
import io.vertx.up.web.ZeroAnno;
import io.vertx.zero.tool.mirror.Anno;
import io.vertx.zero.tool.mirror.Instance;

import javax.ws.rs.BodyParam;
import javax.ws.rs.StreamParam;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                        route.handler(this.verifier.signal(depot))
                                .handler(aim.attack(event))
                                .failureHandler(ZeroHttpEndurer.create());
                    });
        });
    }

    private void verify(final Event event) {
        final Method method = event.getAction();
        Fn.flingUp(null == method, LOGGER, EventActionNoneException.class,
                getClass(), event);
        /** Specification **/
        verify(method, BodyParam.class);
        verify(method, StreamParam.class);
        /** Field Specification **/
        for (final Parameter parameter : method.getParameters()) {
            verify(parameter);
        }
    }

    private void verify(final Method method, final Class<? extends Annotation> annoCls) {
        final Annotation[][] annotations = method.getParameterAnnotations();
        final int occurs = Anno.occurs(annotations, annoCls);

        Fn.flingUp(1 < occurs, LOGGER, AnnotationRepeatException.class,
                getClass(), method.getName(), annoCls, occurs);
    }

    private void verify(final Parameter parameter) {
        final Annotation[] annotations = parameter.getDeclaredAnnotations();
        final List<Annotation> annotationList = Arrays.stream(annotations)
                .filter(item -> Filler.PARAMS.containsKey(item.annotationType()))
                .collect(Collectors.toList());

        final int multi = annotationList.size();
        Fn.flingUp(1 < multi, LOGGER, ParamAnnotationException.class,
                getClass(), parameter.getName(), multi);
    }
}
