package io.vertx.up.rs.dispatcher;

import io.vertx.up.atom.Depot;
import io.vertx.up.rs.Sentry;
import io.vertx.up.rs.sentry.StandardVerifier;
import io.vertx.zero.tool.mirror.Instance;

/**
 * Validation for request based on JSR303 Bean Validation
 * 1. Basic Parameters: @QueryParam, @PathParam
 * 2. Extend Parameters: @BodyParam -> JsonObject, JsonArray
 * 3. POJO Parameters: @BodyParam -> POJO
 */
public class VerifierSplitter {

    public Sentry distribute(final Depot depot) {
        // Annotation to different verifier workflow
        // In current situation, there is only one implementation to build StandardVerifier
        // In future we could extend this implementation
        return Instance.singleton(StandardVerifier.class);
    }
}
