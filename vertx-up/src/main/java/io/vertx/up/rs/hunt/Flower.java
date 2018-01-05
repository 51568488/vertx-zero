package io.vertx.up.rs.hunt;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.Rule;
import io.vertx.up.atom.Virtual;
import io.vertx.up.atom.agent.Depot;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.exception.WebException;
import io.vertx.up.rs.regular.Ruler;
import io.vertx.zero.eon.Values;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

class Flower {

    static <T> Envelop continuous(final RoutingContext context,
                                  final T entity) {
        final Envelop envelop = Envelop.success(entity);
        envelop.setHeaders(context.request().headers());
        envelop.setUser(context.user());
        return envelop;
    }

    static void executeRequest(final RoutingContext context,
                               final Map<String, List<Rule>> rulers,
                               final Depot depot,
                               final Object[] args,
                               final Validator verifier) {
        // Extract major object
        WebException error = verifyPureArguments(verifier, depot, args);
        // 1.Basic validation passed.
        if (null == error) {
            // 2. Body validation for jsonobject
            error = verifyBody(rulers, args);
            if (null == error) {
                context.next();
            } else {
                // Body validation of rulers failure
                replyError(context, error, depot.getEvent());
            }
        } else {
            // Hibernate validate failure
            replyError(context, error, depot.getEvent());
        }
    }

    private static void replyError(final RoutingContext context,
                                   final WebException error,
                                   final Event event) {
        final Envelop envelop = Envelop.failure(error);
        Answer.reply(context, envelop, event);
    }

    private static WebException verifyBody(
            final Map<String, List<Rule>> rulers,
            final Object[] args) {
        WebException error = null;
        if (!rulers.isEmpty()) {
            // Extract the first element
            final Object body = args[Values.IDX];
            if (null != body) {
                final JsonObject data = (JsonObject) body;
                // Verified
                error = verifyBody(rulers, data);
            }
        }
        return error;
    }

    private static WebException verifyBody(
            final Map<String, List<Rule>> rulers,
            final JsonObject data) {
        WebException error = null;
        outer:
        for (final String field : rulers.keySet()) {
            final Object value = data.getValue(field);
            final List<Rule> rules = rulers.get(field);
            // Search for each rule
            for (final Rule rule : rules) {
                final Ruler ruler = Ruler.get(rule.getType());
                if (null != ruler) {
                    error = ruler.verify(field, value, rule);
                }
                // Error found.
                if (null != error) {
                    break outer;
                }
                // Else skip
            }
        }
        return error;
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
