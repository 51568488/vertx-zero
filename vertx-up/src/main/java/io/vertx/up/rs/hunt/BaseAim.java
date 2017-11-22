package io.vertx.up.rs.hunt;

import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.annotations.Address;
import io.vertx.up.atom.EnvelopOld;
import io.vertx.up.atom.Event;
import io.vertx.up.eon.ID;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception.web._500DeliveryErrorException;
import io.vertx.up.exception.web._500EntityCastException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.media.Analyzer;
import io.vertx.up.media.MediaAnalyzer;
import io.vertx.up.rs.mirror.ParamFiller;
import io.vertx.zero.tool.StringUtil;
import io.vertx.zero.tool.mirror.Instance;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class to provide template method
 */
public abstract class BaseAim {

    private transient final Verifier verifier =
            Verifier.create();

    private transient final Analyzer analyzer =
            Instance.singleton(MediaAnalyzer.class);

    /**
     * Template method
     *
     * @param context
     * @param event
     * @return
     */
    @Deprecated
    protected Object[] buildArgs(final RoutingContext context,
                                 final Event event) {
        Object[] cached = context.get(ID.PARAMS);
        if (null == cached) {
            // 1. Call action
            final Method method = event.getAction();
            final List<Object> arguments = new ArrayList<>();

            // 2. Extract definition from method
            final Class<?>[] parameterTypes = method.getParameterTypes();
            final Annotation[][] annotations = method.getParameterAnnotations();
            for (int idx = 0; idx < parameterTypes.length; idx++) {
                // 3. Process filler to build parameters.
                arguments.add(ParamFiller.process(context,
                        parameterTypes[idx], annotations[idx]));
            }
            cached = arguments.toArray();
            context.put(ID.PARAMS, cached);
        }
        // Validation handler has been get the parameters.
        return cached;
    }


    /**
     * Get event bus address.
     *
     * @param event
     * @return
     */
    protected String address(final Event event) {
        final Method method = event.getAction();
        final Annotation annotation = method.getDeclaredAnnotation(Address.class);
        return Instance.invoke(annotation, "value");
    }

    /**
     * @param event
     * @param args
     * @return
     */
    protected Object invoke(final Event event, final Object[] args) {
        final Method method = event.getAction();
        getLogger().info("[ ZERO-DEBUG ] Method = {0}, Args = {1}",
                method.getName(), StringUtil.join(args));
        return Instance.invoke(event.getProxy(), method.getName(), args);
    }

    protected EnvelopOld failure(final String address,
                                 final AsyncResult<Message<EnvelopOld>> handler) {
        final WebException error
                = new _500DeliveryErrorException(getClass(),
                address,
                Fn.get(null,
                        () -> handler.cause().getMessage(), handler.cause()));
        return EnvelopOld.failure(error);
    }

    protected EnvelopOld success(final String address,
                                 final AsyncResult<Message<EnvelopOld>> handler
    ) {
        EnvelopOld envelop;
        try {
            final Message<EnvelopOld> message = handler.result();
            envelop = message.body();
        } catch (final Throwable ex) {
            final WebException error
                    = new _500EntityCastException(getClass(),
                    address, ex.getMessage());
            envelop = EnvelopOld.failure(error);
        }
        return envelop;
    }

    protected Verifier verifier() {
        return this.verifier;
    }

    protected Analyzer analyzer() {
        return this.analyzer;
    }

    protected Annal getLogger() {
        return Annal.get(getClass());
    }
}
