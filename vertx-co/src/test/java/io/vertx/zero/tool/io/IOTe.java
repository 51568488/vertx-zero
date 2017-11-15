package io.vertx.zero.tool.io;

import io.vertx.ext.unit.TestContext;
import io.vertx.zero.exception.EmptyStreamException;
import io.vertx.zero.test.StoreBase;
import org.junit.Test;

public class IOTe extends StoreBase {

    @Test(expected = EmptyStreamException.class)
    public void testProp(final TestContext context) {
        context.assertNotNull(IO.getProp("in.properties"));
    }
}
