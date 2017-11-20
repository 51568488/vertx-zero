package io.vertx.up.rs.dispatcher;

import io.vertx.up.atom.Depot;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Sentry;
import io.vertx.up.rs.sentry.PojoVerifier;

/**
 * Validation for request based on JSR303 Bean Validation
 * 1. Basic Parameters: @QueryParam, @PathParam
 * 2. Extend Parameters: @BodyParam -> JsonObject, JsonArray
 * 3. POJO Parameters: @BodyParam -> POJO
 */
public class VerifierSplitter {
    private static final Annal LOGGER = Annal.get(VerifierSplitter.class);

    public Sentry distribute(final Depot depot) {
        // Annotation to different verifier workflow
        Fn.itMap(depot.getAnnotations(), (field, clazz) -> {
            // 1. PojoVerifier
        });
        return new PojoVerifier();
    }
}
