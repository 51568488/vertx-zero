package io.vertx.up.rs.hunt;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.annotations.Codex;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.Rule;
import io.vertx.up.atom.agent.Depot;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.atom.hold.Virtual;
import io.vertx.up.exception.WebException;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.announce.Rigor;
import io.vertx.up.rs.validation.Validator;
import io.vertx.up.tool.container.KeyPair;
import io.vertx.up.tool.mirror.Anno;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

class Flower {

    private static final Annal LOGGER = Annal.get(Flower.class);

    static <T> Envelop continuous(final RoutingContext context,
                                  final T entity) {
        final Envelop envelop = Envelop.success(entity);
        envelop.setHeaders(context.request().headers());
        envelop.setUser(context.user());
        envelop.setSession(context.session());
        return envelop;
    }

    static void executeRequest(final RoutingContext context,
                               final Map<String, List<Rule>> rulers,
                               final Depot depot,
                               final Object[] args,
                               final Validator verifier) {
        // Extract major object
        final WebException error = verifyPureArguments(verifier, depot, args);
        if (null == error) {

            // Check if annotated with @Codex
            final KeyPair<Integer, Class<?>> found = Anno.findParameter(depot.getEvent().getAction(), Codex.class);
            if (null == found.getValue()) {
                context.next();
            } else {
                // @Codex validation for different types
                final Class<?> type = found.getValue();
                final Object value = args[found.getKey()];
                verifyCodex(context, rulers, depot, type, value);
            }
        } else {
            // Hibernate validate failure
            replyError(context, error, depot.getEvent());
        }
    }

    private static void verifyCodex(final RoutingContext context,
                                    final Map<String, List<Rule>> rulers,
                                    final Depot depot,
                                    final Class<?> type,
                                    final Object value) {
        final Rigor rigor = Rigor.get(type);
        if (null == rigor) {
            LOGGER.warn(Info.RIGOR_NOT_FOUND, type);
            context.next();
        } else {
            final WebException error = rigor.verify(rulers, value);
            if (null == error) {
                // Ignore Errors
                context.next();
            } else {
                // Reply Error
                replyError(context, error, depot.getEvent());
            }
        }
    }

    static void replyError(final RoutingContext context,
                           final WebException error,
                           final Event event) {
        final Envelop envelop = Envelop.failure(error);
        Answer.reply(context, envelop, event);
    }

    private static WebException verifyPureArguments(
            final Validator verifier,
            final Depot depot,
            final Object[] args) {
        final Event event = depot.getEvent();
        final Object proxy = event.getProxy();
        final Method method = event.getAction();
        WebException error = null;
        try {
            if (Virtual.is(proxy)) {
                // TODO: Wait for proxy generation
                // Validation for dynamic proxy
                // final Object delegate = Instance.getProxy(method);
                // verifier.verifyMethod(delegate, method, args);
            } else {
                // Validation for proxy
                verifier.verifyMethod(proxy, method, args);
            }
        } catch (final WebException ex) {
            // Basic validation failure
            error = ex;
        }
        return error;
    }
}
