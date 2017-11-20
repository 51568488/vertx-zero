package io.vertx.up.rs.sentry;

import io.vertx.ext.web.api.validation.ValidationHandler;
import io.vertx.up.atom.Depot;
import io.vertx.up.rs.Sentry;
import io.vertx.up.rs.hunt.BaseAim;

import java.lang.reflect.Method;

public class PojoVerifier extends BaseAim implements Sentry {

    @Override
    public ValidationHandler signal(final Depot depot) {

        return (context) -> {
            // Get parameters
            final Object[] params = buildArgs(context, depot.getEvent());
            depot.setParamValues(params);
            // Must call next in Sentry
            System.out.println(params);
            context.next();
        };
    }

    private void verifyEach(final Method method, final Object[] params) {

    }
}
