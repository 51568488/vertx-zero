package io.vertx.zero.hors;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import org.vie.exception.DemonException;
import org.vie.exception.ensure.RequiredFieldException;
import top.test.UnitBase;

public class EnsureTc extends UnitBase {

    @Test
    public void testRequired(final TestContext context) {
        final DemonException error =
                new RequiredFieldException(getClass(), new JsonObject(), "name");

    }
}
