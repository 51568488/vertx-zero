package io.vertx.up.media.parse;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Epsilon;
import io.vertx.up.eon.em.MimeFlow;
import io.vertx.up.exception.WebException;
import io.vertx.up.log.Annal;
import io.vertx.up.media.Resolver;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.marshal.node.Node;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.lang.annotation.Annotation;

public class MimeAtomic<T> implements Atomic<T> {

    private static final Node<JsonObject> NODE = Node.infix("resolver");
    private static final Annal LOGGER = Annal.get(MimeAtomic.class);

    @Override
    public Epsilon<T> ingest(final RoutingContext context,
                             final Epsilon<T> income)
            throws WebException {
        final Epsilon<T> epsilon;
        if (MimeFlow.TYPED == income.getMime()) {
            /** Resolver **/
            final Atomic<T> atomic = Instance.singleton(TypedAtomic.class);
            epsilon = atomic.ingest(context, income);
        } else if (MimeFlow.STANDARD == income.getMime()) {
            /** System standard filler **/
            final Atomic<T> atomic = Instance.singleton(StandardAtomic.class);
            epsilon = atomic.ingest(context, income);
        } else {
            /** Resolver **/
            final Resolver<T> resolver = getResolver(context, income);
            epsilon = resolver.resolve(context, income);
        }
        return epsilon;
    }

    private Resolver<T> getResolver(final RoutingContext context,
                                    final Epsilon<T> income) {
        /** 1.Read the resolver first **/
        final Annotation annotation = income.getAnnotation();
        final Class<?> resolverCls = Instance.invoke(annotation, "resolver");
        /** 2.Check configured in default **/
        if (null == resolverCls) {
            /** 3. Old path **/
            final JsonObject content = NODE.read();
            final String header = context.request().getHeader(HttpHeaders.CONTENT_TYPE);
            final String resolver;
            if (null == header) {
                resolver = content.getString("default");
            } else {
                final MediaType type = MediaType.valueOf(header);
                final JsonObject resolverMap = content.getJsonObject(type.getType());
                resolver = resolverMap.getString(type.getSubtype());
            }
            LOGGER.info(Info.RESOLVER, resolver, header, context.request().absoluteURI());
            return Instance.singleton(resolver);
        } else {
            LOGGER.info(Info.RESOLVER_CONFIG, resolverCls);
            return Instance.singleton(resolverCls);
        }
    }
}
