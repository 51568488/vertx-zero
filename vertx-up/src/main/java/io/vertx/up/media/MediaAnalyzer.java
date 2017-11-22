package io.vertx.up.media;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.Epsilon;
import io.vertx.up.atom.Event;
import io.vertx.up.exception.WebException;
import io.vertx.up.func.Fn;
import io.vertx.up.media.unit.Atomic;
import io.vertx.up.media.unit.RailAtomic;
import io.vertx.zero.tool.mirror.Instance;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class MediaAnalyzer implements Analyzer {

    private final transient Atomic<Object> atomic =
            Instance.singleton(RailAtomic.class);

    @Override
    public Object[] in(final RoutingContext context,
                       final Event event)
            throws WebException {
        /** Consume mime type matching **/
        final MediaType requestMedia = MediaType.valueOf(
                context.request().getHeader(HttpHeaders.CONTENT_TYPE));
        MediaAtom.accept(event, requestMedia);

        /** Selector execute for Analyzer selection **/
        final Method method = event.getAction();

        /** Extract definition from method **/
        final List<Epsilon<Object>> epsilons =
                this.in(context, method);

        /** Extract value list **/
        return epsilons.stream()
                .map(Epsilon::getValue).toArray();
    }

    private List<Epsilon<Object>> in(final RoutingContext context,
                                     final Method method)
            throws WebException {
        final Class<?>[] paramTypes = method.getParameterTypes();
        final Annotation[][] annoTypes = method.getParameterAnnotations();
        final List<Epsilon<Object>> args = new ArrayList<>();
        for (int idx = 0; idx < paramTypes.length; idx++) {

            /** For each field specification **/
            final Epsilon<Object> epsilon = new Epsilon<>();
            epsilon.setArgType(paramTypes[idx]);
            epsilon.setAnnotations(annoTypes[idx]);

            /** Epsilon income -> outcome **/
            final Epsilon<Object> outcome =
                    this.atomic.ingest(context, epsilon);
            args.add(Fn.get(() -> outcome, outcome));
        }
        return args;
    }

    @Override
    public Envelop out(final Envelop envelop,
                       final Event event) throws WebException {
        // TODO: Replier
        return null;
    }
}
