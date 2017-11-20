package io.vertx.up.rs;

import io.vertx.ext.web.api.validation.ValidationHandler;
import io.vertx.up.atom.Event;

/**
 * JSR330 signal
 */
public interface Sentry {
    /**
     * @param event
     * @return
     */
    ValidationHandler signal(final Event event);
}
