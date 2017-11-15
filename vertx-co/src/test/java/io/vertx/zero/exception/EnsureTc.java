package io.vertx.zero.exception;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.up.exception.zero.RequiredFieldException;
import io.vertx.zero.test.UnitBase;
import org.junit.Test;

public class EnsureTc extends UnitBase {

    @Test
    public void testRequired(final TestContext context) {
        final DemonException error =
                new RequiredFieldException(getClass(), new JsonObject(), "name");

    }
}
