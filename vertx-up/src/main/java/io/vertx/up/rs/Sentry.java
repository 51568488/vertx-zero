package io.vertx.up.rs;

import io.vertx.ext.web.api.validation.ValidationHandler;
import io.vertx.up.atom.Depot;

/**
 * JSR330 signal
 */
public interface Sentry {
    /**
     * @param depot
     * @return
     */
    ValidationHandler signal(final Depot depot);
}
