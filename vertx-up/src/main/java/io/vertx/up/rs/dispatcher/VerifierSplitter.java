package io.vertx.up.rs.dispatcher;

import io.vertx.up.atom.Depot;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Sentry;
import io.vertx.up.rs.sentry.FieldVerifier;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Validation for request based on JSR303 Bean Validation
 * 1. Basic Parameters: @QueryParam, @PathParam
 * 2. Extend Parameters: @BodyParam -> JsonObject, JsonArray
 * 3. POJO Parameters: @BodyParam -> POJO
 */
public class VerifierSplitter {
    private static final Annal LOGGER = Annal.get(VerifierSplitter.class);

    public Sentry distribute(final Depot depot) {
        System.out.println(depot.getTypes());
        System.out.println(depot.getValues());
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        return new FieldVerifier();
    }
}
