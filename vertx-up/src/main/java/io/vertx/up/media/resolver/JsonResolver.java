package io.vertx.up.media.resolver;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Epsilon;
import io.vertx.up.exception.WebException;
import io.vertx.up.media.Resolver;
import io.vertx.up.web.ZeroSerializer;

/**
 * Json Resolver
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class JsonResolver<T> implements Resolver<T> {

    @Override
    public Epsilon<T> resolve(final RoutingContext context,
                              final Epsilon<T> income)
            throws WebException {
        // Json Resolver
        final String content = context.getBodyAsString();
        final Object result = ZeroSerializer.getValue(income.getArgType(), content);
        if (null != result) {
            income.setValue((T) result);
        }
        return income;
    }
}
