package io.vertx.up.rs.sentry;

import io.vertx.ext.web.api.validation.ValidationHandler;
import io.vertx.up.atom.Event;
import io.vertx.up.rs.Sentry;
import io.vertx.up.rs.hunt.BaseAim;

public class FieldVerifier extends BaseAim implements Sentry {

    @Override
    public ValidationHandler signal(final Event event) {

        return (context) -> {
            System.out.println("Hello");

            // Must call next in Sentry
            context.next();
        };
    }
}
